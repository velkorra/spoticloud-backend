package com.example.spoticloudbackend.services.implementations;

import com.example.spoticloudbackend.entities.*;
import com.example.spoticloudbackend.exceptions.NoRecommendationFoundException;
import com.example.spoticloudbackend.exceptions.UserNotFoundException;
import com.example.spoticloudbackend.repositories.TrackRepository;
import com.example.spoticloudbackend.repositories.PlaylistRepository;
import com.example.spoticloudbackend.repositories.UserRepository;
import com.example.spoticloudbackend.services.RecommendationService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class RecommendationServiceImpl implements RecommendationService {
    private final UserRepository userRepository;
    private final TrackRepository trackRepository;
    private final PlaylistRepository playlistRepository;

    @Autowired
    public RecommendationServiceImpl(UserRepository userRepository, TrackRepository trackRepository, PlaylistRepository playlistRepository) {
        this.userRepository = userRepository;
        this.trackRepository = trackRepository;
        this.playlistRepository = playlistRepository;
    }

    public void adjustPreferences(User user, Track track, float value) {
        Set<UserPreferences> userPreferences = user.getUserPreferences();
        Set<Genre> genres = track.getTags();

        for (Genre genre : genres) {
            UserPreferences currentPreference = userPreferences.stream()
                    .filter(p -> p.getGenre().equals(genre)).findFirst().orElse(null);
            if (currentPreference != null) {
                currentPreference.increaseValue(value);
            } else {
                UserPreferences preference = new UserPreferences(0, user, genre);
                userPreferences.add(preference);
            }
        }
    }

    public Track recommendTrack(int userId) {
        User user = userRepository.findById(userId).orElseThrow(
                () -> new UserNotFoundException(userId)
        );
        return recommendTrack(user);
    }

    public Track recommendTrack(User user) {
        Random random = new Random();

        List<Track> listeningHistory = user.getListenedTracks().stream().limit(20).toList();

        List<Track> recentListenedTracks = listeningHistory.stream()
                .limit(5)
                .toList();

        Set<Genre> recentFiveGenres = recentListenedTracks.stream()
                .flatMap(track -> track.getTags().stream())
                .collect(Collectors.toSet());

        List<Track> potentialTracks = trackRepository.findAll()
                .stream()
                .filter(track -> !listeningHistory.contains(track))
                .filter(track -> !Collections.disjoint(track.getTags(), recentFiveGenres))
                .toList();

        if (potentialTracks.isEmpty()) {
            throw new NoRecommendationFoundException(user.getId());
        }

        return potentialTracks.get(random.nextInt(potentialTracks.size()));
    }

    @Transactional
    public Playlist recommendPlaylist(int userId) {
        User user = userRepository.findById(userId).orElseThrow(
                () -> new UserNotFoundException(userId)
        );

        List<Track> tracks = recommendTracks(user);
        String name = generatePlaylistName(tracks, user);
        String description = generatePlaylistDescription(tracks, user);
        Playlist playlist = new Playlist(name, true, description, user);
        for (Track track : tracks) {
            playlist.addTrack(track);
        }
        return playlist;
    }

    @Transactional
    public List<Track> recommendTracks(int userId){
        User user = userRepository.findById(userId).orElseThrow(
                () -> new UserNotFoundException(userId)
        );
        return recommendTracks(user);
    }

    @Transactional
    public List<Track> recommendTracks(User user)
    {
        int numberOfTracks = 10;
        Random random = new Random();
        List<Track> listeningHistory = user.getListenedTracks().stream().limit(20).toList();

        List<Track> recentListenedTracks = listeningHistory.stream()
                .limit(5)
                .toList();

        Set<Genre> recentFiveGenres = recentListenedTracks.stream()
                .flatMap(track -> track.getTags().stream())
                .collect(Collectors.toSet());

        List<Track> potentialTracks = trackRepository.findAll()
                .stream()
                .filter(track -> !listeningHistory.contains(track))
                .filter(track -> !Collections.disjoint(track.getTags(), recentFiveGenres))
                .collect(Collectors.toList());

        if (potentialTracks.isEmpty()) {
            throw new NoRecommendationFoundException(user.getId());
        }

        Map<Genre, Float> userPreferencesMap = user.getUserPreferences().stream()
                .collect(Collectors.toMap(UserPreferences::getGenre, UserPreferences::getValue));

        potentialTracks.sort((track1, track2) -> {
            float score1 = track1.getTags().stream()
                    .map(genre -> userPreferencesMap.getOrDefault(genre, 0.0f))
                    .reduce(0.0f, Float::sum);
            float score2 = track2.getTags().stream()
                    .map(genre -> userPreferencesMap.getOrDefault(genre, 0.0f))
                    .reduce(0.0f, Float::sum);

            return Float.compare(score2, score1);
        });

        Collections.shuffle(potentialTracks.subList(0, Math.min(10, potentialTracks.size())), random);

        return potentialTracks.stream().limit(numberOfTracks).toList();
    }
    private String generatePlaylistName(List<Track> tracks, User user) {
        Random random = new Random();
        List<String> trackNames = tracks.stream().map(Track::getName).limit(3).toList();
        List<String> artistNames = tracks.stream().map(track -> track.getArtist().getName()).distinct().limit(3).toList();

        List<String> nameParts = new ArrayList<>();
        nameParts.add("Mix");
        nameParts.addAll(trackNames);
        nameParts.addAll(artistNames);
        nameParts.add(user.getUsername());

        Collections.shuffle(nameParts, random);

        return nameParts.stream().limit(3).collect(Collectors.joining(" "));
    }

    private String generatePlaylistDescription(List<Track> tracks, User user) {
        List<String> genreNames = tracks.stream().flatMap(track -> track.getTags().stream().map(Genre::getName)).distinct().limit(3).toList();
        List<String> artistNames = tracks.stream().map(track -> track.getArtist().getName()).distinct().limit(3).toList();

        return "A mix of " +
                String.join(", ", genreNames) +
                " by " +
                String.join(", ", artistNames) +
                ". Compiled by " +
                user.getUsername() +
                ".";
    }

}
