package se.theslof.skistarstats.viewmodel;

import android.content.Context;
import android.content.SharedPreferences;
import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.preference.PreferenceManager;
import android.support.v7.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;
import se.theslof.skistarstats.BR;
import se.theslof.skistarstats.activity.SettingsActivity;
import se.theslof.skistarstats.adapter.LiftRidesListAdapter;
import se.theslof.skistarstats.model.LiftRide;
import se.theslof.skistarstats.service.Storage;

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
    private final Storage storage;

    public MainModel(Context context) {
        this.context = context;
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);

        skierId = sharedPreferences.getString(SettingsActivity.PREF_SKIER_ID, "3206");
        season = Integer.parseInt(sharedPreferences.getString(SettingsActivity.PREF_SEASON, "13"));
        liftRides = new ArrayList<>();
        adapter = new LiftRidesListAdapter(liftRides);
        storage = Storage.initialize(this);
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

    public Context getContext() {
        return context;
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

    public void setLiftRides(List<LiftRide> rides) {
        liftRides.clear();
        liftRides.addAll(rides);
        adapter.notifyDataSetChanged();
    }

    // -- Retrofit --

    public void refresh() {
        storage.refresh();
    }
}
