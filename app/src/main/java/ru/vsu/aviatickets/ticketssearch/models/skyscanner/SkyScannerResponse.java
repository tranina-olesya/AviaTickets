package ru.vsu.aviatickets.ticketssearch.models.skyscanner;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SkyScannerResponse {
    @SerializedName("SessionKey")
    @Expose
    private String SessionKey;
}
