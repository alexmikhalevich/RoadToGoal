package com.mikhalevich.roadtogoal;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.mikhalevich.roadtogoal.domain.GoalRepository;
import com.mikhalevich.roadtogoal.domain.ViewGoalEntityProxy;
import com.mikhalevich.roadtogoal.domain.dbtasks.LoadGoalsTask;

public class MainActivity extends BasicActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initializeFloatingActionButton(this, -1);

        LoadGoalsTask task = new LoadGoalsTask(super.goalList, super.recyclerView, -1,
                true);
        task.execute();
    }

    @Override
    protected void onResume() {
        Log.d("Info", "onResume called in parent");

        super.onResume();
        LoadGoalsTask task = new LoadGoalsTask(super.goalList, super.recyclerView, -1,
                true);
        task.execute();
    }
}