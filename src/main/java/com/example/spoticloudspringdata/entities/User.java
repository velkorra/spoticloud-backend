package com.example.spoticloudspringdata.entities;

import jakarta.persistence.*;

import java.sql.Timestamp;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

@Entity
@Table(name = "users")
public class User extends BaseEntity implements SoftDeletable {
    private String username;
    private String email;
    private String password;
    private Timestamp dateRegistered;
    private Boolean isDeleted;
    private Set<UserPlaylist> userPlaylists;
    private Set<UserRelease> userReleases;
    private Set<UserPreferences> userPreferences;
    private Set<Playlist> createdPlaylists;
    private Set<ListeningHistory> listeningHistory;
    private List<LikedTracks> likedTracks;
    private Set<PlaylistAccess> accessedPlaylists;

    protected User() {

    }

    public User(String username, String email, String password) {
        this.username = username;
        this.email = email;
        this.password = password;
    }

    @Basic
    @Column(name = "username")
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Basic
    @Column(name = "email")
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Basic
    @Column(name = "password")
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Basic
    @Column(name = "date_registered", insertable = false, updatable = false)
    public Timestamp getDateRegistered() {
        return dateRegistered;
    }

    public void setDateRegistered(Timestamp dateRegistered) {
        this.dateRegistered = dateRegistered;
    }

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    public Set<UserPlaylist> getUserPlaylists() {
        return userPlaylists;
    }

    public void setUserPlaylists(Set<UserPlaylist> userPlaylists) {
        this.userPlaylists = userPlaylists;
    }

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    public Set<UserRelease> getUserReleases() {
        return userReleases;
    }

    public void setUserReleases(Set<UserRelease> userReleases) {
        this.userReleases = userReleases;
    }

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    public Set<UserPreferences> getUserPreferences() {
        return userPreferences;
    }

    public void setUserPreferences(Set<UserPreferences> userPreferences) {
        this.userPreferences = userPreferences;
    }

    @Transient
    public Set<Release> getReleases() {
        return getUserReleases().stream().map(UserRelease::getRelease).collect(Collectors.toSet());
    }

    @Basic(fetch = FetchType.LAZY)
    @Column(name = "is_deleted", insertable = false)
    public boolean getDeleted() {
        return isDeleted;
    }

    public void setDeleted(Boolean deleted) {
        isDeleted = deleted;
    }

    @OneToMany(mappedBy = "owner")
    public Set<Playlist> getCreatedPlaylists() {
        return createdPlaylists;
    }

    public void setCreatedPlaylists(Set<Playlist> createdPlaylists) {
        this.createdPlaylists = createdPlaylists;
    }

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    public Set<ListeningHistory> getListeningHistory() {
        return listeningHistory;
    }

    @Transient
    public List<Track> getListenedTracks(){
        return getListeningHistory().stream().sorted(Comparator.comparing(ListeningHistory::getDateListened).reversed()).map(ListeningHistory::getTrack).collect(Collectors.toList());
    }

    public void setListeningHistory(Set<ListeningHistory> listeningHistory) {
        this.listeningHistory = listeningHistory;
    }

    public void addListeningHistory(Track track) {
        getListeningHistory().add(new ListeningHistory(this, track));

    }

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    public List<LikedTracks> getLikedTracks() {
        return likedTracks;
    }

    @Transient
    public void addLikedTrack(Track track) {
        likedTracks.add(new LikedTracks(this, track));
    }

    public void setLikedTracks(List<LikedTracks> likedTracks) {
        this.likedTracks = likedTracks;
    }

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    public Set<PlaylistAccess> getAccessedPlaylists() {
        return accessedPlaylists;
    }

    public void setAccessedPlaylists(Set<PlaylistAccess> accessedPlaylists) {
        this.accessedPlaylists = accessedPlaylists;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User users = (User) o;
        return id == users.id && Objects.equals(username, users.username) && Objects.equals(email, users.email) && Objects.equals(password, users.password) && Objects.equals(dateRegistered, users.dateRegistered);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, username, email, password, dateRegistered);
    }

    @Transient
    @Override
    public boolean isDeleted() {
        return isDeleted;
    }
}
