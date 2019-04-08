
package ru.vsu.aviatickets.ticketssearch.models.kiwi;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SearchParams {

    @SerializedName("to_type")
    @Expose
    private String toType;
    @SerializedName("flyFrom_type")
    @Expose
    private String flyFromType;
    @SerializedName("seats")
    @Expose
    private Seats seats;

    public SearchParams() {
    }

    public SearchParams(String toType, String flyFromType, Seats seats) {
        super();
        this.toType = toType;
        this.flyFromType = flyFromType;
        this.seats = seats;
    }

    public String getToType() {
        return toType;
    }

    public void setToType(String toType) {
        this.toType = toType;
    }

    public String getFlyFromType() {
        return flyFromType;
    }

    public void setFlyFromType(String flyFromType) {
        this.flyFromType = flyFromType;
    }

    public Seats getSeats() {
        return seats;
    }

    public void setSeats(Seats seats) {
        this.seats = seats;
    }

}
