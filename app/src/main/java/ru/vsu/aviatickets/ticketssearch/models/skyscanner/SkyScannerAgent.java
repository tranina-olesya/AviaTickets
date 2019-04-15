package ru.vsu.aviatickets.ticketssearch.models.skyscanner;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import ru.vsu.aviatickets.ticketssearch.models.Agent;

public class SkyScannerAgent extends Agent {
    @SerializedName("Id")
    @Expose
    private Integer id;

    public SkyScannerAgent(Integer id, String name, String imageUrl) {
        super(name, imageUrl);
        this.id = id;
    }

    public SkyScannerAgent() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
