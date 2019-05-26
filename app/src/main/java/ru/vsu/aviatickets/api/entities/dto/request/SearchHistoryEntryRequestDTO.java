package ru.vsu.aviatickets.api.entities.dto.request;

import java.util.Date;

import ru.vsu.aviatickets.api.entities.tripmodels.CabinClass;
import ru.vsu.aviatickets.api.entities.tripmodels.FlightType;

public class SearchHistoryEntryRequestDTO {

    private String userCode;

    private String origin;

    private String destination;

    private Date outboundDate;

    private Date inboundDate;

    private Integer adultsCount;

    private Integer childrenCount;

    private Integer infantsCount;

    private FlightType flightType;

    private boolean transfers;

    private CabinClass cabinClass;

    public SearchHistoryEntryRequestDTO() {
    }

    public SearchHistoryEntryRequestDTO(String userCode, String origin, String destination, Date outboundDate, Date inboundDate, Integer adultsCount, Integer childrenCount, Integer infantsCount, FlightType flightType, boolean transfers, CabinClass cabinClass) {
        this.userCode = userCode;
        this.origin = origin;
        this.destination = destination;
        this.outboundDate = outboundDate;
        this.inboundDate = inboundDate;
        this.adultsCount = adultsCount;
        this.childrenCount = childrenCount;
        this.infantsCount = infantsCount;
        this.flightType = flightType;
        this.transfers = transfers;
        this.cabinClass = cabinClass;
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
}
