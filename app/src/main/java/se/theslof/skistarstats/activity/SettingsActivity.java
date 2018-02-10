package se.theslof.skistarstats.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import se.theslof.skistarstats.R;
import se.theslof.skistarstats.fragment.SettingsFragment;

public class SettingsActivity extends AppCompatActivity {
    public static final String PREF_SKIER_ID = "skier_id";
    public static final String PREF_SEASON = "season";
    public static final String PREF_AUTO_REFRESH = "auto_refresh";
    public static final String PREF_REFRESH_INTERVAL = "refresh_interval";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        Toolbar toolbar = (Toolbar) findViewById(R.id.settings_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        getFragmentManager().beginTransaction()
                .add(R.id.settings_container, new SettingsFragment())
                .commit();
    }
}
