package ru.vsu.aviatickets.ticketssearch.models.iata;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class City {
    @SerializedName("iata")
    @Expose
    private String iata;

    @SerializedName("name")
    @Expose
    private String name;

    public City(String iata, String name) {
        this.iata = iata;
        this.name = name;
    }

    public City() {
    }

    public String getIata() {
        return iata;
    }

    public void setIata(String iata) {
        this.iata = iata;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Iata: " + iata + "\nName: " + name;
    }
}
