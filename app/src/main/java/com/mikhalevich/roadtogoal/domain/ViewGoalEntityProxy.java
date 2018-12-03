package com.mikhalevich.roadtogoal.domain;

public class ViewGoalEntityProxy extends GoalEntityProxy {

    ViewGoalEntityProxy(GoalEntity proxied) {
        super(proxied);
    }

    public String getName() { return proxied.name; }
}