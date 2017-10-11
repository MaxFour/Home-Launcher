/*
 * Copyright (C) 2017 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package com.android.max_launcher.ui.widget;

import android.support.test.uiautomator.By;
import android.support.test.uiautomator.UiObject2;
import android.test.suitebuilder.annotation.LargeTest;
import android.view.View;

import com.android.max_launcher.ItemInfo;
import com.android.max_launcher.Launcher;
import com.android.max_launcher.LauncherAppWidgetInfo;
import com.android.max_launcher.LauncherAppWidgetProviderInfo;
import com.android.max_launcher.Workspace.ItemOperator;
import com.android.max_launcher.ui.LauncherInstrumentationTestCase;
import com.android.max_launcher.util.Condition;
import com.android.max_launcher.util.Wait;
import com.android.max_launcher.widget.WidgetCell;

/**
 * Test to add widget from widget tray
 */
@LargeTest
public class AddWidgetTest extends LauncherInstrumentationTestCase {

    private LauncherAppWidgetProviderInfo widgetInfo;

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        grantWidgetPermission();

        widgetInfo = findWidgetProvider(false /* hasConfigureScreen */);
    }

    public void testDragIcon_portrait() throws Throwable {
        lockRotation(true);
        performTest();
    }

    public void testDragIcon_landscape() throws Throwable {
        lockRotation(false);
        performTest();
    }

    private void performTest() throws Throwable {
        clearHomescreen();
        Launcher launcher = startLauncher();

        // Open widget tray and wait for load complete.
        final UiObject2 widgetContainer = openWidgetsTray();
        assertTrue(Wait.atMost(Condition.minChildCount(widgetContainer, 2), DEFAULT_UI_TIMEOUT));

        // Drag widget to homescreen
        UiObject2 widget = scrollAndFind(widgetContainer, By.clazz(WidgetCell.class)
                .hasDescendant(By.text(widgetInfo.getLabel(mTargetContext.getPackageManager()))));
        dragToWorkspace(widget, false);

        assertNotNull(launcher.getWorkspace().getFirstMatch(new ItemOperator() {
            @Override
            public boolean evaluate(ItemInfo info, View view) {
                return info instanceof LauncherAppWidgetInfo &&
                        ((LauncherAppWidgetInfo) info).providerName.equals(widgetInfo.provider);
            }
        }));
    }
}
