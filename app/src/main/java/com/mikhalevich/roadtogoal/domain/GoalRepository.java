package com.mikhalevich.roadtogoal.domain;

import android.app.Application;
import android.os.AsyncTask;

import java.util.List;

public class GoalRepository<GoalEntityT extends GoalEntity> {

    private GoalDao goalDao;
    private List<GoalEntityT> goalEntities;

    public GoalRepository(Application application) {
        GoalRoomDatabase db = GoalRoomDatabase.getDatabase(application);
        goalDao = db.goalDao();

        //TODO: check whether selectAllGoals() will not be called multiple times
        for (Object g : goalDao.selectAllGoals())
            goalEntities.add((GoalEntityT) g);
    }

    public List<GoalEntityT> getAllGoals() {
        return goalEntities;
    }

    public void insert(GoalEntity goalEntity) {
        new insertAsyncTask(goalDao).execute(goalEntity);
    }

    private static class insertAsyncTask extends AsyncTask<GoalEntity, Void, Void> {
        private GoalDao goalAsyncDao;

        insertAsyncTask(GoalDao dao) {
            this.goalAsyncDao = dao;
        }

        @Override
        protected Void doInBackground(final GoalEntity... entities) {
            goalAsyncDao.insert(entities[0]);
            return null;
        }
    }
}
