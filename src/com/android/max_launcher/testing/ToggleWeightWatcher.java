package com.android.max_launcher.testing;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;

import com.android.max_launcher.Launcher;
import com.android.max_launcher.LauncherAppState;
import com.android.max_launcher.Utilities;
import com.android.max_launcher.util.TestingUtils;

public class ToggleWeightWatcher extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        SharedPreferences sp = Utilities.getPrefs(this);
        boolean show = sp.getBoolean(TestingUtils.SHOW_WEIGHT_WATCHER, true);

        show = !show;
        sp.edit().putBoolean(TestingUtils.SHOW_WEIGHT_WATCHER, show).apply();

        Launcher launcher = (Launcher) LauncherAppState.getInstance(this).getModel().getCallback();
        if (launcher != null && launcher.mWeightWatcher != null) {
            launcher.mWeightWatcher.setVisibility(show ? View.VISIBLE : View.GONE);
        }
        finish();
    }
}
