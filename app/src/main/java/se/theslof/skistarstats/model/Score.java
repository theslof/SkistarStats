
package se.theslof.skistarstats.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Score {

    @SerializedName("position")
    @Expose
    private Integer position;
    @SerializedName("value")
    @Expose
    private Integer value;
    @SerializedName("tiebreak")
    @Expose
    private Integer tiebreak;
    @SerializedName("percentageValue")
    @Expose
    private Integer percentageValue;
    @SerializedName("displayValue")
    @Expose
    private String displayValue;
    @SerializedName("unit")
    @Expose
    private Object unit;

    public Integer getPosition() {
        return position;
    }

    public void setPosition(Integer position) {
        this.position = position;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    public Integer getTiebreak() {
        return tiebreak;
    }

    public void setTiebreak(Integer tiebreak) {
        this.tiebreak = tiebreak;
    }

    public Integer getPercentageValue() {
        return percentageValue;
    }

    public void setPercentageValue(Integer percentageValue) {
        this.percentageValue = percentageValue;
    }

    public String getDisplayValue() {
        return displayValue;
    }

    public void setDisplayValue(String displayValue) {
        this.displayValue = displayValue;
    }

    public Object getUnit() {
        return unit;
    }

    public void setUnit(Object unit) {
        this.unit = unit;
    }

}
