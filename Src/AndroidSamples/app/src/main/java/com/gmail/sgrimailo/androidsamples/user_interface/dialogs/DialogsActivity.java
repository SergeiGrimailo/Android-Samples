package com.gmail.sgrimailo.androidsamples.user_interface.dialogs;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.gmail.sgrimailo.androidsamples.R;

public class DialogsActivity extends AppCompatActivity implements NoticeDialogFragment.Listener {

    private static final String TAG_PREFIX = DialogsActivity.class.getSimpleName();

    private static final String FRAGMENT_TAG_FIRE_MISSILES = genTag("FRAGMENT_TAG_FIRE_MISSILES");
    private static final String FRAGMENT_TAG_CHOOSE_COLOR = genTag("FRAGMENT_TAG_CHOOSE_COLOR");
    private static final String FRAGMENT_TAG_RADIO_BUTTONS = genTag("FRAGMENT_TAG_RADIO_BUTTONS");
    private static final String FRAGMENT_TAG_MULTI_CHOICE = genTag("FRAGMENT_TAG_MULTI_CHOICE");
    private static final String FRAGMENT_TAG_USER_LOGIN = genTag("FRAGMENT_TAG_USER_LOGIN");
    private static final String FRAGMENT_TAG_NOTICE_DIALOG = genTag("FRAGMENT_TAG_NOTICE_DIALOG");

    private static String genTag(String tagName) {
        return String.format("%s.%s", TAG_PREFIX, tagName);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dialogs);
    }

    public void onFireMissilesClick(View view) {
        FireMissilesDialogFragment dialogFragment = new FireMissilesDialogFragment();
        dialogFragment.show(getSupportFragmentManager(), FRAGMENT_TAG_FIRE_MISSILES);
    }

    public void onChooseColorClick(View view) {
        SingleChoiceDialogFragment dialogFragment = new SingleChoiceDialogFragment();
        dialogFragment.show(getSupportFragmentManager(), FRAGMENT_TAG_CHOOSE_COLOR);
    }

    public void onSingleChoosePersistClick(View view) {
        RadioButtonsDialogFragment dialogFragment = new RadioButtonsDialogFragment();
        dialogFragment.show(getSupportFragmentManager(), FRAGMENT_TAG_RADIO_BUTTONS);
    }

    public void onMultiChoicePersistClick(View view) {
        MultiChoiceDialogFragment dialogFragment = new MultiChoiceDialogFragment();
        dialogFragment.show(getSupportFragmentManager(), FRAGMENT_TAG_MULTI_CHOICE);
    }

    public void onUserLoginClick(View view) {
        UserLoginDialogFragment dialogFragment = new UserLoginDialogFragment();
        dialogFragment.show(getSupportFragmentManager(), FRAGMENT_TAG_USER_LOGIN);
    }

    public void onStartActivityAsDialog(View view) {
        Intent intent = new Intent(this, ActivityAsDialogActivity.class);
        startActivity(intent);
    }

    public void showNoticeDialogFragment(View view) {
        NoticeDialogFragment fragment = new NoticeDialogFragment();
        fragment.show(getSupportFragmentManager(), FRAGMENT_TAG_NOTICE_DIALOG);
    }

    @Override
    public void onPositiveButtonClick() {
        Snackbar.make(findViewById(R.id.snackbar_host),
                "Positive button click", Snackbar.LENGTH_LONG).show();
    }

    @Override
    public void onNegativeButtonClick() {
        Snackbar.make(findViewById(R.id.snackbar_host),
                "Negative button click", Snackbar.LENGTH_LONG).show();
    }

    public static class FireMissilesDialogFragment extends DialogFragment {

        @NonNull
        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(getActivity());

            dialogBuilder.setTitle("Fire Missiles");
            dialogBuilder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    // Fire!!!
                }
            });
            dialogBuilder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    // Cancel fire!!!
                }
            });

            return dialogBuilder.create();
        }
    }

    public static class SingleChoiceDialogFragment extends DialogFragment {

        @NonNull
        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(getActivity());
            dialogBuilder.setTitle("");
            final String[] items = {"Red", "Blue", "Yellow"};
            dialogBuilder.setItems(items,
                    new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            View contentView = getActivity().findViewById(R.id.snackbar_host);
                            if (contentView != null) {
                                Snackbar.make(contentView,
                                        String.format("You have choosed %s color!", items[i]),
                                        Snackbar.LENGTH_LONG)
                                        .show();
                            }
                        }
                    });
            return dialogBuilder.create();
        }
    }

    public static class RadioButtonsDialogFragment extends DialogFragment {

        @NonNull
        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(getActivity());
            final String[] items = {"One", "Two", "Three"};
            final String[] selectedItem = {"None"};
            dialogBuilder.setSingleChoiceItems(items,
                    0, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            selectedItem[0] = items[i];
                        }
                    });
            dialogBuilder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    View contentView = getActivity().findViewById(R.id.snackbar_host);
                    Snackbar.make(contentView,
                            String.format("You have chosen: %s", selectedItem[0]),
                            Snackbar.LENGTH_LONG).show();
                }
            });
            dialogBuilder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    View contentView = getActivity().findViewById(R.id.snackbar_host);
                    Snackbar.make(contentView,
                            String.format("Your haven't chosen: %s", selectedItem[0]),
                            Snackbar.LENGTH_LONG).show();
                }
            });
            return dialogBuilder.create();
        }
    }

    public static class MultiChoiceDialogFragment extends DialogFragment {

        @NonNull
        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(getActivity());
            final String[] items = {"How", "are", "you", "?"};
            final boolean[] choice = {true, true, true, true};
            dialogBuilder.setMultiChoiceItems(items, new boolean[]{true, true, true, true},
                    new DialogInterface.OnMultiChoiceClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i, boolean b) {
                            choice[i] = b;
                        }
                    });
            dialogBuilder.setPositiveButton("Ok",
                    new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    StringBuilder stringBuilder = new StringBuilder();
                    for (int n = 0; n < 4; n++) {
                        if (choice[n]) stringBuilder.append(
                                String.format("%s ", items[n]));
                    }
                    Snackbar.make(getActivity().findViewById(R.id.snackbar_host),
                            stringBuilder.toString(), Snackbar.LENGTH_LONG).show();

                }
            });
            return dialogBuilder.create();
        }
    }

    public static class UserLoginDialogFragment extends DialogFragment {

        @NonNull
        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(getActivity());

            dialogBuilder.setView(R.layout.user_login_dialog);
            dialogBuilder.setPositiveButton("Login", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    EditText etLogin = UserLoginDialogFragment.this.getDialog()
                            .findViewById(R.id.etLogin);
                    EditText etPassword = UserLoginDialogFragment.this.getDialog()
                            .findViewById(R.id.etPassword);
                    Snackbar.make(getActivity().findViewById(R.id.snackbar_host),
                            String.format("Your login is: %s, Your password is: %s",
                                    etLogin.getText(), etPassword.getText()),
                            Snackbar.LENGTH_LONG).show();
                }
            });

            return dialogBuilder.create();
        }
    }
}
