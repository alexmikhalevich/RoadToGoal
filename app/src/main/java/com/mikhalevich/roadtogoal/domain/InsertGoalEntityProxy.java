package com.mikhalevich.roadtogoal.domain;

public class InsertGoalEntityProxy extends GoalEntityProxy {

    public InsertGoalEntityProxy(GoalEntity proxied) {
        super(proxied);
        proxied.hasChildren = false;
    }

    public void setName(String name) {
        proxied.name = name;
    }
    public void setParent(Integer parentId) { proxied.parentId = parentId; }
}
