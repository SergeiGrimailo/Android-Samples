package com.gmail.sgrimailo.androidsamples.content_sharing;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.gmail.sgrimailo.androidsamples.R;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.List;

public class SharingFilesActivity extends AppCompatActivity {

    private static final int REQUEST_CODE_SEND_FILE = 1;
    private static final String LOG_TAG = SharingFilesActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sharing_files);
    }

    public void onShareFileClick(View view) throws IOException {
        File filePath = new File(getFilesDir(), "temp");
        if (!filePath.exists()) {
            filePath.mkdirs();
        }
        File file = new File(filePath, "temporary_file.txt");

        FileOutputStream outStream = new FileOutputStream(file);
        OutputStreamWriter writer = new OutputStreamWriter(outStream);
        try {
            writer.write("Hello, World!");
            writer.flush();
            writer.close();
        } finally {
            outStream.close();
        }

        Uri fileUri = FileProvider.getUriForFile(this,
                "com.gmail.sgrimailo.androidsamples.fileprovider", file);

        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.putExtra(Intent.EXTRA_STREAM, fileUri);
//        intent.putExtra(Intent.EXTRA_TITLE, "test_title");
//        intent.putExtra(Intent.EXTRA_TEXT, "Hello, World! (EXTRA_TEXT)");
        intent.setType("text/plain");

//        intent.putExtra(Intent.EXTRA_TITLE, "test_file");
//        intent.addCategory(Intent.CATEGORY_OPENABLE);

        PackageManager packageManager = getPackageManager();
        List<ResolveInfo> resolveInfoList = packageManager.queryIntentActivities(intent, 0);
        ComponentName componentName = intent.resolveActivity(packageManager);
        if (componentName != null) {
            startActivityForResult(intent, 1);
        } else {
            Snackbar.make(findViewById(android.R.id.content),
                    "Couldn't sahre file", Snackbar.LENGTH_LONG).show();
        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_CODE_SEND_FILE) {
            if (resultCode == Activity.RESULT_OK) {
                //
            }
            Log.d(LOG_TAG, String.format("File sending result: %s", resultCode));
        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }
}
