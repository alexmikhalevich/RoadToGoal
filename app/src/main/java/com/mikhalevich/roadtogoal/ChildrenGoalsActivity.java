package com.mikhalevich.roadtogoal;

import android.os.Bundle;
import android.util.Log;

import com.mikhalevich.roadtogoal.domain.GoalRepository;
import com.mikhalevich.roadtogoal.domain.ViewGoalEntityProxy;
import com.mikhalevich.roadtogoal.domain.dbtasks.LoadGoalsTask;

import lombok.Getter;

public class ChildrenGoalsActivity extends BasicActivity {
    @Getter
    private Integer parentId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        parentId = getIntent().getIntExtra("parentId", -1);
        if (parentId == -1)
            throw new RuntimeException();
        String parentName = getIntent().getStringExtra("parentName");
        Log.d("Parent id = ", String.valueOf(parentId));
        super.initializeFloatingActionButton(this, parentId);
        super.setTitle(parentName + " decomposition");

        LoadGoalsTask task = new LoadGoalsTask(super.goalList, super.recyclerView, parentId,
                true);
        task.execute();

        if (getSupportActionBar() != null)
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }

    @Override
    protected void onResume() {
        Log.d("Info", "onResume called in child, parent id = " + String.valueOf(parentId));

        super.onResume();

        LoadGoalsTask task = new LoadGoalsTask(super.goalList, super.recyclerView, parentId,
                true);
        task.execute();
    }
}