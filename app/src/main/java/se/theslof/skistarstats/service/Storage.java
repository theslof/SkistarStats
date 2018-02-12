package se.theslof.skistarstats.service;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.preference.PreferenceManager;
import android.util.Log;

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

    public static Storage initialize(MainModel aMainModel) {
        Storage storage = new Storage();
        storage.model = aMainModel;
        storage.database = AppDatabase.getAppDatabase(aMainModel.getContext());

        storage.refresh(false);

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

    private void saveLatest(final LatestDayStatistics day, final LatestWeekStatistics week, final LatestSeasonStatistics season) {
        pushLatest(day, week, season);
        new AsyncTask<Void, Void, Void>(){

            @Override
            protected Void doInBackground(Void... voids) {
                database.latestDao().insert(day);
                database.latestDao().insert(week);
                database.latestDao().insert(season);
                return null;
            }
        }.execute();
    }

    private void saveLiftRides(final List<LiftRide> liftRides) {
        for (LiftRide ride : liftRides) {
            ride.setSeason(model.getSeason());
        }
        pushRides(liftRides);
        SharedPreferences.Editor editor = PreferenceManager.getDefaultSharedPreferences(model.getContext()).edit();
        editor.putString(model.getContext().getResources().getString(R.string.latest_run), liftRides.get(0).getDate()).apply();
        new AsyncTask<Void, Void, Void>(){

            @Override
            protected Void doInBackground(Void... voids) {
                database.liftRideDao().clear(model.getSeason());
                database.liftRideDao().insertAll(liftRides.toArray(new LiftRide[]{}));
                return null;
            }
        }.execute();
    }

    private void saveFriends(final List<Entity> friends) {
        pushFriends(friends);

        new AsyncTask<Void, Void, Void>(){

            @Override
            protected Void doInBackground(Void... voids) {
                database.friendsDao().clear();
                database.friendsDao().insertAll(friends.toArray(new Entity[]{}));
                return null;
            }
        }.execute();
    }

    public void refresh(boolean fromServer) {
        Context context = model.getContext();

        if(!fromServer){
            new GetFromSQLite().execute(this);
            return;
        }

        // Fetch latest data from Skistar servers

        final SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        final String skierId = sharedPreferences.getString(SettingsActivity.PREF_SKIER_ID, "3206");
        final String season = sharedPreferences.getString(SettingsActivity.PREF_SEASON,
                context.getResources().getString(R.string.default_skier));

        ConnectivityManager cm =
                (ConnectivityManager) model.getContext().getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        boolean isConnected = activeNetwork != null &&
                activeNetwork.isConnectedOrConnecting();
        if (!isConnected) {
            model.setRefreshing(false);
            return;
        }

        if(model.isRefreshing())
            return;

        model.setRefreshing(true);
        model.setSkierId(skierId);
        model.setSeason(Integer.parseInt(season));
        model.setRefreshing(false);

        APIClient.getSkistarService().latestStatistics(model.getSkierId()).enqueue(new Callback<Latest>() {
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

        APIClient.getSkistarService().liftRides(model.getSkierId(), "" + season).enqueue(new Callback<List<LiftRide>>() {
            @Override
            public void onResponse(Call<List<LiftRide>> call, Response<List<LiftRide>> response) {
                saveLiftRides(response.body());
            }

            @Override
            public void onFailure(Call<List<LiftRide>> call, Throwable t) {

            }
        });

        APIClient.getSkistarService().leaderboard(model.getSkierId()).enqueue(new Callback<Leaderboard>() {
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

    private static class GetFromSQLite extends AsyncTask<Storage, Void, LatestDayStatistics>{

        @Override
        protected LatestDayStatistics doInBackground(Storage... storages) {
            if(storages.length < 1)
                return null;
            Storage storage = storages[0];
            AppDatabase database = storage.database;
            MainModel model = storage.model;

            LatestDayStatistics latestDay = database.latestDao().getLatestDay(model.getSkierId());
            LatestWeekStatistics latestWeek = database.latestDao().getLatestWeek(model.getSkierId());
            LatestSeasonStatistics latestSeason = database.latestDao().getLatestSeason(model.getSkierId());
            List<LiftRide> rides = database.liftRideDao().getLiftRides(model.getSeason());
            List<Entity> friends = database.friendsDao().getFriends();

            if (latestDay == null ||
                    latestWeek == null ||
                    latestSeason == null ||
                    friends == null ||
                    rides == null)
                storage.refresh(true);
            else {
                storage.pushLatest(latestDay, latestWeek, latestSeason);
                storage.pushRides(rides);
                storage.pushFriends(friends);
            }

            return latestDay;

        }
    }
}
