package se.theslof.skistarstats.sql;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

import se.theslof.skistarstats.model.Latest;

/**
 * Created by theslof on 2018-02-09.
 */

@Entity(tableName = "latest")
public class LatestEntity {
    @PrimaryKey(autoGenerate = true)
    private int latestid;

    @ColumnInfo(name = "day_drop_height")
    private Integer dayDropHeight;

    @ColumnInfo(name = "day_lift_rides")
    private Integer dayLiftRides;

    @ColumnInfo(name = "week_drop_height")
    private Integer weekDropHeight;

    @ColumnInfo(name = "week_lift_rides")
    private Integer weekLiftRides;

    @ColumnInfo(name = "season_drop_height")
    private Integer seasonDropHeight;

    @ColumnInfo(name = "season_lift_rides")
    private Integer seasonLiftRides;

    public static LatestEntity makeEntity(Latest latest){
        LatestEntity entity = new LatestEntity();
        entity.setDayDropHeight(latest.getLatestDayStatistics().getDropHeight());
        entity.setDayLiftRides(latest.getLatestDayStatistics().getLiftRides());
        entity.setWeekDropHeight(latest.getLatestWeekStatistics().getDropHeight());
        entity.setWeekLiftRides(latest.getLatestWeekStatistics().getLiftRides());
        entity.setSeasonDropHeight(latest.getLatestSeasonStatistics().getDropHeight());
        entity.setSeasonLiftRides(latest.getLatestSeasonStatistics().getLiftRides());
        return entity;
    }

    public int getLatestid() {
        return latestid;
    }

    public void setLatestid(int latestid) {
        this.latestid = latestid;
    }

    public Integer getDayDropHeight() {
        return dayDropHeight;
    }

    public void setDayDropHeight(Integer dayDropHeight) {
        this.dayDropHeight = dayDropHeight;
    }

    public Integer getDayLiftRides() {
        return dayLiftRides;
    }

    public void setDayLiftRides(Integer dayLiftRides) {
        this.dayLiftRides = dayLiftRides;
    }

    public Integer getWeekDropHeight() {
        return weekDropHeight;
    }

    public void setWeekDropHeight(Integer weekDropHeight) {
        this.weekDropHeight = weekDropHeight;
    }

    public Integer getWeekLiftRides() {
        return weekLiftRides;
    }

    public void setWeekLiftRides(Integer weekLiftRides) {
        this.weekLiftRides = weekLiftRides;
    }

    public Integer getSeasonDropHeight() {
        return seasonDropHeight;
    }

    public void setSeasonDropHeight(Integer seasonDropHeight) {
        this.seasonDropHeight = seasonDropHeight;
    }

    public Integer getSeasonLiftRides() {
        return seasonLiftRides;
    }

    public void setSeasonLiftRides(Integer seasonLiftRides) {
        this.seasonLiftRides = seasonLiftRides;
    }
}
