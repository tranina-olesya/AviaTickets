
package ru.vsu.aviatickets.ticketssearch.models.kiwi;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class KiwiResponse {

    @SerializedName("search_params")
    @Expose
    private SearchParams searchParams;
    @SerializedName("data")
    @Expose
    private List<Datum> data = null;
    @SerializedName("search_id")
    @Expose
    private String searchId;

    public KiwiResponse() {
    }

    public KiwiResponse(SearchParams searchParams, List<Datum> data, String searchId) {
        super();
        this.searchParams = searchParams;
        this.data = data;
        this.searchId = searchId;
    }

    public SearchParams getSearchParams() {
        return searchParams;
    }

    public void setSearchParams(SearchParams searchParams) {
        this.searchParams = searchParams;
    }

    public List<Datum> getData() {
        return data;
    }

    public void setData(List<Datum> data) {
        this.data = data;
    }

    public String getSearchId() {
        return searchId;
    }

    public void setSearchId(String searchId) {
        this.searchId = searchId;
    }
}
