package ru.vsu.aviatickets.ticketssearch.models.skyscanner;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class SkyScannerPlaces {

    @SerializedName("Places")
    @Expose
    private List<SkyScannerPlace> places = null;

    public List<SkyScannerPlace> getPlaces() {
        return places;
    }

    public void setPlaces(List<SkyScannerPlace> places) {
        this.places = places;
    }

}
