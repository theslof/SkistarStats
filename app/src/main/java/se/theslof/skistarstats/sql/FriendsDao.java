package se.theslof.skistarstats.sql;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

import se.theslof.skistarstats.model.Entity;
import se.theslof.skistarstats.model.LiftRide;

@Dao
public interface FriendsDao {
    @Query("SELECT * FROM friends")
    List<Entity> getFriends();

    @Query("DELETE FROM friends")
    void clear();

    @Insert
    void insertAll(Entity... friends);
}
