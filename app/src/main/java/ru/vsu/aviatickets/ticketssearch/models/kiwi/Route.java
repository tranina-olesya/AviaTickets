
package ru.vsu.aviatickets.ticketssearch.models.kiwi;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Route {

    @SerializedName("aTimeUTC")
    @Expose
    private Integer aTimeUTC;
    @SerializedName("refresh_timestamp")
    @Expose
    private Integer refreshTimestamp;
    @SerializedName("bags_recheck_required")
    @Expose
    private Boolean bagsRecheckRequired;
    @SerializedName("return")
    @Expose
    private Integer _return;

    @SerializedName("flight_no")
    @Expose
    private Integer flightNo;
    @SerializedName("price")
    @Expose
    private Integer price;
    @SerializedName("original_return")
    @Expose
    private Integer originalReturn;
    @SerializedName("operating_carrier")
    @Expose
    private String operatingCarrier;
    @SerializedName("cityTo")
    @Expose
    private String cityTo;
    @SerializedName("flyFrom")
    @Expose
    private String flyFrom;
    @SerializedName("dTimeUTC")
    @Expose
    private Integer dTimeUTC;

    @SerializedName("dTime")
    @Expose
    private Integer dTime;
    @SerializedName("flyTo")
    @Expose
    private String flyTo;

    @SerializedName("airline")
    @Expose
    private String airline;

    @SerializedName("cityFrom")
    @Expose
    private String cityFrom;

    @SerializedName("aTime")
    @Expose
    private Integer aTime;

    public Route() {
    }

    public Route(Integer aTimeUTC, Integer refreshTimestamp, Boolean bagsRecheckRequired, Integer _return, Integer flightNo, Integer price, Integer originalReturn, String operatingCarrier, String cityTo, String flyFrom, Integer dTimeUTC, Integer dTime,  String flyTo, String airline, String cityFrom, Integer aTime) {
        super();
        this.aTimeUTC = aTimeUTC;
        this.refreshTimestamp = refreshTimestamp;
        this.bagsRecheckRequired = bagsRecheckRequired;
        this._return = _return;
        this.flightNo = flightNo;
        this.price = price;
        this.originalReturn = originalReturn;
        this.operatingCarrier = operatingCarrier;
        this.cityTo = cityTo;
        this.flyFrom = flyFrom;
        this.dTimeUTC = dTimeUTC;
        this.dTime = dTime;
        this.flyTo = flyTo;
        this.airline = airline;
        this.cityFrom = cityFrom;
        this.aTime = aTime;
    }

    public Integer getaTimeUTC() {
        return aTimeUTC;
    }

    public void setaTimeUTC(Integer aTimeUTC) {
        this.aTimeUTC = aTimeUTC;
    }

    public Integer getRefreshTimestamp() {
        return refreshTimestamp;
    }

    public void setRefreshTimestamp(Integer refreshTimestamp) {
        this.refreshTimestamp = refreshTimestamp;
    }

    public Boolean getBagsRecheckRequired() {
        return bagsRecheckRequired;
    }

    public void setBagsRecheckRequired(Boolean bagsRecheckRequired) {
        this.bagsRecheckRequired = bagsRecheckRequired;
    }

    public Integer get_return() {
        return _return;
    }

    public void set_return(Integer _return) {
        this._return = _return;
    }

    public Integer getFlightNo() {
        return flightNo;
    }

    public void setFlightNo(Integer flightNo) {
        this.flightNo = flightNo;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Integer getOriginalReturn() {
        return originalReturn;
    }

    public void setOriginalReturn(Integer originalReturn) {
        this.originalReturn = originalReturn;
    }

    public String getOperatingCarrier() {
        return operatingCarrier;
    }

    public void setOperatingCarrier(String operatingCarrier) {
        this.operatingCarrier = operatingCarrier;
    }

    public String getCityTo() {
        return cityTo;
    }

    public void setCityTo(String cityTo) {
        this.cityTo = cityTo;
    }

    public String getFlyFrom() {
        return flyFrom;
    }

    public void setFlyFrom(String flyFrom) {
        this.flyFrom = flyFrom;
    }

    public Integer getdTimeUTC() {
        return dTimeUTC;
    }

    public void setdTimeUTC(Integer dTimeUTC) {
        this.dTimeUTC = dTimeUTC;
    }

    public Integer getdTime() {
        return dTime;
    }

    public void setdTime(Integer dTime) {
        this.dTime = dTime;
    }

    public String getFlyTo() {
        return flyTo;
    }

    public void setFlyTo(String flyTo) {
        this.flyTo = flyTo;
    }

    public String getAirline() {
        return airline;
    }

    public void setAirline(String airline) {
        this.airline = airline;
    }

    public String getCityFrom() {
        return cityFrom;
    }

    public void setCityFrom(String cityFrom) {
        this.cityFrom = cityFrom;
    }

    public Integer getaTime() {
        return aTime;
    }

    public void setaTime(Integer aTime) {
        this.aTime = aTime;
    }
}
