package se.theslof.skistarstats.fragment;

import android.os.Bundle;
import android.preference.PreferenceFragment;

import se.theslof.skistarstats.R;

public class SettingsFragment extends PreferenceFragment {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        addPreferencesFromResource(R.xml.preferences);
    }
}
