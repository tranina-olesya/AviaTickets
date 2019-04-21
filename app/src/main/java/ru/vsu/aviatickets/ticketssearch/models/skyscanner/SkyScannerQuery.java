package ru.vsu.aviatickets.ticketssearch.models.skyscanner;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SkyScannerQuery {
    @SerializedName("Adults")
    @Expose
    private Integer adults;

    @SerializedName("Children")
    @Expose
    private Integer children;

    @SerializedName("Infants")
    @Expose
    private Integer infants;

    @SerializedName("OriginPlace")
    @Expose
    private String originPlace;

    @SerializedName("DestinationPlace")
    @Expose
    private String destinationPlace;

    @SerializedName("CabinClass")
    @Expose
    private String cabinClass;

    @SerializedName("GroupPricing")
    @Expose
    private boolean groupPricing;

    public SkyScannerQuery(Integer adults, Integer children, Integer infants, String originPlace, String destinationPlace, String cabinClass, boolean groupPricing) {
        this.adults = adults;
        this.children = children;
        this.infants = infants;
        this.originPlace = originPlace;
        this.destinationPlace = destinationPlace;
        this.cabinClass = cabinClass;
        this.groupPricing = groupPricing;
    }

    public SkyScannerQuery() {
    }

    public Integer getAdults() {
        return adults;
    }

    public void setAdults(Integer adults) {
        this.adults = adults;
    }

    public Integer getChildren() {
        return children;
    }

    public void setChildren(Integer children) {
        this.children = children;
    }

    public Integer getInfants() {
        return infants;
    }

    public void setInfants(Integer infants) {
        this.infants = infants;
    }

    public String getOriginPlace() {
        return originPlace;
    }

    public void setOriginPlace(String originPlace) {
        this.originPlace = originPlace;
    }

    public String getDestinationPlace() {
        return destinationPlace;
    }

    public void setDestinationPlace(String destinationPlace) {
        this.destinationPlace = destinationPlace;
    }

    public String getCabinClass() {
        return cabinClass;
    }

    public void setCabinClass(String cabinClass) {
        this.cabinClass = cabinClass;
    }

    public boolean isGroupPricing() {
        return groupPricing;
    }

    public void setGroupPricing(boolean groupPricing) {
        this.groupPricing = groupPricing;
    }
}
