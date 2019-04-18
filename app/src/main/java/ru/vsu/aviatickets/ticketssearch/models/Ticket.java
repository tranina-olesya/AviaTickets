package ru.vsu.aviatickets.ticketssearch.models;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

import ru.vsu.aviatickets.ticketssearch.models.skyscanner.SkyScannerPlace;

public class Ticket implements Serializable {
    private Carrier carrier;
    private Place origin;
    private Place destination;
    private Date outboundDate;
    private Date inboundDate;
    private Integer duration;

    public Ticket(Carrier carrier, Place origin, Place destination, Date outboundDate, Date inboundDate, Integer duration) {
        this.carrier = carrier;
        this.origin = origin;
        this.destination = destination;
        this.outboundDate = outboundDate;
        this.inboundDate = inboundDate;
        this.duration = duration;
    }

    public Ticket() {
    }

    public Carrier getCarrier() {
        return carrier;
    }

    public void setCarrier(Carrier carrier) {
        this.carrier = carrier;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ticket ticket = (Ticket) o;
        return Objects.equals(carrier, ticket.carrier) &&
                Objects.equals(origin, ticket.origin) &&
                Objects.equals(destination, ticket.destination) &&
                Objects.equals(outboundDate, ticket.outboundDate) &&
                Objects.equals(inboundDate, ticket.inboundDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(carrier, origin, destination, outboundDate, inboundDate, duration);
    }
}
