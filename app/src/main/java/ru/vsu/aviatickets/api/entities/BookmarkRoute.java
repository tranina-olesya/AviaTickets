package ru.vsu.aviatickets.api.entities;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.Objects;

import ru.vsu.aviatickets.api.entities.tripmodels.CabinClass;
import ru.vsu.aviatickets.api.entities.tripmodels.FlightType;

public class BookmarkRoute {

    @SerializedName("id")
    @Expose
    private Long id;
    @SerializedName("userCode")
    @Expose
    private String userCode;
    @SerializedName("origin")
    @Expose
    private String origin;
    @SerializedName("destination")
    @Expose
    private String destination;
    @SerializedName("adultCount")
    @Expose
    private Integer adultCount;
    @SerializedName("childCount")
    @Expose
    private Integer childCount;
    @SerializedName("infantCount")
    @Expose
    private Integer infantCount;
    @SerializedName("flightType")
    @Expose
    private FlightType flightType;
    @SerializedName("transfers")
    @Expose
    private Boolean transfers;
    @SerializedName("classType")
    @Expose
    private CabinClass classType;

    public BookmarkRoute(String origin, String destination, int adultCount, int childCount, int infantCount, FlightType flightType, boolean transfers, CabinClass classType) {
        this.origin = origin;
        this.destination = destination;
        this.adultCount = adultCount;
        this.childCount = childCount;
        this.infantCount = infantCount;
        this.flightType = flightType;
        this.transfers = transfers;
        this.classType = classType;
    }

    public FlightType getFlightType() {
        return flightType;
    }

    public void setFlightType(FlightType flightType) {
        this.flightType = flightType;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public int getAdultCount() {
        return adultCount;
    }

    public void setAdultCount(int adultCount) {
        this.adultCount = adultCount;
    }

    public int getChildCount() {
        return childCount;
    }

    public void setChildCount(int childCount) {
        this.childCount = childCount;
    }

    public int getInfantCount() {
        return infantCount;
    }

    public void setInfantCount(int infantCount) {
        this.infantCount = infantCount;
    }

    public CabinClass getClassType() {
        return classType;
    }

    public void setClassType(CabinClass classType) {
        this.classType = classType;
    }

    public boolean isTransfers() {
        return transfers;
    }

    public void setTransfers(boolean transfers) {
        this.transfers = transfers;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BookmarkRoute that = (BookmarkRoute) o;
        return adultCount == that.adultCount &&
                childCount == that.childCount &&
                infantCount == that.infantCount &&
                transfers == that.transfers &&
                origin.equals(that.origin) &&
                destination.equals(that.destination) &&
                flightType.equals(that.flightType) &&
                classType.equals(that.classType);
    }

    @Override
    public int hashCode() {
        return Objects.hash(origin, destination, adultCount, childCount, infantCount, flightType, transfers, classType);
    }

    public boolean checkAllNull() {
        return this.id == null && this.adultCount == null && this.childCount == null && this.userCode == null
                && this.infantCount == null && this.origin == null && this.destination == null
                && this.transfers == null && this.classType == null && this.flightType == null;
    }
}
