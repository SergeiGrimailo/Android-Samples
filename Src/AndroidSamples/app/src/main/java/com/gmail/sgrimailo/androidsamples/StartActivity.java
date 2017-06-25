package com.gmail.sgrimailo.androidsamples;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.gmail.sgrimailo.androidsamples.navigation.NavigationDrawerActivity;

public class StartActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
    }

    public void onNavigationDrawerButtonClick(View view) {
        Intent intent = new Intent(this, NavigationDrawerActivity.class);
        startActivity(intent);
    }
}
