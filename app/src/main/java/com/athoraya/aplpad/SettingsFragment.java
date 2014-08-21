package com.athoraya.aplpad;

import android.os.Bundle;
import android.preference.PreferenceFragment;

/**
 * Created by gil on 20/08/2014.
 */
public class SettingsFragment extends PreferenceFragment {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        addPreferencesFromResource(R.xml.preferences);
    }
}
