package se.theslof.skistarstats.sql;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

import se.theslof.skistarstats.model.LiftRide;

/**
 * Created by theslof on 2018-02-09.
 */

@Dao
public interface LiftRideDao {
    @Query("SELECT * FROM lift_rides WHERE season LIKE :season")
    List<LiftRide> getLiftRides(String season);

    @Query("DELETE FROM lift_rides WHERE season LIKE :season")
    void clear(String season);

    @Insert
    void insertAll(LiftRide... rides);
}
