package com.example.spoticloudspringdata.entities;

import com.example.spoticloudspringdata.entities.compositeId.UserPreferencesId;
import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "user_preferences")
public class UserPreferences {
    private UserPreferencesId id;
    private float value;
    private User user;
    private Genre genre;

    public UserPreferences(float value, User user, Genre genre) {
        this.value = value;
        this.user = user;
        this.genre = genre;
        this.id = new UserPreferencesId(user.getId(), genre.getId());
    }

    protected UserPreferences() {

    }

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("userId")
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("genreId")
    @JoinColumn(name = "genre_id", referencedColumnName = "id")
    public Genre getGenre() {
        return genre;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }

    @EmbeddedId
    public UserPreferencesId getId() {
        return id;
    }

    public void setId(UserPreferencesId id) {
        this.id = id;
    }

    @Column(name = "value", nullable = false, columnDefinition = "real DEFAULT 0")
    public float getValue() {
        return value;
    }

    public void setValue(float value) {
        this.value = value;
    }

    @Transient
    public void increaseValue(float delta) {
        value += delta;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserPreferences that = (UserPreferences) o;
        return Float.compare(value, that.value) == 0 && Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, value);
    }
}