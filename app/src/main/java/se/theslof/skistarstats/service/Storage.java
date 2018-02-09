package se.theslof.skistarstats.service;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.preference.PreferenceManager;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import se.theslof.skistarstats.R;
import se.theslof.skistarstats.activity.SettingsActivity;
import se.theslof.skistarstats.model.Latest;
import se.theslof.skistarstats.model.LiftRide;
import se.theslof.skistarstats.sql.AppDatabase;
import se.theslof.skistarstats.sql.LatestEntity;
import se.theslof.skistarstats.viewmodel.MainModel;

/**
 * Created by theslof on 2018-02-09.
 */

public final class Storage {
    private AppDatabase database;
    private MainModel model;

    private Storage() {
    }

    public static Storage initialize(MainModel mainModel) {
        Storage storage = new Storage();
        storage.model = mainModel;
        storage.database = AppDatabase.getAppDatabase(mainModel.getContext());

        LatestEntity latest = storage.database.latestDao().getLatest();
        List<LiftRide> rides = storage.database.liftRideDao().getLiftRides(mainModel.getSeason());

        if (latest == null || rides == null)
            storage.refresh();
        else {
            storage.pushLatest(latest);
            storage.pushRides(rides);
        }

        return storage;
    }

    private void pushLatest(LatestEntity latest) {
        model.setDropHeightDay(latest.getDayDropHeight());
        model.setRunCountDay(latest.getDayLiftRides());
        model.setDropHeightWeek(latest.getWeekDropHeight());
        model.setRunCountWeek(latest.getWeekLiftRides());
        model.setDropHeightSeason(latest.getSeasonDropHeight());
        model.setRunCountSeason(latest.getSeasonLiftRides());
    }

    private void pushRides(List<LiftRide> rides) {
        model.setLiftRides(rides);
    }

    private void saveLatest(final LatestEntity entity) {
        pushLatest(entity);
        database.latestDao().clear();
        database.latestDao().insert(entity);
    }

    private void saveLiftRides(List<LiftRide> liftRides) {
        for (LiftRide ride : liftRides) {
            ride.setSeason(model.getSeason());
        }
        pushRides(liftRides);
        SharedPreferences.Editor editor = PreferenceManager.getDefaultSharedPreferences(model.getContext()).edit();
        editor.putString(model.getContext().getResources().getString(R.string.latest_run), liftRides.get(0).getDate()).apply();
        database.liftRideDao().clear(model.getSeason());
        database.liftRideDao().insertAll(liftRides.toArray(new LiftRide[]{}));
    }

    public void refresh() {
        Context context = model.getContext();
        final SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        final String skierId = sharedPreferences.getString(SettingsActivity.PREF_SKIER_ID, "3206");
        final String season = sharedPreferences.getString(SettingsActivity.PREF_SEASON,
                context.getResources().getString(R.string.default_skier));

        ConnectivityManager cm =
                (ConnectivityManager) model.getContext().getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        boolean isConnected = activeNetwork != null &&
                activeNetwork.isConnectedOrConnecting();

        if (!isConnected || model.isRefreshing())
            return;

        model.setRefreshing(true);
        model.setSkierId(skierId);
        model.setSeason(Integer.parseInt(season));
        model.setRefreshing(false);

        APIClient.getSkistarService().latestStatistics(skierId).enqueue(new Callback<Latest>() {
            @Override
            public void onResponse(Call<Latest> call, Response<Latest> response) {
                saveLatest(LatestEntity.makeEntity(response.body()));
            }

            @Override
            public void onFailure(Call<Latest> call, Throwable t) {
                model.setRefreshing(false);
            }
        });

        APIClient.getSkistarService().liftRides(skierId, "" + season).enqueue(new Callback<List<LiftRide>>() {
            @Override
            public void onResponse(Call<List<LiftRide>> call, Response<List<LiftRide>> response) {
                saveLiftRides(response.body());
            }

            @Override
            public void onFailure(Call<List<LiftRide>> call, Throwable t) {

            }
        });
    }
}
