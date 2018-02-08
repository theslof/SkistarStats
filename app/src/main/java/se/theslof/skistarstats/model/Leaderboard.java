
package se.theslof.skistarstats.model;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Leaderboard {

    @SerializedName("destination")
    @Expose
    private Destination destination;
    @SerializedName("leaderboardType")
    @Expose
    private LeaderboardType leaderboardType;
    @SerializedName("leaderboardPeriodType")
    @Expose
    private LeaderboardPeriodType leaderboardPeriodType;
    @SerializedName("entries")
    @Expose
    private List<Entry> entries = null;
    @SerializedName("periodValue")
    @Expose
    private String periodValue;
    @SerializedName("startDate")
    @Expose
    private String startDate;
    @SerializedName("currentEntityEntry")
    @Expose
    private Object currentEntityEntry;
    @SerializedName("positionDiffText")
    @Expose
    private Object positionDiffText;
    @SerializedName("positionDiff")
    @Expose
    private Integer positionDiff;

    public Destination getDestination() {
        return destination;
    }

    public void setDestination(Destination destination) {
        this.destination = destination;
    }

    public LeaderboardType getLeaderboardType() {
        return leaderboardType;
    }

    public void setLeaderboardType(LeaderboardType leaderboardType) {
        this.leaderboardType = leaderboardType;
    }

    public LeaderboardPeriodType getLeaderboardPeriodType() {
        return leaderboardPeriodType;
    }

    public void setLeaderboardPeriodType(LeaderboardPeriodType leaderboardPeriodType) {
        this.leaderboardPeriodType = leaderboardPeriodType;
    }

    public List<Entry> getEntries() {
        return entries;
    }

    public void setEntries(List<Entry> entries) {
        this.entries = entries;
    }

    public String getPeriodValue() {
        return periodValue;
    }

    public void setPeriodValue(String periodValue) {
        this.periodValue = periodValue;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public Object getCurrentEntityEntry() {
        return currentEntityEntry;
    }

    public void setCurrentEntityEntry(Object currentEntityEntry) {
        this.currentEntityEntry = currentEntityEntry;
    }

    public Object getPositionDiffText() {
        return positionDiffText;
    }

    public void setPositionDiffText(Object positionDiffText) {
        this.positionDiffText = positionDiffText;
    }

    public Integer getPositionDiff() {
        return positionDiff;
    }

    public void setPositionDiff(Integer positionDiff) {
        this.positionDiff = positionDiff;
    }

}
