package se.theslof.skistarstats.model;

import android.arch.persistence.room.*;
import android.arch.persistence.room.Entity;

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
}