package se.theslof.skistarstats.viewmodel;

import android.content.Context;
import android.content.SharedPreferences;
import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.preference.PreferenceManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import se.theslof.skistarstats.BR;
import se.theslof.skistarstats.R;
import se.theslof.skistarstats.activity.SettingsActivity;
import se.theslof.skistarstats.adapter.LiftRidesListAdapter;
import se.theslof.skistarstats.model.Latest;
import se.theslof.skistarstats.model.LatestDayStatistics;
import se.theslof.skistarstats.model.LatestSeasonStatistics;
import se.theslof.skistarstats.model.LatestWeekStatistics;
import se.theslof.skistarstats.model.LiftRide;
import se.theslof.skistarstats.service.APIClient;

/**
 * Created by theslof on 2018-01-30.
 */

public class MainModel extends BaseObservable {
    private String skierId = "";
    private int season;
    private int dropHeightDay;
    private int runCountDay;
    private int dropHeightWeek;
    private int runCountWeek;
    private int dropHeightSeason;
    private int runCountSeason;
    private boolean refreshing = false;
    private Context context;
    public final List<LiftRide> liftRides;
    public final RecyclerView.Adapter adapter;

    public MainModel(Context context) {
        this.context = context;
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);

        skierId = sharedPreferences.getString(SettingsActivity.PREF_SKIER_ID, "3206");
        season = Integer.parseInt(sharedPreferences.getString(SettingsActivity.PREF_SEASON, "13"));
        liftRides = new ArrayList<>();
        adapter = new LiftRidesListAdapter(liftRides);
        refresh();
    }

    @Bindable
    public String getSkierId() {
        return skierId;
    }

    @Bindable
    public String getDropHeightDay() {
        return "" + dropHeightDay;
    }

    @Bindable
    public boolean isRefreshing() {
        return refreshing;
    }

    @Bindable
    public String getRunCountDay() {
        return "" + runCountDay;
    }

    @Bindable
    public String getSeason() {
        return "" + season;
    }

    @Bindable
    public String getDropHeightWeek() {
        return "" + dropHeightWeek;
    }

    @Bindable
    public String getRunCountWeek() {
        return "" + runCountWeek;
    }

    @Bindable
    public String getDropHeightSeason() {
        return "" + dropHeightSeason;
    }

    @Bindable
    public String getRunCountSeason() {
        return "" + runCountSeason;
    }

    public void setRefreshing(boolean refreshing) {
        this.refreshing = refreshing;
        notifyPropertyChanged(BR.refreshing);
    }

    public void setDropHeightDay(int dropHeight) {
        this.dropHeightDay = dropHeight;
        notifyPropertyChanged(BR.dropHeightDay);
    }

    public void setRunCountDay(int runCount) {
        this.runCountDay = runCount;
        notifyPropertyChanged(BR.runCountDay);
    }

    public void setDropHeightWeek(int dropHeightWeek) {
        this.dropHeightWeek = dropHeightWeek;
        notifyPropertyChanged(BR.dropHeightWeek);
    }

    public void setRunCountWeek(int runCountWeek) {
        this.runCountWeek = runCountWeek;
        notifyPropertyChanged(BR.runCountWeek);
    }

    public void setDropHeightSeason(int dropHeightSeason) {
        this.dropHeightSeason = dropHeightSeason;
        notifyPropertyChanged(BR.dropHeightSeason);
    }

    public void setRunCountSeason(int runCountSeason) {
        this.runCountSeason = runCountSeason;
        notifyPropertyChanged(BR.runCountSeason);
    }

    public void setSkierId(String skierId) {
        this.skierId = skierId;
        notifyPropertyChanged(BR.skierId);
    }

    public void setSeason(int season) {
        this.season = season;
        notifyPropertyChanged(BR.season);
    }

    // -- Retrofit --

    public void refresh() {
        ConnectivityManager cm =
                (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        boolean isConnected = activeNetwork != null &&
                activeNetwork.isConnectedOrConnecting();

        if (!isConnected || isRefreshing())
            return;

        setRefreshing(true);
        final SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        setSkierId(sharedPreferences.getString(context.getResources()
                        .getString(R.string.skier_id_pref),
                context.getResources().getString(R.string.default_skier)));
        setSeason(Integer.parseInt(sharedPreferences.getString(context.getResources().getString(R.string.season_pref), "13")));

        APIClient.getSkistarService().latestStatistics(skierId).enqueue(new Callback<Latest>() {
            @Override
            public void onResponse(Call<Latest> call, Response<Latest> response) {
                LatestDayStatistics dayStatistics = response.body().getLatestDayStatistics();
                LatestWeekStatistics weekStatistics = response.body().getLatestWeekStatistics();
                LatestSeasonStatistics seasonStatistics = response.body().getLatestSeasonStatistics();
                setDropHeightDay(dayStatistics.getDropHeight());
                setRunCountDay(dayStatistics.getLiftRides());
                setDropHeightWeek(weekStatistics.getDropHeight());
                setRunCountWeek(weekStatistics.getLiftRides());
                setDropHeightSeason(seasonStatistics.getDropHeight());
                setRunCountSeason(seasonStatistics.getLiftRides());
                setRefreshing(false);
            }

            @Override
            public void onFailure(Call<Latest> call, Throwable t) {
                setRefreshing(false);
            }
        });

        APIClient.getSkistarService().liftRides(skierId, "" + season).enqueue(new Callback<List<LiftRide>>() {
            @Override
            public void onResponse(Call<List<LiftRide>> call, Response<List<LiftRide>> response) {
                liftRides.clear();
                liftRides.addAll(response.body());
                adapter.notifyDataSetChanged();
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString(context.getResources().getString(R.string.latest_run), liftRides.get(0).getDate()).apply();

            }

            @Override
            public void onFailure(Call<List<LiftRide>> call, Throwable t) {

            }
        });

    }
}
