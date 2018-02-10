package se.theslof.skistarstats.model;

import android.arch.persistence.room.*;
import android.arch.persistence.room.Entity;
import android.support.annotation.NonNull;

@Entity(tableName = "latest_season")
public class LatestSeasonStatistics {

    @PrimaryKey
    @NonNull
    private String entityId;

    @ColumnInfo(name = "drop_height_season")
    private Integer dropHeight;

    @ColumnInfo(name = "lift_rides_season")
    private Integer liftRides;

    public Integer getDropHeight() {
        return dropHeight;
    }

    public void setDropHeight(Integer dropHeight) {
        this.dropHeight = dropHeight;
    }

    public Integer getLiftRides() {
        return liftRides;
    }

    public void setLiftRides(Integer liftRides) {
        this.liftRides = liftRides;
    }

    public String getEntityId() {
        return entityId;
    }

    public void setEntityId(String entityId) {
        this.entityId = entityId;
    }

/*
    private Object weeks;
    private Integer kcal;
    private Integer pinCount;
    private Integer liftCount;
    private Integer dayCount;
    private Integer destinationCount;
    private Season season;

    public Season getSeason() {
        return season;
    }

    public void setSeason(Season season) {
        this.season = season;
    }


    public Object getWeeks() {
        return weeks;
    }

    public void setWeeks(Object weeks) {
        this.weeks = weeks;
    }

    public Integer getKcal() {
        return kcal;
    }

    public void setKcal(Integer kcal) {
        this.kcal = kcal;
    }

    public Integer getPinCount() {
        return pinCount;
    }

    public void setPinCount(Integer pinCount) {
        this.pinCount = pinCount;
    }

    public Integer getLiftCount() {
        return liftCount;
    }

    public void setLiftCount(Integer liftCount) {
        this.liftCount = liftCount;
    }

    public Integer getDayCount() {
        return dayCount;
    }

    public void setDayCount(Integer dayCount) {
        this.dayCount = dayCount;
    }

    public Integer getDestinationCount() {
        return destinationCount;
    }

    public void setDestinationCount(Integer destinationCount) {
        this.destinationCount = destinationCount;
    }
*/
}
