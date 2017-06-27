package com.gmail.sgrimailo.androidsamples;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.gmail.sgrimailo.androidsamples.content_sharing.SharingSimpleDataActivity;
import com.gmail.sgrimailo.androidsamples.fundamentals.saving_data.SavingFilesActivity;
import com.gmail.sgrimailo.androidsamples.interaction_and_engagement.notifying.NotifyingActivity;
import com.gmail.sgrimailo.androidsamples.navigation.NavigationDrawerActivity;
import com.gmail.sgrimailo.androidsamples.user_interface.dialogs.DialogsActivity;
import com.gmail.sgrimailo.androidsamples.user_interface.popup_messages.ShowingPopUpMessagesActivity;

import java.util.HashMap;
import java.util.Map;

public class StartActivity extends AppCompatActivity {

    private static final Map<Integer, Class> idToActivityMap = new HashMap<>();
    static {
        idToActivityMap.put(R.id.saving_files_activity, SavingFilesActivity.class);
        idToActivityMap.put(R.id.show_dialogs_samples, DialogsActivity.class);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.start_activity_options_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int itemId = item.getItemId();
        switch (itemId) {
            case R.id.notifying_the_user:
                Intent intent = new Intent(this, NotifyingActivity.class);
                startActivity(intent);
                break;
            case R.id.showing_pop_up_messages:
                Intent intent1 = new Intent(this, ShowingPopUpMessagesActivity.class);
                startActivity(intent1);
                break;
            default:
                Class<?> activityClass = idToActivityMap.get(itemId);
                if (activityClass != null) {
                    startActivity(activityClass);
                }
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    protected void startActivity(Class<?> acitivityClass) {
        Intent intent = new Intent(this, acitivityClass);
        startActivity(intent);
    }

    public void onNavigationDrawerClick(View view) {
        Intent intent = new Intent(this, NavigationDrawerActivity.class);
        startActivity(intent);
    }

    public void onSimpleDataShareClick(View view) {
        Intent intent = new Intent(this, SharingSimpleDataActivity.class);
        startActivity(intent);
    }
}
