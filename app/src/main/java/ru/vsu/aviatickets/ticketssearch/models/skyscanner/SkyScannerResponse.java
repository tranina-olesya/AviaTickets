package ru.vsu.aviatickets.ticketssearch.models.skyscanner;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

import ru.vsu.aviatickets.ticketssearch.models.Carrier;
import ru.vsu.aviatickets.ticketssearch.models.Place;

public class SkyScannerResponse {
    @SerializedName("SessionKey")
    @Expose
    private String sessionKey;

    @SerializedName("Query")
    @Expose
    private SkyScannerQuery query;

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
    private List<SkyScannerAgent> skyScannerAgents;

    public SkyScannerResponse(String sessionKey, SkyScannerQuery query, List<Segment> segments, List<Leg> legs, List<Place> places, List<Itinerary> itineraries, List<Carrier> carriers, List<SkyScannerAgent> skyScannerAgents) {
        this.sessionKey = sessionKey;
        this.query = query;
        this.segments = segments;
        this.legs = legs;
        this.places = places;
        this.itineraries = itineraries;
        this.carriers = carriers;
        this.skyScannerAgents = skyScannerAgents;
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

    public List<SkyScannerAgent> getSkyScannerAgents() {
        return skyScannerAgents;
    }

    public void setSkyScannerAgents(List<SkyScannerAgent> skyScannerAgents) {
        this.skyScannerAgents = skyScannerAgents;
    }

    public SkyScannerQuery getQuery() {
        return query;
    }

    public void setQuery(SkyScannerQuery query) {
        this.query = query;
    }
}
