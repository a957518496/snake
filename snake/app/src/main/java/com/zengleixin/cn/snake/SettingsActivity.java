package com.zengleixin.cn.snake;

import android.content.Context;
import android.os.Bundle;
import android.preference.PreferenceActivity;
import android.preference.PreferenceManager;

public class SettingsActivity extends PreferenceActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.setting);
    }

    public static boolean getBgSound(Context context){
        return PreferenceManager.getDefaultSharedPreferences(context).getBoolean("bgsound",true);
    }

}
