package se.theslof.skistarstats.model;

import android.arch.persistence.room.*;
import android.arch.persistence.room.Entity;
import android.support.annotation.NonNull;

@Entity(tableName = "latest_day")
public class LatestDayStatistics {

    @PrimaryKey
    @NonNull
    private String entityId;

    @ColumnInfo(name = "drop_height_day")
    private Integer dropHeight;

    @ColumnInfo(name = "lift_rides_day")
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
}
