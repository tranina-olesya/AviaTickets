package ru.vsu.aviatickets.bookmarks.entity;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

import java.util.Objects;

@Entity
public class BookmarkRoute {

    @PrimaryKey
    private Long id;
    private String origin;
    private String destination;
    private int adultCount;
    private int childCount;
    private int infantCount;
    private String flightType;
    private boolean transfers;
    private String classType;

    public BookmarkRoute(String origin, String destination, int adultCount, int childCount, int infantCount, String flightType, boolean transfers, String classType) {
        this.origin = origin;
        this.destination = destination;
        this.adultCount = adultCount;
        this.childCount = childCount;
        this.infantCount = infantCount;
        this.flightType = flightType;
        this.transfers = transfers;
        this.classType = classType;
    }

    public String getFlightType() {
        return flightType;
    }

    public void setFlightType(String flightType) {
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

    public String getClassType() {
        return classType;
    }

    public void setClassType(String classType) {
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
}
