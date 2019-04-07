package ru.vsu.aviatickets.ticketssearch.models;

import java.util.ArrayList;
import java.util.List;

public class Trip {
    private Flight outbound;
    private Flight inbound;
    private List<PriceLink> priceLinks;

    public Trip(Flight outbound, Flight inbound, List<PriceLink> priceLinks) {
        this.outbound = outbound;
        this.inbound = inbound;
        this.priceLinks = priceLinks;
    }

    public Trip() {
        priceLinks = new ArrayList<>();
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

    public List<PriceLink> getPriceLinks() {
        return priceLinks;
    }

    public void setPriceLinks(List<PriceLink> priceLinks) {
        this.priceLinks = priceLinks;
    }
}
