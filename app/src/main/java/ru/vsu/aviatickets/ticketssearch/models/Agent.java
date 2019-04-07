package ru.vsu.aviatickets.ticketssearch.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

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
