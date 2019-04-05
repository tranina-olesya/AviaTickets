package ru.vsu.aviatickets.ticketssearch.models.skyscanner;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.Date;
import java.util.List;

public class Leg {
    @SerializedName("Id")
    @Expose
    private String id;

    @SerializedName("SegmentIds")
    @Expose
    private List<Integer> segmentIds;

    @SerializedName("OriginStation")
    @Expose
    private Integer originStation;

    @SerializedName("DestinationStation")
    @Expose
    private Integer destinationStation;

    @SerializedName("Departure")
    @Expose
    private Date departure;

    @SerializedName("Arrival")
    @Expose
    private Date arrival;

    @SerializedName("Duration")
    @Expose
    private Integer duration;

    @SerializedName("Carriers")
    @Expose
    private List<Integer> carriers;

    @SerializedName("Directionality")
    @Expose
    private String directionality;

    @SerializedName("FlightNumbers")
    @Expose
    private List<FlightNumber> flightNumbers;

    public Leg(String id, List<Integer> segmentIds, Integer originStation, Integer destinationStation, Date departure, Date arrival, Integer duration, List<Integer> carriers, String directionality, List<FlightNumber> flightNumbers) {
        this.id = id;
        this.segmentIds = segmentIds;
        this.originStation = originStation;
        this.destinationStation = destinationStation;
        this.departure = departure;
        this.arrival = arrival;
        this.duration = duration;
        this.carriers = carriers;
        this.directionality = directionality;
        this.flightNumbers = flightNumbers;
    }

    public Leg() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<Integer> getSegmentIds() {
        return segmentIds;
    }

    public void setSegmentIds(List<Integer> segmentIds) {
        this.segmentIds = segmentIds;
    }

    public Integer getOriginStation() {
        return originStation;
    }

    public void setOriginStation(Integer originStation) {
        this.originStation = originStation;
    }

    public Integer getDestinationStation() {
        return destinationStation;
    }

    public void setDestinationStation(Integer destinationStation) {
        this.destinationStation = destinationStation;
    }

    public Date getDeparture() {
        return departure;
    }

    public void setDeparture(Date departure) {
        this.departure = departure;
    }

    public Date getArrival() {
        return arrival;
    }

    public void setArrival(Date arrival) {
        this.arrival = arrival;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public List<Integer> getCarriers() {
        return carriers;
    }

    public void setCarriers(List<Integer> carriers) {
        this.carriers = carriers;
    }

    public String getDirectionality() {
        return directionality;
    }

    public void setDirectionality(String directionality) {
        this.directionality = directionality;
    }

    public List<FlightNumber> getFlightNumbers() {
        return flightNumbers;
    }

    public void setFlightNumbers(List<FlightNumber> flightNumbers) {
        this.flightNumbers = flightNumbers;
    }
}
