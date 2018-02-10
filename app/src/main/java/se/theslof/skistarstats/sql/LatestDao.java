package se.theslof.skistarstats.sql;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import se.theslof.skistarstats.model.LatestDayStatistics;
import se.theslof.skistarstats.model.LatestSeasonStatistics;
import se.theslof.skistarstats.model.LatestWeekStatistics;

@Dao
public interface LatestDao {
    @Query("SELECT * FROM latest_day WHERE entityId LIKE :skierid LIMIT 1")
    LatestDayStatistics getLatestDay(String skierid);

    @Query("SELECT * FROM latest_week WHERE entityId LIKE :skierid LIMIT 1")
    LatestWeekStatistics getLatestWeek(String skierid);

    @Query("SELECT * FROM latest_season WHERE entityId LIKE :skierid LIMIT 1")
    LatestSeasonStatistics getLatestSeason(String skierid);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(LatestDayStatistics entity);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(LatestWeekStatistics entity);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(LatestSeasonStatistics entity);
}
