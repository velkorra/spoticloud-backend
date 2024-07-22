package com.example.spoticloudspringdata.entities.compositeId;


import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class LikedTracksId implements Serializable {
    @Column(name = "track_id")
    private Integer trackId;
    @Column(name = "user_id")
    private Integer UserId;

    protected LikedTracksId() {}

    public LikedTracksId(Integer trackId, Integer UserId) {
        this.trackId = trackId;
        this.UserId = UserId;
    }

    public Integer getTrackId() {
        return trackId;
    }

    public void setTrackId(Integer trackId) {
        this.trackId = trackId;
    }

    public Integer getUserId() {
        return UserId;
    }

    public void setUserId(Integer userId) {
        UserId = userId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LikedTracksId that = (LikedTracksId) o;
        return Objects.equals(trackId, that.trackId) && Objects.equals(UserId, that.UserId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(trackId, UserId);
    }
}
