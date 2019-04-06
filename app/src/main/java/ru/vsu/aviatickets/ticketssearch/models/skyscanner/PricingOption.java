package ru.vsu.aviatickets.ticketssearch.models.skyscanner;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class PricingOption {
    @SerializedName("Agents")
    @Expose
    private List<Integer> agents;

    @SerializedName("Price")
    @Expose
    private Double price;

    @SerializedName("DeeplinkUrl")
    @Expose
    private String deeplinkUrl;

    public PricingOption(List<Integer> agents, Double price, String deeplinkUrl) {
        this.agents = agents;
        this.price = price;
        this.deeplinkUrl = deeplinkUrl;
    }

    public PricingOption() {
    }

    public List<Integer> getAgents() {
        return agents;
    }

    public void setAgents(List<Integer> agents) {
        this.agents = agents;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getDeeplinkUrl() {
        return deeplinkUrl;
    }

    public void setDeeplinkUrl(String deeplinkUrl) {
        this.deeplinkUrl = deeplinkUrl;
    }
}
