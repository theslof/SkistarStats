package se.theslof.skistarstats.sql;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import se.theslof.skistarstats.model.LatestDayStatistics;
import se.theslof.skistarstats.model.LatestSeasonStatistics;
import se.theslof.skistarstats.model.LatestWeekStatistics;
import se.theslof.skistarstats.model.LiftRide;

@Database(entities = {
        LatestDayStatistics.class,
        LatestWeekStatistics.class,
        LatestSeasonStatistics.class,
        LiftRide.class}, version = 1, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {

    private static AppDatabase INSTANCE;

    public abstract LatestDao latestDao();

    public abstract LiftRideDao liftRideDao();

    public static AppDatabase getAppDatabase(Context context) {
        if (INSTANCE == null) {
            INSTANCE =
                    Room.databaseBuilder(context,
                            AppDatabase.class, "skistar-db")
                            .allowMainThreadQueries()
                            .build();
        }
        return INSTANCE;
    }

    public static void destroyInstance() {
        INSTANCE = null;
    }
}