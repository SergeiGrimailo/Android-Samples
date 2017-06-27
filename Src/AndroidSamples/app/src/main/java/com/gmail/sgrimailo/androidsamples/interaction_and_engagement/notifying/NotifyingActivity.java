package com.gmail.sgrimailo.androidsamples.interaction_and_engagement.notifying;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.app.NotificationCompat;
import android.view.View;

import com.gmail.sgrimailo.androidsamples.R;

public class NotifyingActivity extends AppCompatActivity {

    private static final Integer REQUEST_CODE_NOTIFICATION_INTENT = 1;

    private static final Integer NOTIFICATION_ID_FIRST_TEST = 001;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notifying);
    }

    public void onShowNotificationClick(View view) {
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this);
        builder.setSmallIcon(R.drawable.ic_menu_slideshow);
        builder.setContentTitle("Title");
        builder.setContentText("Content Text");

        Intent resultIntent = new Intent(this, NotificationDetailsActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this,
                REQUEST_CODE_NOTIFICATION_INTENT, resultIntent,
                PendingIntent.FLAG_UPDATE_CURRENT);
        builder.setContentIntent(pendingIntent);

        NotificationManager notificationManager =
                (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        notificationManager.notify(NOTIFICATION_ID_FIRST_TEST, builder.build());

    }
}
