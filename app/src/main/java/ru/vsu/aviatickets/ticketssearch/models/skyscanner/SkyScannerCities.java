package ru.vsu.aviatickets.ticketssearch.models.skyscanner;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class SkyScannerCities {

    @SerializedName("Places")
    @Expose
    private List<SkyScannerCity> places = null;

    public List<SkyScannerCity> getPlaces() {
        return places;
    }

    public void setPlaces(List<SkyScannerCity> places) {
        this.places = places;
    }

}
