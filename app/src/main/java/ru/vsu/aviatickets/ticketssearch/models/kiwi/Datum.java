
package ru.vsu.aviatickets.ticketssearch.models.kiwi;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Datum {

    @SerializedName("quality")
    @Expose
    private Double quality;
    @SerializedName("flyTo")
    @Expose
    private String flyTo;
    @SerializedName("deep_link")
    @Expose
    private String deepLink;
    @SerializedName("airlines")
    @Expose
    private List<String> airlines = null;
    @SerializedName("pnr_count")
    @Expose
    private Integer pnrCount;
    @SerializedName("fly_duration")
    @Expose
    private String flyDuration;
    @SerializedName("return_duration")
    @Expose
    private String returnDuration;
    @SerializedName("has_airport_change")
    @Expose
    private Boolean hasAirportChange;
    @SerializedName("distance")
    @Expose
    private Double distance;
    @SerializedName("type_flights")
    @Expose
    private List<String> typeFlights = null;

    @SerializedName("flyFrom")
    @Expose
    private String flyFrom;
    @SerializedName("dTime")
    @Expose
    private Long dTime;
    @SerializedName("cityFrom")
    @Expose
    private String cityFrom;

    @SerializedName("duration")
    @Expose
    private Duration duration;
    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("countryTo")
    @Expose
    private Country countryTo;
    @SerializedName("price")
    @Expose
    private Double price;
    @SerializedName("routes")
    @Expose
    private List<List<String>> routes = null;
    @SerializedName("cityTo")
    @Expose
    private String cityTo;
    @SerializedName("transfers")
    @Expose
    private List<Object> transfers = null;
    @SerializedName("route")
    @Expose
    private List<Route> route = null;
    @SerializedName("aTime")
    @Expose
    private Long aTime;
    @SerializedName("countryFrom")
    @Expose
    private Country countryFrom;


    public Datum() {
    }

    public Datum(Double quality, String flyTo, String deepLink, List<String> airlines, Integer pnrCount, String flyDuration, String returnDuration, Boolean hasAirportChange, Double distance, List<String> typeFlights, String flyFrom, Long dTime, String cityFrom, Duration duration, String id, Country countryTo, Double price, List<List<String>> routes, String cityTo, List<Object> transfers, List<Route> route, Long aTime, Country countryFrom) {
        this.quality = quality;
        this.flyTo = flyTo;
        this.deepLink = deepLink;
        this.airlines = airlines;
        this.pnrCount = pnrCount;
        this.flyDuration = flyDuration;
        this.returnDuration = returnDuration;
        this.hasAirportChange = hasAirportChange;
        this.distance = distance;
        this.typeFlights = typeFlights;
        this.flyFrom = flyFrom;
        this.dTime = dTime;
        this.cityFrom = cityFrom;
        this.duration = duration;
        this.id = id;
        this.countryTo = countryTo;
        this.price = price;
        this.routes = routes;
        this.cityTo = cityTo;
        this.transfers = transfers;
        this.route = route;
        this.aTime = aTime;
        this.countryFrom = countryFrom;
    }

    public Double getQuality() {
        return quality;
    }

    public void setQuality(Double quality) {
        this.quality = quality;
    }

    public String getFlyTo() {
        return flyTo;
    }

    public void setFlyTo(String flyTo) {
        this.flyTo = flyTo;
    }

    public String getDeepLink() {
        return deepLink;
    }

    public void setDeepLink(String deepLink) {
        this.deepLink = deepLink;
    }

    public List<String> getAirlines() {
        return airlines;
    }

    public void setAirlines(List<String> airlines) {
        this.airlines = airlines;
    }

    public Integer getPnrCount() {
        return pnrCount;
    }

    public void setPnrCount(Integer pnrCount) {
        this.pnrCount = pnrCount;
    }

    public String getFlyDuration() {
        return flyDuration;
    }

    public void setFlyDuration(String flyDuration) {
        this.flyDuration = flyDuration;
    }

    public Boolean getHasAirportChange() {
        return hasAirportChange;
    }

    public void setHasAirportChange(Boolean hasAirportChange) {
        this.hasAirportChange = hasAirportChange;
    }

    public Double getDistance() {
        return distance;
    }

    public void setDistance(Double distance) {
        this.distance = distance;
    }

    public List<String> getTypeFlights() {
        return typeFlights;
    }

    public void setTypeFlights(List<String> typeFlights) {
        this.typeFlights = typeFlights;
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

    public String getCityFrom() {
        return cityFrom;
    }

    public void setCityFrom(String cityFrom) {
        this.cityFrom = cityFrom;
    }

    public Duration getDuration() {
        return duration;
    }

    public void setDuration(Duration duration) {
        this.duration = duration;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Country getCountryTo() {
        return countryTo;
    }

    public void setCountryTo(Country countryTo) {
        this.countryTo = countryTo;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public List<List<String>> getRoutes() {
        return routes;
    }

    public void setRoutes(List<List<String>> routes) {
        this.routes = routes;
    }

    public String getCityTo() {
        return cityTo;
    }

    public void setCityTo(String cityTo) {
        this.cityTo = cityTo;
    }

    public List<Object> getTransfers() {
        return transfers;
    }

    public void setTransfers(List<Object> transfers) {
        this.transfers = transfers;
    }

    public List<Route> getRoute() {
        return route;
    }

    public void setRoute(List<Route> route) {
        this.route = route;
    }

    public Long getATime() {
        return aTime;
    }

    public void setATime(Long aTime) {
        this.aTime = aTime;
    }

    public Country getCountryFrom() {
        return countryFrom;
    }

    public void setCountryFrom(Country countryFrom) {
        this.countryFrom = countryFrom;
    }

    public String getReturnDuration() {
        return returnDuration;
    }

    public void setReturnDuration(String returnDuration) {
        this.returnDuration = returnDuration;
    }
}
