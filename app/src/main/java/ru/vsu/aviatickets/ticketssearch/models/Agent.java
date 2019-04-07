package ru.vsu.aviatickets.ticketssearch.models;

public class Agent {

    private String name;

    private String imageUrl;

    public Agent(String name, String imageUrl) {
        this.name = name;
        this.imageUrl = imageUrl;
    }

    public Agent() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
