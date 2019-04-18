package ru.vsu.aviatickets.ticketssearch.models;

import java.io.Serializable;
import java.util.List;

public class PriceLink implements Serializable {
    private List<Agent> agents;
    private Double price;
    private String deepLink;

    public PriceLink() {
    }

    public PriceLink(List<Agent> agents, Double price, String deepLink) {
        this.agents = agents;
        this.price = price;
        this.deepLink = deepLink;
    }

    public List<Agent> getAgents() {
        return agents;
    }

    public void setAgents(List<Agent> agents) {
        this.agents = agents;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getDeepLink() {
        return deepLink;
    }

    public void setDeepLink(String deepLink) {
        this.deepLink = deepLink;
    }
}
