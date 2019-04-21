
package ru.vsu.aviatickets.ticketssearch.models.kiwi;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Duration {

    @SerializedName("total")
    @Expose
    private Integer total;
    @SerializedName("return")
    @Expose
    private Integer _return;
    @SerializedName("departure")
    @Expose
    private Integer departure;

    public Duration() {
    }

    public Duration(Integer total, Integer _return, Integer departure) {
        super();
        this.total = total;
        this._return = _return;
        this.departure = departure;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public Integer getReturn() {
        return _return;
    }

    public void setReturn(Integer _return) {
        this._return = _return;
    }

    public Integer getDeparture() {
        return departure;
    }

    public void setDeparture(Integer departure) {
        this.departure = departure;
    }

}
