package com.android.home.features;


import android.content.ComponentName;

import com.android.home.AppFilter;

import java.util.HashSet;

public class HomeAppFilter extends AppFilter {
    private final HashSet mHide;

    public HomeAppFilter() {
        mHide = new HashSet();
        mHide.add(ComponentName.unflattenFromString("com.google.android.googlequicksearchbox/.VoiceSearchActivity"));
        mHide.add(ComponentName.unflattenFromString("com.google.android.apps.wallpaper/.picker.CategoryPickerActivity"));
        mHide.add(ComponentName.unflattenFromString("com.google.android.launcher/com.google.android.launcher.StubApp"));
        mHide.add(ComponentName.unflattenFromString("com.android.documentsui/.LauncherActivity"));
    }

    public boolean shouldShowApp(final  ComponentName componentName) {
        return !mHide.contains(componentName);
    }
}
