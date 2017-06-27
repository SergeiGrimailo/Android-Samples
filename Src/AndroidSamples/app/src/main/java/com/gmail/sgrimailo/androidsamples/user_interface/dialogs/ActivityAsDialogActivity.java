package com.gmail.sgrimailo.androidsamples.user_interface.dialogs;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.gmail.sgrimailo.androidsamples.R;

public class ActivityAsDialogActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_as_dialog);
    }
}
