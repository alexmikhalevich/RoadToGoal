package com.mikhalevich.roadtogoal;

import android.os.Bundle;

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
        super.initializeFloatingActionButton(this, parentId);

        LoadGoalsTask task = new LoadGoalsTask(super.goalList, super.recyclerView, parentId);
        task.execute();
    }
}