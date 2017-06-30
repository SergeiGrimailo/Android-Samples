package com.gmail.sgrimailo.androidsamples.content_providers;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.gmail.sgrimailo.androidsamples.R;

import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.List;

public class StorageAccessFrameworkActivity extends AppCompatActivity {

    private static final int REQUEST_CODE_CREATE_TEXT_DOCUMENT = 1;
    private static final String LOG_TAG = StorageAccessFrameworkActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_storage_access_framework);
    }

    public void onCreateTextDocumentClick(View view) throws IOException {

        Intent intent = new Intent(Intent.ACTION_CREATE_DOCUMENT);

        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_TITLE, "test_file");
        intent.addCategory(Intent.CATEGORY_OPENABLE);


        PackageManager packageManager = getPackageManager();
        List<ResolveInfo> resolveInfos = packageManager.queryIntentActivities(intent, 0);

        ComponentName componentName = intent.resolveActivity(packageManager);
        if (componentName != null) {
            startActivityForResult(Intent.createChooser(intent, "Create new text document"),
                    REQUEST_CODE_CREATE_TEXT_DOCUMENT);
        } else {
            Intent intent1 = new Intent(this, DocumentActionsActivity.class);
            startActivity(intent1);
        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_CODE_CREATE_TEXT_DOCUMENT) {
            if (resultCode == Activity.RESULT_OK) {
                try {
                    OutputStream outputStream = getContentResolver().openOutputStream(data.getData());
                    try {
                        OutputStreamWriter writer = new OutputStreamWriter(outputStream);
                        writer.write("Hello, world!");
                        writer.flush();
                        writer.close();
                    } finally {
                        outputStream.close();
                    }
                } catch (IOException e) {
                    Log.e(LOG_TAG, "File writing error", e);
                }
            }
            Log.d(LOG_TAG, String.format("File sending result: %s", resultCode));
        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }
}
