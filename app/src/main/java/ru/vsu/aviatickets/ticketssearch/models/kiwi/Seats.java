
package ru.vsu.aviatickets.ticketssearch.models.kiwi;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Seats {

    @SerializedName("infants")
    @Expose
    private Integer infants;
    @SerializedName("passengers")
    @Expose
    private Integer passengers;
    @SerializedName("adults")
    @Expose
    private Integer adults;
    @SerializedName("children")
    @Expose
    private Integer children;

    public Seats() {
    }

    public Seats(Integer infants, Integer passengers, Integer adults, Integer children) {
        super();
        this.infants = infants;
        this.passengers = passengers;
        this.adults = adults;
        this.children = children;
    }

    public Integer getInfants() {
        return infants;
    }

    public void setInfants(Integer infants) {
        this.infants = infants;
    }

    public Integer getPassengers() {
        return passengers;
    }

    public void setPassengers(Integer passengers) {
        this.passengers = passengers;
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

}
