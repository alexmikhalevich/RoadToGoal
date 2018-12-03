package com.mikhalevich.roadtogoal.domain;

import lombok.Getter;

public class GoalEntityProxy extends GoalEntity {
    @Getter
    protected GoalEntity proxied;

    GoalEntityProxy(GoalEntity proxied) {
        this.proxied = proxied;
    }
}
