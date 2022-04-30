package com.dingar.twok.lottery.presentation.view;

import android.os.Bundle;

import androidx.preference.PreferenceFragmentCompat;

import com.dingar.twok.lottery.R;

public class Fragment_Setting extends PreferenceFragmentCompat {

    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
        setPreferencesFromResource(R.xml.root_preferences, rootKey);
    }

}