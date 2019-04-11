package ru.vsu.aviatickets.ticketssearch.models.skyscanner;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Itinerary {
    @SerializedName("OutboundLegId")
    @Expose
    private String outboundLegId;

    @SerializedName("InboundLegId")
    @Expose
    private String inboundLegId;

    @SerializedName("PricingOptions")
    @Expose
    private List<PricingOption> pricingOptions;

    public Itinerary(String outboundLegId, String inboundLegId, List<PricingOption> pricingOptions) {
        this.outboundLegId = outboundLegId;
        this.inboundLegId = inboundLegId;
        this.pricingOptions = pricingOptions;
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

    public List<PricingOption> getPricingOptions() {
        return pricingOptions;
    }

    public void setPricingOptions(List<PricingOption> pricingOptions) {
        this.pricingOptions = pricingOptions;
    }
}
