package se.theslof.skistarstats.job;

import android.app.PendingIntent;
import android.app.job.JobParameters;
import android.app.job.JobService;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.preference.PreferenceManager;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import se.theslof.skistarstats.R;
import se.theslof.skistarstats.activity.MainActivity;
import se.theslof.skistarstats.activity.SettingsActivity;
import se.theslof.skistarstats.model.LiftRide;
import se.theslof.skistarstats.service.APIClient;
import se.theslof.skistarstats.utility.DateUtil;

public class AutoUpdateStats extends JobService {
    @Override
    public boolean onStartJob(final JobParameters jobParameters) {
        // Refresh skier stats, notify if new data
        final SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        String skierId = sharedPreferences.getString(SettingsActivity.PREF_SKIER_ID, "3206");
        int season = Integer.parseInt(sharedPreferences.getString(SettingsActivity.PREF_SEASON, "13"));

        ConnectivityManager cm =
                (ConnectivityManager)this.getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        boolean isConnected = activeNetwork != null &&
                activeNetwork.isConnectedOrConnecting();

        if(isConnected)
            APIClient.getSkistarService().liftRides(skierId, "" + season).enqueue(new Callback<List<LiftRide>>() {
            @Override
            public void onResponse(Call<List<LiftRide>> call, Response<List<LiftRide>> response) {
                String latestRun = sharedPreferences.getString(getResources().getString(R.string.latest_run), "1970-01-01T00:00:00");
                String newRun = response.body().get(0).getDate();

                if(DateUtil.isLater(latestRun, newRun)){
                    jobFinished(jobParameters,true);

                    NotificationCompat.Builder builder =
                            new NotificationCompat.Builder(AutoUpdateStats.this,
                                    getResources().getString(R.string.channel_id))
                                    .setSmallIcon(R.drawable.ic_ac_unit_black_24dp)
                                    .setContentTitle(getResources().getString(R.string.app_name))
                                    .setContentText(getString(R.string.new_data_notification));

                    Intent intent = new Intent(AutoUpdateStats.this, MainActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP
                            | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                    PendingIntent pIntent = PendingIntent.getActivity(AutoUpdateStats.this, 0,
                            intent, 0);

                    builder.setContentIntent(pIntent);

                    NotificationManagerCompat.from(AutoUpdateStats.this).notify(1, builder.build() );

                }

            }

            @Override
            public void onFailure(Call<List<LiftRide>> call, Throwable t) {

            }
        });

        return true;
    }

    @Override
    public boolean onStopJob(JobParameters jobParameters) {
        return false;
    }
}
