package ru.vsu.aviatickets.api.entities;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.Date;

import ru.vsu.aviatickets.api.entities.tripmodels.CabinClass;
import ru.vsu.aviatickets.api.entities.tripmodels.FlightType;
import ru.vsu.aviatickets.api.entities.tripmodels.SearchData;

public class SearchHistoryEntry {
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
    @SerializedName("outboundDate")
    @Expose
    private Date outboundDate;
    @SerializedName("inboundDate")
    @Expose
    private Date inboundDate;
    @SerializedName("adultsCount")
    @Expose
    private Integer adultsCount;
    @SerializedName("childrenCount")
    @Expose
    private Integer childrenCount;
    @SerializedName("infantsCount")
    @Expose
    private Integer infantsCount;
    @SerializedName("flightType")
    @Expose
    private FlightType flightType;
    @SerializedName("transfers")
    @Expose
    private Boolean transfers;
    @SerializedName("cabinClass")
    @Expose
    private CabinClass cabinClass;

    public SearchHistoryEntry() {
    }

    public SearchHistoryEntry(String userCode, SearchData searchData) {
        this.userCode = userCode;
        this.origin = searchData.getOrigin().getName();
        this.destination = searchData.getDestination().getName();
        this.outboundDate = searchData.getOutboundDate();
        this.inboundDate = searchData.getInboundDate();
        this.adultsCount = searchData.getAdultsCount();
        this.childrenCount = searchData.getChildrenCount();
        this.infantsCount = searchData.getInfantsCount();
        this.flightType = searchData.getFlightType();
        this.transfers = searchData.getTransfers();
        this.cabinClass = searchData.getCabinClass();
    }

    public String getUserCode() {
        return userCode;
    }

    public void setUserCode(String userCode) {
        this.userCode = userCode;
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

    public Date getOutboundDate() {
        return outboundDate;
    }

    public void setOutboundDate(Date outboundDate) {
        this.outboundDate = outboundDate;
    }

    public Date getInboundDate() {
        return inboundDate;
    }

    public void setInboundDate(Date inboundDate) {
        this.inboundDate = inboundDate;
    }

    public Integer getAdultsCount() {
        return adultsCount;
    }

    public void setAdultsCount(Integer adultsCount) {
        this.adultsCount = adultsCount;
    }

    public Integer getChildrenCount() {
        return childrenCount;
    }

    public void setChildrenCount(Integer childrenCount) {
        this.childrenCount = childrenCount;
    }

    public Integer getInfantsCount() {
        return infantsCount;
    }

    public void setInfantsCount(Integer infantsCount) {
        this.infantsCount = infantsCount;
    }

    public FlightType getFlightType() {
        return flightType;
    }

    public void setFlightType(FlightType flightType) {
        this.flightType = flightType;
    }

    public boolean getTransfers() {
        return transfers;
    }

    public void setTransfers(boolean transfers) {
        this.transfers = transfers;
    }

    public CabinClass getCabinClass() {
        return cabinClass;
    }

    public void setCabinClass(CabinClass cabinClass) {
        this.cabinClass = cabinClass;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
