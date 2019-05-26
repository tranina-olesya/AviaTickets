package ru.vsu.aviatickets.api.entities.tripmodels.iata;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Route {
    @SerializedName("origin")
    @Expose
    private City origin;

    @SerializedName("destination")
    @Expose
    private City destination;

    public Route(City origin, City destination) {
        this.origin = origin;
        this.destination = destination;
    }

    public Route() {
    }

    public City getOrigin() {
        return origin;
    }

    public void setOrigin(City origin) {
        this.origin = origin;
    }

    public City getDestination() {
        return destination;
    }

    public void setDestination(City destination) {
        this.destination = destination;
    }

    @Override
    public String toString() {
        return "Origin:\n{\n" + origin.toString() + "\n}\nDestination:\n{\n" + destination.toString() + "\n}\n";
    }
}
