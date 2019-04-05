package ru.vsu.aviatickets.ticketssearch.models.skyscanner;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Itinerary {
    @SerializedName("OutboundLegId")
    @Expose
    private String outboundLegId;

    @SerializedName("InboundLegId")
    @Expose
    private String inboundLegId;

    public Itinerary(String outboundLegId, String inboundLegId) {
        this.outboundLegId = outboundLegId;
        this.inboundLegId = inboundLegId;
    }

    public Itinerary() {
    }

    public String getOutboundLegId() {
        return outboundLegId;
    }

    public void setOutboundLegId(String outboundLegId) {
        this.outboundLegId = outboundLegId;
    }

    public String getInboundLegId() {
        return inboundLegId;
    }

    public void setInboundLegId(String inboundLegId) {
        this.inboundLegId = inboundLegId;
    }
}
