package ru.vsu.aviatickets.api.entities.dto.request;

import ru.vsu.aviatickets.api.entities.tripmodels.CabinClass;
import ru.vsu.aviatickets.api.entities.tripmodels.FlightType;
import ru.vsu.aviatickets.api.entities.tripmodels.SearchData;

public class BookmarkRequestDTO {
    private String userCode;

    private String origin;

    private String destination;

    private int adultCount;

    private int childCount;

    private int infantCount;

    private FlightType flightType;

    private boolean transfers;

    private CabinClass classType;

    public BookmarkRequestDTO() {
    }

    public BookmarkRequestDTO(String userCode, String origin, String destination, int adultCount, int childCount, int infantCount, FlightType flightType, boolean transfers, CabinClass classType) {
        this.userCode = userCode;
        this.origin = origin;
        this.destination = destination;
        this.adultCount = adultCount;
        this.childCount = childCount;
        this.infantCount = infantCount;
        this.flightType = flightType;
        this.transfers = transfers;
        this.classType = classType;
    }

    public BookmarkRequestDTO(String userCode, SearchData searchData) {
        this.userCode = userCode;
        this.origin = searchData.getOrigin().getName();
        this.destination = searchData.getDestination().getName();
        this.adultCount = searchData.getAdultsCount();
        this.childCount = searchData.getChildrenCount();
        this.infantCount = searchData.getInfantsCount();
        this.flightType = searchData.getFlightType();
        this.transfers = searchData.getTransfers();
        this.classType = searchData.getCabinClass();
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

    public CabinClass getClassType() {
        return classType;
    }

    public void setClassType(CabinClass classType) {
        this.classType = classType;
    }
}
