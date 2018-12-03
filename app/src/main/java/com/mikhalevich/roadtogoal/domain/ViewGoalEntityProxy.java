package com.mikhalevich.roadtogoal.domain;

public class ViewGoalEntityProxy extends GoalEntityProxy {

    ViewGoalEntityProxy(GoalEntity proxied) {
        super(proxied);
    }

    public Integer getId() { return proxied.id; }
    public String getName() { return proxied.name; }
    public boolean hasChildren() { return proxied.hasChildren; }
}