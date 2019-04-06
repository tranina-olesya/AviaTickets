package ru.vsu.aviatickets.ticketssearch.models;

public class Trip {
    private Flight outbound;
    private Flight inbound;

    public Trip(Flight outbound, Flight inbound) {
        this.outbound = outbound;
        this.inbound = inbound;
    }

    public Trip() {
    }

    public Flight getOutbound() {
        return outbound;
    }

    public void setOutbound(Flight outbound) {
        this.outbound = outbound;
    }

    public Flight getInbound() {
        return inbound;
    }

    public void setInbound(Flight inbound) {
        this.inbound = inbound;
    }
}
