package se.theslof.skistarstats.model;

import android.arch.persistence.room.*;
import android.arch.persistence.room.Entity;

/**
 * Created by Martin on 2018-01-25.
 */

@Entity(tableName = "lift_rides")
public class LiftRide {
    @PrimaryKey(autoGenerate = true)
    private int rideid;

    @ColumnInfo(name = "date")
    private String date;

    @ColumnInfo(name = "season")
    private String season;

    @ColumnInfo(name = "lift_name")
    private String liftName;

    public int getRideid() {
        return rideid;
    }

    public void setRideid(int rideid) {
        this.rideid = rideid;
    }

    public String getSeason() {
        return season;
    }

    public void setSeason(String season) {
        this.season = season;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getLiftName() {
        return liftName;
    }

    public void setLiftName(String liftName) {
        this.liftName = liftName;
    }
/*
    @Embedded
    private Destination destination;

    public Destination getDestination() {
        return destination;
    }

    public void setDestination(Destination destination) {
        this.destination = destination;
    }

    @ColumnInfo(name = "drop_height")
    private Integer dropHeight;

    @ColumnInfo(name = "timestamp")
    private String timestamp;


    public Integer getDropHeight() {
        return dropHeight;
    }

    public void setDropHeight(Integer dropHeight) {
        this.dropHeight = dropHeight;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }
*/
}