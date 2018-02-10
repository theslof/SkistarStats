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
import se.theslof.skistarstats.adapter.FriendsListAdapter;
import se.theslof.skistarstats.adapter.LiftRidesListAdapter;
import se.theslof.skistarstats.model.Entity;
import se.theslof.skistarstats.model.LatestDayStatistics;
import se.theslof.skistarstats.model.LatestSeasonStatistics;
import se.theslof.skistarstats.model.LatestWeekStatistics;
import se.theslof.skistarstats.model.LiftRide;
import se.theslof.skistarstats.service.Storage;

public class MainModel extends BaseObservable {
    private String skierId = "";
    private int season;
    private LatestDayStatistics latestDay = new LatestDayStatistics();
    private LatestWeekStatistics latestWeek = new LatestWeekStatistics();
    private LatestSeasonStatistics latestSeason = new LatestSeasonStatistics();
    private boolean refreshing = false;
    private Context context;
    public final List<LiftRide> liftRides;
    public final List<Entity> friendList;
    public final RecyclerView.Adapter runsAdapter;
    public final RecyclerView.Adapter friendsAdapter;
    private final Storage storage;

    public MainModel(Context context) {
        this.context = context;
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);

        skierId = sharedPreferences.getString(SettingsActivity.PREF_SKIER_ID, "3206");
        season = Integer.parseInt(sharedPreferences.getString(SettingsActivity.PREF_SEASON, "13"));
        liftRides = new ArrayList<>();
        friendList = new ArrayList<>();
        runsAdapter = new LiftRidesListAdapter(liftRides);
        friendsAdapter = new FriendsListAdapter(friendList);
        storage = Storage.initialize(this);
    }

    @Bindable
    public String getSkierId() {
        return skierId;
    }

    @Bindable
    public String getDropHeightDay() {
        return "" + latestDay.getDropHeight();
    }

    @Bindable
    public String getRunCountDay() {
        return "" + latestDay.getLiftRides();
    }

    @Bindable
    public String getDropHeightWeek() {
        return "" + latestWeek.getDropHeight();
    }

    @Bindable
    public String getRunCountWeek() {
        return "" + latestWeek.getLiftRides();
    }

    @Bindable
    public String getDropHeightSeason() {
        return "" + latestSeason.getDropHeight();
    }

    @Bindable
    public String getRunCountSeason() {
        return "" + latestSeason.getLiftRides();
    }

    @Bindable
    public String getSeason() {
        return "" + season;
    }

    @Bindable
    public boolean isRefreshing() {
        return refreshing;
    }

    public Context getContext() {
        return context;
    }

    public void setRefreshing(boolean refreshing) {
        this.refreshing = refreshing;
        notifyPropertyChanged(BR.refreshing);
    }

    public void setLatestDay(LatestDayStatistics latestDay) {
        this.latestDay = latestDay;
        notifyPropertyChanged(BR.dropHeightDay);
        notifyPropertyChanged(BR.runCountDay);
    }

    public void setLatestWeek(LatestWeekStatistics latestWeek) {
        this.latestWeek = latestWeek;
        notifyPropertyChanged(BR.dropHeightWeek);
        notifyPropertyChanged(BR.runCountWeek);
    }

    public void setLatestSeason(LatestSeasonStatistics latestSeason) {
        this.latestSeason = latestSeason;
        notifyPropertyChanged(BR.dropHeightSeason);
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
        runsAdapter.notifyDataSetChanged();
    }

    public void setFriendList(List<Entity> friends) {
        friendList.clear();
        friendList.addAll(friends);
        friendsAdapter.notifyDataSetChanged();
    }

    public void refresh() {
        storage.refresh();
    }
}
