package com.android.home.features;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Process;

import com.android.home.FastBitmapDrawable;
import com.android.home.ItemInfo;
import com.android.home.LauncherSettings;
import com.android.home.graphics.DrawableFactory;
import com.android.home.features.DynamicClock;

public class DynamicDrawableFactory extends DrawableFactory {
    private final DynamicClock mDynamicClockDrawer;

    public DynamicDrawableFactory(Context context) {
        mDynamicClockDrawer = new DynamicClock(context);
    }

    @Override
    public FastBitmapDrawable newIcon(Bitmap icon, ItemInfo info) {
        if (info != null &&
                info.itemType == LauncherSettings.Favorites.ITEM_TYPE_APPLICATION &&
                DynamicClock.DESK_CLOCK.equals(info.getTargetComponent()) &&
                info.user.equals(Process.myUserHandle())) {
            return mDynamicClockDrawer.drawIcon(icon);
        }
        return super.newIcon(icon, info);
    }
}