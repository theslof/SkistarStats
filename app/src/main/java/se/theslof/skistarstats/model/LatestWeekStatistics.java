package se.theslof.skistarstats.model;

import android.arch.persistence.room.*;
import android.arch.persistence.room.Entity;
import android.support.annotation.NonNull;

import java.util.List;

@Entity(tableName = "latest_week")
public class LatestWeekStatistics {

    @PrimaryKey
    @NonNull
    private String entityId;

    @ColumnInfo(name = "drop_height_week")
    private Integer dropHeight;

    @ColumnInfo(name = "lift_rides_week")
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
    private Week week;
    private List<Day> days = null;
    private Integer kcal;
    private Integer pinCount;
    private Integer liftCount;
    private Integer dayCount;
    private Integer destinationCount;

    public Week getWeek() {
        return week;
    }

    public void setWeek(Week week) {
        this.week = week;
    }

    public List<Day> getDays() {
        return days;
    }

    public void setDays(List<Day> days) {
        this.days = days;
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
