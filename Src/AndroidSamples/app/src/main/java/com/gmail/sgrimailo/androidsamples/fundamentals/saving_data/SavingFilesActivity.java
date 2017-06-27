package com.gmail.sgrimailo.androidsamples.fundamentals.saving_data;

import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.gmail.sgrimailo.androidsamples.R;
import com.gmail.sgrimailo.androidsamples.utils.ui.SnackbarQueue;

import java.io.File;

public class SavingFilesActivity extends AppCompatActivity {

    private final SnackbarQueue mSnackbarQueue = new SnackbarQueue();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_saving_files);
    }

    public void onShowHeadDirsInfoClick(View view) {

        showDirectoryInfo("Files Dir", getFilesDir());
        showDirectoryInfo("Cache Dir", getCacheDir());
        showDirectoryInfo("Obb Dir", getObbDir());
        showDirectoryInfo("External Cache Dir", getExternalCacheDir());
//        showDirectoryInfo("", getExternalFilesDir(""));
//        showDirectoryInfo("", getDir("", -1));
//        showDirectoryInfo("", getDatabasePath(""));
//        showDirectoryInfo("", getFileStreamPath(""));
    }

    protected void showDirectoryInfo(String dirDescription, File dir) {
        String message = String.format("%s: %s", dirDescription, dir);
        mSnackbarQueue.showSnackbar(Snackbar.make(findViewById(
                R.id.coordinator_layout), message, Snackbar.LENGTH_LONG));
        Log.d(SavingFilesActivity.class.getSimpleName(), message);
    }
}
