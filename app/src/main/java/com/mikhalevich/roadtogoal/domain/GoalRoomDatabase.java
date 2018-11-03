package com.mikhalevich.roadtogoal.domain;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

@Database(entities = {GoalEntity.class}, version = 1)
public abstract class GoalRoomDatabase extends RoomDatabase {

    public abstract GoalDao goalDao();

    private static volatile GoalRoomDatabase INSTANCE;

    static GoalRoomDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (GoalRoomDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            GoalRoomDatabase.class, "goals_database")
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}
