package com.gmail.sgrimailo.androidsamples.user_interface.popup_messages;

import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.gmail.sgrimailo.androidsamples.R;
import com.gmail.sgrimailo.androidsamples.utils.ui.SnackbarQueue;

public class ShowingPopUpMessagesActivity extends AppCompatActivity {

    private final SnackbarQueue mSnackbarQueue = new SnackbarQueue();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_showing_pop_up_messages);
    }

    public void onShowPopupMessageClick(View view) {
        Snackbar mySnackBar = Snackbar.make(findViewById(R.id.coordinator_layout),
                "Test Pop-Up Message", Snackbar.LENGTH_LONG);
        mSnackbarQueue.showSnackbar(mySnackBar);
    }

    public void onShowSeveralPopupMessagesClick(View view) {
        View theViewAttachTo = findViewById(R.id.coordinator_layout);
        mSnackbarQueue.showSnackbar(Snackbar.make(theViewAttachTo,
                "Popup One", Snackbar.LENGTH_LONG));
        mSnackbarQueue.showSnackbar(Snackbar.make(theViewAttachTo,
                "Popup Two", Snackbar.LENGTH_LONG));
        mSnackbarQueue.showSnackbar(Snackbar.make(theViewAttachTo,
                "Popup Three", Snackbar.LENGTH_LONG));
    }

    public void onShowSnackBarWithAction(View view) {
        final View coordinatorLayout = findViewById(R.id.coordinator_layout);
        Snackbar snackbar = Snackbar.make(coordinatorLayout, "Snack bar with the action", Snackbar.LENGTH_LONG);
        snackbar.setAction("Go!Go!Go!", new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mSnackbarQueue.showSnackbar(Snackbar.make(coordinatorLayout,
                        "You've just pressed the snackbar action button!", Snackbar.LENGTH_SHORT));
            }
        });
        mSnackbarQueue.showSnackbar(snackbar);
    }
}
