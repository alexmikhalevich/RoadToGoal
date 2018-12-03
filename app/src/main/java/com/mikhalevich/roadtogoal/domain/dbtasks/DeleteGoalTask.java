package com.mikhalevich.roadtogoal.domain.dbtasks;

import android.os.AsyncTask;

import com.mikhalevich.roadtogoal.domain.GoalEntityProxy;
import com.mikhalevich.roadtogoal.domain.GoalRepository;

public class DeleteGoalTask<GoalEntityT extends GoalEntityProxy>
        extends AsyncTask<GoalEntityT, Void, Void> {
    @SafeVarargs
    @Override
    protected final Void doInBackground(GoalEntityT... items) {
        GoalRepository.getRepository().deleteGoal(items[0]);
        return null;
    }
}