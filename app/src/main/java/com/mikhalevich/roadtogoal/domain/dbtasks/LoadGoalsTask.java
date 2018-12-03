package com.mikhalevich.roadtogoal.domain.dbtasks;

import android.os.AsyncTask;
import android.support.v7.widget.RecyclerView;

import com.mikhalevich.roadtogoal.domain.GoalRepository;
import com.mikhalevich.roadtogoal.domain.ViewGoalEntityProxy;

import java.util.ArrayList;

public class LoadGoalsTask extends AsyncTask<Void, Void, Void> {
    private ArrayList<ViewGoalEntityProxy> goalList;
    private RecyclerView recyclerView;
    private int parentId;
    private boolean forceReload;

    public LoadGoalsTask(ArrayList<ViewGoalEntityProxy> goalList, RecyclerView recyclerView,
                         int parentId, boolean forceReload) {
        super();
        this.goalList = goalList;
        this.recyclerView = recyclerView;
        this.parentId = parentId;
        this.forceReload = forceReload;
    }

    @Override
    protected Void doInBackground(Void... voids) {
        goalList.clear();
        goalList.addAll(GoalRepository.getRepository().getAllGoals(this.forceReload, parentId,
                ViewGoalEntityProxy.class));
        return null;
    }

    @Override
    protected void onPostExecute(Void result) {
        super.onPostExecute(result);
        recyclerView.getAdapter().notifyDataSetChanged();
    }
}