package com.example.spoticloudspringdata.schemas;

import com.example.spoticloudspringdata.entities.Genre;

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
