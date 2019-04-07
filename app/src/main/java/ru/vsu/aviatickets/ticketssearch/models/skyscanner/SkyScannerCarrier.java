package ru.vsu.aviatickets.ticketssearch.models.skyscanner;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import ru.vsu.aviatickets.ticketssearch.models.Carrier;

public class SkyScannerCarrier extends Carrier {
    @SerializedName("Id")
    @Expose
    private Integer id;
    public SkyScannerCarrier(Integer id, String code, String name, String imageUrl) {
        super(code, name, imageUrl);
        this.id = id;
    }

    public SkyScannerCarrier() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
