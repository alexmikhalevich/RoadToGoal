package com.mikhalevich.roadtogoal.domain.dbtasks;

import android.os.AsyncTask;

import com.mikhalevich.roadtogoal.domain.GoalRepository;
import com.mikhalevich.roadtogoal.domain.InsertGoalEntityProxy;

public class InsertGoalTask extends AsyncTask<InsertGoalEntityProxy, Void, Void> {
    @Override
    protected Void doInBackground(InsertGoalEntityProxy... entity) {
        GoalRepository.getRepository().insertGoal(entity[0]);
        //TODO: modify parent's "hasChildren"
        return null;
    }
}
