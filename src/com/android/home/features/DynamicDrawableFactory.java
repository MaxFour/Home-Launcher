package com.android.home.features;

import android.content.Context;
import android.graphics.Bitmap;

import com.android.home.FastBitmapDrawable;
import com.android.home.ItemInfo;
import com.android.home.graphics.DrawableFactory;

public class DynamicDrawableFactory extends DrawableFactory
{
    ClockUpdateReceiver du;

    public DynamicDrawableFactory(final Context context) {
        this.du = ClockUpdateReceiver.getInstance(context);
    }

    public FastBitmapDrawable newIcon(final Bitmap bitmap, final ItemInfo itemInfo) {
        if (itemInfo != null && itemInfo.itemType == 0 && ClockUpdateReceiver.componentName.equals(itemInfo.getTargetComponent())) {
            final ClockStatus b = new ClockStatus(bitmap, this.du);
            b.setFilterBitmap(true);
            return b;
        }
        return super.newIcon(bitmap, itemInfo);
    }
}
