package ru.vsu.aviatickets.api.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SkyScannerPlace {
    @SerializedName("PlaceId")
    @Expose
    private String placeId;
    @SerializedName("PlaceName")
    @Expose
    private String placeName;
    @SerializedName("CountryId")
    @Expose
    private String countryId;
    @SerializedName("RegionId")
    @Expose
    private String regionId;
    @SerializedName("CityId")
    @Expose
    private String cityId;
    @SerializedName("CountryName")
    @Expose
    private String countryName;

    public SkyScannerPlace() {
    }

    public String getPlaceId() {
        return placeId;
    }

    public void setPlaceId(String placeId) {
        this.placeId = placeId;
    }

    public String getPlaceName() {
        return placeName;
    }

    public void setPlaceName(String placeName) {
        this.placeName = placeName;
    }

    public String getCountryId() {
        return countryId;
    }

    public void setCountryId(String countryId) {
        this.countryId = countryId;
    }

    public String getRegionId() {
        return regionId;
    }

    public void setRegionId(String regionId) {
        this.regionId = regionId;
    }

    public String getCityId() {
        return cityId;
    }

    public void setCityId(String cityId) {
        this.cityId = cityId;
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }
}
