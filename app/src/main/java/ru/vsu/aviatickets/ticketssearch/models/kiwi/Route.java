
package ru.vsu.aviatickets.ticketssearch.models.kiwi;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Route {

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
    @SerializedName("dTime")
    @Expose
    private Long dTime;
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
    private Long aTime;

    public Route() {
    }

    public Route(Integer _return, Integer flightNo, Integer price, Integer originalReturn, String operatingCarrier, String cityTo, String flyFrom, Long dTime,  String flyTo, String airline, String cityFrom, Long aTime) {
        super();
        this._return = _return;
        this.flightNo = flightNo;
        this.price = price;
        this.originalReturn = originalReturn;
        this.operatingCarrier = operatingCarrier;
        this.cityTo = cityTo;
        this.flyFrom = flyFrom;
        this.dTime = dTime;
        this.flyTo = flyTo;
        this.airline = airline;
        this.cityFrom = cityFrom;
        this.aTime = aTime;
    }

    public Integer getReturn() {
        return _return;
    }

    public void setReturn(Integer _return) {
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

    public Long getDTime() {
        return dTime;
    }

    public void setDTime(Long dTime) {
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

    public Long getATime() {
        return aTime;
    }

    public void setATime(Long aTime) {
        this.aTime = aTime;
    }
}
