package com.gmail.sgrimailo.androidsamples.utils.ui;

import android.support.design.widget.Snackbar;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by Sergey on 6/26/2017.
 */

public class SnackbarQueue {

    private final Queue<Snackbar> mSnackbarsQueue = new LinkedList<>();

    private Snackbar.Callback mSnackbarCallbak = new Snackbar.Callback() {
        @Override
        public void onDismissed(Snackbar transientBottomBar, int event) {
            transientBottomBar.removeCallback(this);
            mSnackbarsQueue.poll();
            Snackbar snackbar = mSnackbarsQueue.peek();
            if (snackbar != null) {
                snackbar.addCallback(mSnackbarCallbak);
                snackbar.show();
            }
        }
    };

    public void showSnackbar(Snackbar snackbar) {
        if (snackbar != null) {
            if (mSnackbarsQueue.peek() == null) {
                mSnackbarsQueue.offer(snackbar);
                snackbar.addCallback(mSnackbarCallbak);
                snackbar.show();
            } else {
                mSnackbarsQueue.offer(snackbar);
            }
        }
    }
}
