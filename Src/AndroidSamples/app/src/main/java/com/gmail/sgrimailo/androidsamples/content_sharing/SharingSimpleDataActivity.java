package com.gmail.sgrimailo.androidsamples.content_sharing;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.gmail.sgrimailo.androidsamples.R;

public class SharingSimpleDataActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sharing_simple_data);
    }

    public void onShareTextAction(View view) {
        Intent sendIntent = new Intent(Intent.ACTION_SEND);
        sendIntent.putExtra(Intent.EXTRA_TEXT, "This is a text");
        sendIntent.setType("text/plain");
        startActivity(sendIntent);
    }

    public void onShareTextWithChooserClick(View view) {
        Intent sendIntent = new Intent();
        sendIntent.setAction(Intent.ACTION_SEND);
        sendIntent.putExtra(Intent.EXTRA_TEXT, "This is a text");
        sendIntent.setType("text/plain");
        startActivity(Intent.createChooser(sendIntent, "Share Text with Chooser"));
    }

    public void onPickTextFileClick(View view) {
        Intent pickIntent = new Intent();
        pickIntent.setAction(Intent.ACTION_PICK);
        startActivity(Intent.createChooser(pickIntent, "Pick file"));
    }
}
