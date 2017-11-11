package com.android.max_launcher;

import android.app.backup.BackupAgent;
import android.app.backup.BackupDataInput;
import android.app.backup.BackupDataOutput;
import android.os.ParcelFileDescriptor;

import com.android.max_launcher.logging.FileLog;
import com.android.max_launcher.provider.RestoreDbTask;

public class LauncherBackupAgent extends BackupAgent {

    @Override
    public void onCreate() {
        super.onCreate();
        // Set the log dir as LauncherAppState is not initialized during restore.
        FileLog.setDir(getFilesDir());
    }

    @Override
    public void onRestore(
            BackupDataInput data, int appVersionCode, ParcelFileDescriptor newState) {
        // Doesn't do incremental backup/restore
    }

    @Override
    public void onBackup(
            ParcelFileDescriptor oldState, BackupDataOutput data, ParcelFileDescriptor newState) {
        // Doesn't do incremental backup/restore
    }

    @Override
    public void onRestoreFinished() {
        RestoreDbTask.setPending(this, true);
    }
}
