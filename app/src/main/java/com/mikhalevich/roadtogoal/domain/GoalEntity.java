package com.mikhalevich.roadtogoal.domain;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Entity(tableName = "goals_table")
@Getter
@Setter
@AllArgsConstructor
public class GoalEntity {
    public GoalEntity() {}

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    protected Integer id;

    @ColumnInfo(name = "name")
    protected String name;

}
