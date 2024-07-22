package com.example.spoticloudbackend.schemas;


public class PlaylistCreateDto {
    private String name;
    private boolean isPrivate;
    private String description;
    private int owner;

    public PlaylistCreateDto(String name, boolean isPrivate, String description, int owner) {
        this.name = name;
        this.isPrivate = isPrivate;
        this.description = description;
        this.owner = owner;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isPrivate() {
        return isPrivate;
    }

    public void setPrivate(boolean aPrivate) {
        isPrivate = aPrivate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getOwner() {
        return owner;
    }

    public void setOwner(int owner) {
        this.owner = owner;
    }
}
