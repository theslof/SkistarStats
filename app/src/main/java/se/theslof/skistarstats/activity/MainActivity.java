package se.theslof.skistarstats.activity;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.job.JobInfo;
import android.app.job.JobScheduler;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import se.theslof.skistarstats.R;
import se.theslof.skistarstats.fragment.FriendsFragment;
import se.theslof.skistarstats.fragment.RunsFragment;
import se.theslof.skistarstats.fragment.SummaryFragment;
import se.theslof.skistarstats.job.AutoUpdateStats;
import se.theslof.skistarstats.viewmodel.MainModel;
import se.theslof.skistarstats.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private MainModel viewModel;
    private SummaryFragment summaryFragment;
    private RunsFragment runsFragment;
    private FriendsFragment friendsFragment;

    private boolean autoUpdate = false;
    private long updateInterval = 360000;
    private SharedPreferences.OnSharedPreferenceChangeListener prefListener;

    JobScheduler jobScheduler;
    private int jobId = 1234;


    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_summary:
                    if (summaryFragment == null)
                        summaryFragment = SummaryFragment.newInstance(viewModel);

                    getFragmentManager().beginTransaction()
                            .replace(R.id.main_container, summaryFragment, "SUMMARY")
                            .commit();

                    return true;
                case R.id.navigation_runs:
                    if (runsFragment == null)
                        runsFragment = RunsFragment.newInstance(viewModel);

                    getFragmentManager().beginTransaction()
                            .replace(R.id.main_container, runsFragment, "RUNS")
                            .commit();

                    return true;
                case R.id.navigation_friends:
                    if (friendsFragment == null)
                        friendsFragment = FriendsFragment.newInstance(viewModel);

                    getFragmentManager().beginTransaction()
                            .replace(R.id.main_container, friendsFragment, "FRIENDS")
                            .commit();

                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        ActivityMainBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        viewModel = new MainModel(this);
        binding.setViewModel(viewModel);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ((BottomNavigationView) findViewById(R.id.navigation))
                .setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        summaryFragment = SummaryFragment.newInstance(viewModel);

        getFragmentManager().beginTransaction()
                .replace(R.id.main_container, summaryFragment, "SUMMARY")
                .commit();

        jobScheduler = (JobScheduler) getSystemService(Context.JOB_SCHEDULER_SERVICE);
        registerNotificationChannel();

        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        prefListener = new SharedPreferences.OnSharedPreferenceChangeListener() {
            @Override
            public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String s) {
                Log.d("Settings changed", s);
                switch (s) {
                    case SettingsActivity.PREF_AUTO_REFRESH:
                        autoUpdate = sharedPreferences.getBoolean(SettingsActivity.PREF_AUTO_REFRESH, false);
                        setAutoUpdate();
                        break;
                    case SettingsActivity.PREF_REFRESH_INTERVAL:
                        updateInterval = Long.parseLong(sharedPreferences.getString(SettingsActivity.PREF_REFRESH_INTERVAL, "360000"));
                        setAutoUpdate();
                        break;
                }
            }
        };
        sharedPreferences.registerOnSharedPreferenceChangeListener(prefListener);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_refresh:
                viewModel.refresh();
                return true;
            case R.id.action_settings:
                // Open settings activity
                Intent intent = new Intent(this, SettingsActivity.class);
                startActivity(intent);
                return true;
            default:
                return false;
        }
    }

    private void registerNotificationChannel() {
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            NotificationManager notificationManager =
                    (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);


            // The user-visible name of the channel.
            CharSequence name = getString(R.string.channel_name);
            String id = getString(R.string.channel_id);
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel mChannel = new NotificationChannel(id, name, importance);
            // Configure the notification channel.
            notificationManager.createNotificationChannel(mChannel);
        }
    }

    // -- Auto update --

    private void setAutoUpdate() {
        Log.d("Auto Update", "Tried to set update to: " + autoUpdate + ", " + updateInterval);
        schedulePeriodicJob(autoUpdate, updateInterval);
    }

    private void schedulePeriodicJob(boolean enable, long interval) {
        jobScheduler.cancel(jobId);
        if (enable)
            jobScheduler.schedule(new JobInfo.Builder(jobId,
                    new ComponentName(this, AutoUpdateStats.class))
                    .setRequiredNetworkType(JobInfo.NETWORK_TYPE_ANY)
                    .setPeriodic(interval)
                    .build());
    }
}
