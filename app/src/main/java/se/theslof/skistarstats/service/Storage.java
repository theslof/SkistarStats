package se.theslof.skistarstats.service;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.preference.PreferenceManager;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import se.theslof.skistarstats.R;
import se.theslof.skistarstats.activity.SettingsActivity;
import se.theslof.skistarstats.model.Entity;
import se.theslof.skistarstats.model.Entry;
import se.theslof.skistarstats.model.Latest;
import se.theslof.skistarstats.model.LatestDayStatistics;
import se.theslof.skistarstats.model.LatestSeasonStatistics;
import se.theslof.skistarstats.model.LatestWeekStatistics;
import se.theslof.skistarstats.model.Leaderboard;
import se.theslof.skistarstats.model.LiftRide;
import se.theslof.skistarstats.sql.AppDatabase;
import se.theslof.skistarstats.viewmodel.MainModel;

public final class Storage {
    private AppDatabase database;
    private MainModel model;

    private Storage() {
    }

    public static Storage initialize(MainModel mainModel) {
        Storage storage = new Storage();
        storage.model = mainModel;
        storage.database = AppDatabase.getAppDatabase(mainModel.getContext());

        LatestDayStatistics latestDay = storage.database.latestDao().getLatestDay(mainModel.getSkierId());
        LatestWeekStatistics latestWeek = storage.database.latestDao().getLatestWeek(mainModel.getSkierId());
        LatestSeasonStatistics latestSeason = storage.database.latestDao().getLatestSeason(mainModel.getSkierId());

        List<LiftRide> rides = storage.database.liftRideDao().getLiftRides(mainModel.getSeason());

        if (latestDay == null ||
                latestWeek == null ||
                latestSeason == null ||
                rides == null)
            storage.refresh();
        else {
            storage.pushLatest(latestDay, latestWeek, latestSeason);
            storage.pushRides(rides);
        }

        return storage;
    }

    private void pushLatest(LatestDayStatistics day, LatestWeekStatistics week, LatestSeasonStatistics season) {
        model.setLatestDay(day);
        model.setLatestWeek(week);
        model.setLatestSeason(season);
    }

    private void pushRides(List<LiftRide> rides) {
        model.setLiftRides(rides);
    }

    private void pushFriends(List<Entity> friends) {
        model.setFriendList(friends);
    }

    private void saveLatest(LatestDayStatistics day, LatestWeekStatistics week, LatestSeasonStatistics season) {
        pushLatest(day, week, season);
        database.latestDao().insert(day);
        database.latestDao().insert(week);
        database.latestDao().insert(season);
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

    private void saveFriends(List<Entity> friends) {
        pushFriends(friends);
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
                saveLatest(response.body().getLatestDayStatistics(),
                        response.body().getLatestWeekStatistics(),
                        response.body().getLatestSeasonStatistics());
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

        APIClient.getSkistarService().leaderboard(skierId).enqueue(new Callback<Leaderboard>() {
            @Override
            public void onResponse(Call<Leaderboard> call, Response<Leaderboard> response) {
                List<Entity> friends = new ArrayList<>();
                for (Entry e : response.body().getEntries()) {
                    friends.add(e.getEntity());
                }
                saveFriends(friends);
            }

            @Override
            public void onFailure(Call<Leaderboard> call, Throwable t) {

            }
        });
    }
}
