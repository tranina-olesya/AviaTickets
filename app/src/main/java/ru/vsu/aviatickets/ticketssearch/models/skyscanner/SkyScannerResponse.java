package ru.vsu.aviatickets.ticketssearch.models.skyscanner;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class SkyScannerResponse {
    @SerializedName("SessionKey")
    @Expose
    private String sessionKey;

    @SerializedName("Segments")
    @Expose
    private List<Segment> segments;

    @SerializedName("Legs")
    @Expose
    private List<Leg> legs;

    @SerializedName("Places")
    @Expose
    private List<Place> places;

    @SerializedName("Itineraries")
    @Expose
    private List<Itinerary> itineraries;

    @SerializedName("Carriers")
    @Expose
    private List<Carrier> carriers;

    @SerializedName("Agents")
    @Expose
    private List<Agent> agents;

    public SkyScannerResponse(String sessionKey, List<Segment> segments, List<Leg> legs, List<Place> places, List<Itinerary> itineraries, List<Carrier> carriers, List<Agent> agents) {
        this.sessionKey = sessionKey;
        this.segments = segments;
        this.legs = legs;
        this.places = places;
        this.itineraries = itineraries;
        this.carriers = carriers;
        this.agents = agents;
    }

    public SkyScannerResponse() {
    }

    public String getSessionKey() {
        return sessionKey;
    }

    public void setSessionKey(String sessionKey) {
        this.sessionKey = sessionKey;
    }

    public List<Segment> getSegments() {
        return segments;
    }

    public void setSegments(List<Segment> segments) {
        this.segments = segments;
    }

    public List<Leg> getLegs() {
        return legs;
    }

    public void setLegs(List<Leg> legs) {
        this.legs = legs;
    }

    public List<Place> getPlaces() {
        return places;
    }

    public void setPlaces(List<Place> places) {
        this.places = places;
    }

    public List<Itinerary> getItineraries() {
        return itineraries;
    }

    public void setItineraries(List<Itinerary> itineraries) {
        this.itineraries = itineraries;
    }

    public List<Carrier> getCarriers() {
        return carriers;
    }

    public void setCarriers(List<Carrier> carriers) {
        this.carriers = carriers;
    }

    public List<Agent> getAgents() {
        return agents;
    }

    public void setAgents(List<Agent> agents) {
        this.agents = agents;
    }
}
