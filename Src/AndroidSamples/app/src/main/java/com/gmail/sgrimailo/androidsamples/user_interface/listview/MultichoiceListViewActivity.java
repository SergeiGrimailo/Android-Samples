package com.gmail.sgrimailo.androidsamples.user_interface.listview;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.ActionMode;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.gmail.sgrimailo.androidsamples.R;

public class MultichoiceListViewActivity extends AppCompatActivity {

    private static final String LOG_TAG = MultichoiceListViewActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checked_list_view);

        final ListView listView = (ListView) findViewById(R.id.listView);

        String[] dataList = {"Item 1", "Item 2", "Item 3", "Item 4"};
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(
                this, R.layout.list_view_item, R.id.textView, dataList);
        listView.setAdapter(dataAdapter);

        listView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE_MODAL);

        listView.setMultiChoiceModeListener(new AbsListView.MultiChoiceModeListener() {

            boolean moreThanTwo = false;
            boolean moreThanTwoMenu = false;

            @Override
            public void onItemCheckedStateChanged(ActionMode mode, int position, long id, boolean checked) {
                if (moreThanTwo) {
                    if (listView.getCheckedItemCount() == 1) {
                        moreThanTwo = false;
                        mode.invalidate();
                    }
                } else {
                    if (listView.getCheckedItemCount() > 1) {
                        moreThanTwo = true;
                        mode.invalidate();
                    }
                }
                mode.setTitle(String.format("%d selected", listView.getCheckedItemCount()));
            }

            @Override
            public boolean onCreateActionMode(ActionMode mode, Menu menu) {
                mode.setTitle("Title");
                mode.setSubtitle("SubTitle");
                moreThanTwo = listView.getCheckedItemCount() > 1;
                moreThanTwoMenu = !moreThanTwo;
                return true;
            }

            @Override
            public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
                if (moreThanTwo) {
                    if (!moreThanTwoMenu) {
                        menu.clear();
                        mode.getMenuInflater().inflate(R.menu.multichoice_listview_menu2, menu);
                        moreThanTwoMenu = true;
                    }
                } else {
                    if (moreThanTwoMenu) {
                        menu.clear();
                        mode.getMenuInflater().inflate(R.menu.multichoice_listview_menu1, menu);
                        moreThanTwoMenu = false;
                    }
                }
                return true;
            }

            @Override
            public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
                return false;
            }

            @Override
            public void onDestroyActionMode(ActionMode mode) {
                //
            }
        });
    }
}
