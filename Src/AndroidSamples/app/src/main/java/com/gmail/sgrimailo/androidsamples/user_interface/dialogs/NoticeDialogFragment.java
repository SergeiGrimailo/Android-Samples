package com.gmail.sgrimailo.androidsamples.user_interface.dialogs;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;

/**
 * Created by Sergey on 6/27/2017.
 */

public class NoticeDialogFragment extends DialogFragment {

    public interface Listener {
        void onPositiveButtonClick();
        void onNegativeButtonClick();
    }

    private Listener mListener = new Listener() {
        @Override
        public void onPositiveButtonClick() {
        }

        @Override
        public void onNegativeButtonClick() {
        }
    };

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof Listener) {
            mListener = (Listener) context;
        }
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        return new AlertDialog.Builder(getContext())
                .setMessage("Press any button")
                .setPositiveButton("Positive", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        mListener.onPositiveButtonClick();
                    }
                })
                .setNegativeButton("Negative", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        mListener.onNegativeButtonClick();
                    }
                }).create();
    }

    @Override
    public void onDetach() {
        mListener = null;
        super.onDetach();
    }
}
