package com.mikhalevich.roadtogoal.domain;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

@Dao
public interface GoalDao {
    @Insert
    void insert(GoalEntity goalEntity);

    @Query("SELECT * FROM goals_table WHERE parent == :parentId")
    List<GoalEntity> selectAllGoals(Integer parentId);

    @Delete
    void delete(GoalEntity goalEntity);
}
