package com.example.spoticloudbackend.entities;

import jakarta.persistence.*;

import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "genre")
public class Genre extends BaseEntity{
    private String name;
    private Integer parentGenreId;
    private Genre parentGenre;
    private Set<Genre> orphanGenres;
    private Set<Track> tracks;
    private Set<Release> releases;
    private Set<Artist> artists;
    private Set<UserPreferences> userPreferences;

    public Genre(String name, Integer parentGenreId, Set<Track> tracks, Set<Release> releases) {
        this.name = name;
        this.parentGenreId = parentGenreId;
        this.tracks = tracks;
        this.releases = releases;
    }

    protected Genre() {

    }

    @Basic
    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_genre_id", referencedColumnName = "id")
    public Genre getParentGenre() {
        return parentGenre;
    }

    public void setParentGenre(Genre parentGenre) {
        this.parentGenre = parentGenre;
    }

    @OneToMany(mappedBy = "parentGenre")
    public Set<Genre> getOrphanGenres() {
        return orphanGenres;
    }

    public void setOrphanGenres(Set<Genre> orphanGenres) {
        this.orphanGenres = orphanGenres;
    }

    @OneToMany(mappedBy = "genre")
    public Set<Track> getTracks() {
        return tracks;
    }

    public void setTracks(Set<Track> tracks) {
        this.tracks = tracks;
    }


    @OneToMany(mappedBy = "genre")
    public Set<Release> getReleases() {
        return releases;
    }

    public void setReleases(Set<Release> releases) {
        this.releases = releases;
    }

    @OneToMany(mappedBy = "genre")
    public Set<Artist> getArtists() {
        return artists;
    }

    public void setArtists(Set<Artist> artists) {
        this.artists = artists;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Genre genre = (Genre) o;
        return id == genre.id && Objects.equals(name, genre.name) && Objects.equals(parentGenreId, genre.parentGenreId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, parentGenreId);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Genre ").append(id).append(" \"").append(name).append("\"");
        if (parentGenreId != null) {
            sb.append(", its parent genre is ").append("\"Genre ").append(parentGenreId).append("\"");
        }
        return sb.toString();
    }
}