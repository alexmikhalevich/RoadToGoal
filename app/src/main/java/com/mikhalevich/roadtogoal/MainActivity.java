package com.mikhalevich.roadtogoal;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;

import com.mikhalevich.roadtogoal.domain.dbtasks.LoadGoalsTask;

public class MainActivity extends BasicActivity {
    @Override
    public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction, int position) {
        super.onSwiped(viewHolder, direction, position);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initializeFloatingActionButton(this, -1);

        LoadGoalsTask task = new LoadGoalsTask(super.goalList, super.recyclerView, -1);
        task.execute();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }
}