package ru.vsu.aviatickets.ticketssearch.models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import ru.vsu.aviatickets.ticketssearch.models.skyscanner.SkyScannerPlace;

public class Flight implements Serializable {
    private Place origin;
    private Place destination;
    private Date outboundDate;
    private Date inboundDate;
    private Integer duration;
    private int adultsCount;
    private int childrenCount;
    private int infantsCount;
    private List<Ticket> flightParts;

    public Flight(Place origin, Place destination, Date outboundDate, Date inboundDate, Integer duration, int adultsCount, int childrenCount, int infantsCount, List<Ticket> flightParts) {
        this();
        this.origin = origin;
        this.destination = destination;
        this.outboundDate = outboundDate;
        this.inboundDate = inboundDate;
        this.duration = duration;
        this.adultsCount = adultsCount;
        this.childrenCount = childrenCount;
        this.infantsCount = infantsCount;
        this.flightParts = flightParts;
    }

    public Flight() {
        flightParts = new ArrayList<>();
    }

    public Place getOrigin() {
        return origin;
    }

    public void setOrigin(Place origin) {
        this.origin = origin;
    }

    public Place getDestination() {
        return destination;
    }

    public void setDestination(Place destination) {
        this.destination = destination;
    }

    public Date getOutboundDate() {
        return outboundDate;
    }

    public void setOutboundDate(Date outboundDate) {
        this.outboundDate = outboundDate;
    }

    public Date getInboundDate() {
        return inboundDate;
    }

    public void setInboundDate(Date inboundDate) {
        this.inboundDate = inboundDate;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public int getAdultsCount() {
        return adultsCount;
    }

    public void setAdultsCount(int adultsCount) {
        this.adultsCount = adultsCount;
    }

    public int getChildrenCount() {
        return childrenCount;
    }

    public void setChildrenCount(int childrenCount) {
        this.childrenCount = childrenCount;
    }

    public int getInfantsCount() {
        return infantsCount;
    }

    public void setInfantsCount(int infantsCount) {
        this.infantsCount = infantsCount;
    }

    public List<Ticket> getFlightParts() {
        return flightParts;
    }

    public void setFlightParts(List<Ticket> flightParts) {
        this.flightParts = flightParts;
    }

    public Integer getPrice(){
        return null;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Flight flight = (Flight) o;
        return adultsCount == flight.adultsCount &&
                childrenCount == flight.childrenCount &&
                infantsCount == flight.infantsCount &&
                Objects.equals(origin, flight.origin) &&
                Objects.equals(destination, flight.destination) &&
                Objects.equals(outboundDate, flight.outboundDate) &&
                Objects.equals(inboundDate, flight.inboundDate) &&
                Objects.equals(flightParts, flight.flightParts);
    }

    @Override
    public int hashCode() {
        return Objects.hash(origin, destination, outboundDate, inboundDate, adultsCount, childrenCount, infantsCount, flightParts);
    }
}
