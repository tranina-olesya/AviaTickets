package ru.vsu.aviatickets.ticketssearch.models.skyscanner;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.Date;

public class Segment {
    @SerializedName("Id")
    @Expose
    private Integer id;

    @SerializedName("OriginStation")
    @Expose
    private Integer originStation;

    @SerializedName("DestinationStation")
    @Expose
    private Integer destinationStation;

    @SerializedName("DepartureDateTime")
    @Expose
    private Date departureDateTime;

    @SerializedName("ArrivalDateTime")
    @Expose
    private Date arrivalDateTime;

    @SerializedName("Carrier")
    @Expose
    private Integer carrier;

    @SerializedName("Duration")
    @Expose
    private Integer duration;

    @SerializedName("FlightNumber")
    @Expose
    private String flightNumber;

    @SerializedName("Directionality")
    @Expose
    private String directionality;

    public Segment(Integer id, Integer originStation, Integer destinationStation, Date departureDateTime, Date arrivalDateTime, Integer carrier, Integer duration, String flightNumber, String directionality) {
        this.id = id;
        this.originStation = originStation;
        this.destinationStation = destinationStation;
        this.departureDateTime = departureDateTime;
        this.arrivalDateTime = arrivalDateTime;
        this.carrier = carrier;
        this.duration = duration;
        this.flightNumber = flightNumber;
        this.directionality = directionality;
    }

    public Segment() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public Date getDepartureDateTime() {
        return departureDateTime;
    }

    public void setDepartureDateTime(Date departureDateTime) {
        this.departureDateTime = departureDateTime;
    }

    public Date getArrivalDateTime() {
        return arrivalDateTime;
    }

    public void setArrivalDateTime(Date arrivalDateTime) {
        this.arrivalDateTime = arrivalDateTime;
    }

    public Integer getCarrier() {
        return carrier;
    }

    public void setCarrier(Integer carrier) {
        this.carrier = carrier;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public String getFlightNumber() {
        return flightNumber;
    }

    public void setFlightNumber(String flightNumber) {
        this.flightNumber = flightNumber;
    }

    public String getDirectionality() {
        return directionality;
    }

    public void setDirectionality(String directionality) {
        this.directionality = directionality;
    }
}
