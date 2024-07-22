package com.example.spoticloudbackend.entities;

import jakarta.persistence.*;

import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "listening_history")
public class ListeningHistory extends BaseEntity {
    private User user;
    private Track track;
    private Timestamp dateListened;

    protected ListeningHistory() {
    }

    public ListeningHistory(User user, Track track) {
        this.user = user;
        this.track = track;
        this.dateListened = new Timestamp(System.currentTimeMillis());
    }

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @ManyToOne
    @JoinColumn(name = "track_id", referencedColumnName = "id")
    public Track getTrack() {
        return track;
    }

    public void setTrack(Track track) {
        this.track = track;
    }

    @Column(name = "date_listened", insertable = false, updatable = false)
    public Timestamp getDateListened() {
        return dateListened;
    }

    public void setDateListened(Timestamp dateListened) {
        this.dateListened = dateListened;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ListeningHistory that = (ListeningHistory) o;
        return Objects.equals(user, that.user) && Objects.equals(track, that.track) && Objects.equals(dateListened, that.dateListened);
    }

    @Override
    public int hashCode() {
        return Objects.hash(user, track, dateListened);
    }
}
