package com.example.spoticloudbackend.schemas;

import com.example.spoticloudbackend.entities.Genre;

public class GenreDto {
    private String genre;
    private int id;
    public GenreDto(Genre genre) {
        this.genre = genre.getName();
        this.id = genre.getId();
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
