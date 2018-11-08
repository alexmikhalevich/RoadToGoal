package com.mikhalevich.roadtogoal.domain;

import android.app.Application;
import android.os.AsyncTask;

import java.util.List;

public class GoalRepository {

    private GoalDao goalDao;
    private List<GoalEntity> goalEntities;

    public GoalRepository(Application application) {
        GoalRoomDatabase db = GoalRoomDatabase.getDatabase(application);
        goalDao = db.goalDao();
        goalEntities = goalDao.selectAllGoals();
    }

    public List<GoalEntity> getAllGoals() {
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
