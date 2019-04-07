package ru.vsu.aviatickets.ticketssearch.models.skyscanner;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import ru.vsu.aviatickets.ticketssearch.models.Place;

public class SkyScannerPlace extends Place {
    @SerializedName("Id")
    @Expose
    private Integer id;

    public SkyScannerPlace(Integer id, String code, String type, String name) {
        super(code, type, name);
        this.id = id;
    }

    public SkyScannerPlace() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
