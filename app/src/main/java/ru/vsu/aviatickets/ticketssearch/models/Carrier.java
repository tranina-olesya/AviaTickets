package ru.vsu.aviatickets.ticketssearch.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Carrier {
    @SerializedName("Id")
    @Expose
    private Integer id;

    @SerializedName("Code")
    @Expose
    private String code;

    @SerializedName("Name")
    @Expose
    private String name;

    @SerializedName("ImageUrl")
    @Expose
    private String imageUrl;

    public Carrier(Integer id, String code, String name, String imageUrl) {
        this.id = id;
        this.code = code;
        this.name = name;
        this.imageUrl = imageUrl;
    }

    public Carrier() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
