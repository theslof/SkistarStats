package se.theslof.skistarstats.sql;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

/**
 * Created by theslof on 2018-02-09.
 */

@Dao
public interface LatestDao {
    @Query("SELECT * FROM latest ORDER BY latestid DESC LIMIT 1")
    LatestEntity getLatest();

    @Query("DELETE FROM latest")
    void clear();

    @Insert
    void insert(LatestEntity entity);
}
