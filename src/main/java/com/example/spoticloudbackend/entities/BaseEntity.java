package com.example.spoticloudbackend.entities;
import jakarta.persistence.*;

@MappedSuperclass
public abstract class BaseEntity {
    protected int id;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
