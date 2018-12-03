package com.mikhalevich.roadtogoal.domain;

public class InsertGoalEntityProxy extends GoalEntityProxy {

    public InsertGoalEntityProxy(GoalEntity proxied) {
        super(proxied);
    }

    public void setName(String name) {
        proxied.name = name;
    }
}
