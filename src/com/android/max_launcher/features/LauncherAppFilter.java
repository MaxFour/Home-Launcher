package com.android.max_launcher.features;

import android.content.ComponentName;

import com.android.max_launcher.AppFilter;

import java.util.HashSet;

public class LauncherAppFilter extends AppFilter {
    private final HashSet mHide;

    public LauncherAppFilter() {
        mHide = new HashSet();
        mHide.add(ComponentName.unflattenFromString("com.google.android.googlequicksearchbox/.VoiceSearchActivity"));
        mHide.add(ComponentName.unflattenFromString("com.google.android.apps.wallpaper/.picker.CategoryPickerActivity"));
        mHide.add(ComponentName.unflattenFromString("com.google.android.launcher/com.google.android.launcher.StubApp"));
    }

    public boolean shouldShowApp(final ComponentName componentName) {
        return !mHide.contains(componentName);
    }
}