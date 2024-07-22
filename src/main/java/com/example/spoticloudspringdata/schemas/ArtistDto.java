package com.example.spoticloudspringdata.schemas;

import com.example.spoticloudspringdata.entities.Artist;

public class ArtistDto {
    private String name;
    private String type;
    private String description;
    private String country;
    private String genre;

    public ArtistDto(String name, String type, String description, String country, String genre) {
        this.name = name;
        this.type = type;
        this.description = description;
        this.country = country;
        this.genre = genre;

    }

    public ArtistDto(Artist artist) {
        this.name = artist.getName();
        this.type = artist.getType();
        this.description = artist.getDescription();
        this.country = artist.getCountry();
        this.genre = artist.getGenre().getName();
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }
}
