package com.mikhalevich.roadtogoal.domain;

import android.app.Application;

import java.util.ArrayList;
import java.util.List;

public class GoalRepository {

    private GoalDao goalDao;
    private List<GoalEntity> goalEntities;
    private static GoalRepository instance;

    private GoalRepository(Application application) {
        goalEntities = new ArrayList<>();

        GoalRoomDatabase db = GoalRoomDatabase.getDatabase(application);
        goalDao = db.goalDao();
    }

    private <GoalEntityT extends GoalEntityProxy>
    List<GoalEntityT> castGoals(final Class<GoalEntityT> kind) {
        List<GoalEntityT> result = new ArrayList<>();
        try {
            for (GoalEntity g : goalEntities) {
                GoalEntityT entity =
                        kind.getDeclaredConstructor(GoalEntity.class).newInstance(g);
                result.add(entity);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return result;
    }

    public static synchronized void initRepository(Application application) {
        if (instance == null)
            instance = new GoalRepository(application);
    }

    public static GoalRepository getRepository() {
        assert (instance != null);
        return instance;
    }

    public <GoalEntityT extends GoalEntityProxy>
    List<GoalEntityT> getAllGoals(boolean forceReload, final Class<GoalEntityT> kind) {
        if (forceReload)
            goalEntities = goalDao.selectAllGoals();

        return castGoals(kind);
    }

    public <GoalEntityT extends GoalEntityProxy>
    void insertGoal(final GoalEntityT goalEntity) {
        goalEntities.add(goalEntity.getProxied());
        goalDao.insert(goalEntity.getProxied());
    }

    public <GoalEntityT extends GoalEntityProxy>
    void deleteGoal(final GoalEntityT goalEntity) {
        goalEntities.remove(goalEntity.getProxied());
        goalDao.delete(goalEntity.getProxied());
    }
}