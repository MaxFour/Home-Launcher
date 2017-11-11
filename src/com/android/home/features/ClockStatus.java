package com.android.home.features;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.SystemClock;

import com.android.home.BubbleTextView;
import com.android.home.FastBitmapDrawable;

import java.util.Calendar;

public class ClockStatus extends FastBitmapDrawable implements Runnable
{
    private final ClockUpdateReceiver dr;
    private final Calendar ds;

    public ClockStatus(final Bitmap bitmap, final ClockUpdateReceiver dr) {
        super(bitmap);
        this.ds = Calendar.getInstance();
        this.dr = dr;
    }

    public void draw(final Canvas canvas) {
        final long n = 100;
        if (!(this.getCallback() instanceof BubbleTextView)) {
            this.drawInternal(canvas);
            return;
        }
        if (!this.dr.bitmapAvailable()) {
            super.draw(canvas);
            return;
        }
        this.ds.setTimeInMillis(System.currentTimeMillis());
        final Rect bounds = this.getBounds();
        final Drawable cm = this.dr.updateTime(this.ds);
        cm.setBounds(bounds);
        final float cn = this.dr.getScale();
        canvas.scale(cn, cn, bounds.exactCenterX(), bounds.exactCenterY());
        cm.draw(canvas);
        this.unscheduleSelf(this);
        final long uptimeMillis = SystemClock.uptimeMillis();
        this.scheduleSelf(this, uptimeMillis - uptimeMillis % n + n);
    }

    public void run() {
        this.invalidateSelf();
    }
}