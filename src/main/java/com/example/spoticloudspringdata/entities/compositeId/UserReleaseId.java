package com.example.spoticloudspringdata.entities.compositeId;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class UserReleaseId implements Serializable {
    @Column(name = "user_id")
    private int userId;
    @Column(name = "release_id")
    private int releaseId;

    public UserReleaseId() {
    }

    public UserReleaseId(int userId, int releaseId) {
        this.userId = userId;
        this.releaseId = releaseId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getReleaseId() {
        return releaseId;
    }

    public void setReleaseId(int releaseId) {
        this.releaseId = releaseId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserReleaseId that = (UserReleaseId) o;
        return userId == that.userId && releaseId == that.releaseId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, releaseId);
    }
}