package ru.vsu.aviatickets.api.entities.tripmodels;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Trip implements Serializable {
    private Flight outbound;
    private Flight inbound;
    private List<PriceLink> priceLinks;
    private Double minPrice;

    public Trip(Flight outbound, Flight inbound, List<PriceLink> priceLinks, Double minPrice) {
        this.outbound = outbound;
        this.inbound = inbound;
        this.priceLinks = priceLinks;
        this.minPrice = minPrice;
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

    public Double getMinPrice() {
        return minPrice;
    }

    public void setMinPrice(Double minPrice) {
        this.minPrice = minPrice;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Trip trip = (Trip) o;
        return Objects.equals(outbound, trip.outbound) &&
                Objects.equals(inbound, trip.inbound);
    }

    @Override
    public int hashCode() {
        return Objects.hash(outbound, inbound);
    }
}
