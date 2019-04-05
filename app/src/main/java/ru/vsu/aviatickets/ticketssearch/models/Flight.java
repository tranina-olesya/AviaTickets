package ru.vsu.aviatickets.ticketssearch.models;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Flight {
    private String origin;
    private String destination;
    private Date outboundDate;
    private Date inboundDate;
    private Integer flightLength;
    private int adultsCount;
    private int childrenCount;
    private int infantsCount;
    private List<Ticket> flightParts;

    public Flight(String origin, String destination, Date outboundDate, Date inboundDate, Integer flightLength, int adultsCount, int childrenCount, int infantsCount, List<Ticket> flightParts) {
        this();
        this.origin = origin;
        this.destination = destination;
        this.outboundDate = outboundDate;
        this.inboundDate = inboundDate;
        this.flightLength = flightLength;
        this.adultsCount = adultsCount;
        this.childrenCount = childrenCount;
        this.infantsCount = infantsCount;
        this.flightParts = flightParts;
    }

    public Flight() {
        flightParts = new ArrayList<>();
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
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

    public Integer getFlightLength() {
        return flightLength;
    }

    public void setFlightLength(Integer flightLength) {
        this.flightLength = flightLength;
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
}
