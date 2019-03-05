package com.github.mouse0w0.invokefinder.rule;

public enum Level {

    INFO(0),
    WARN(25),
    DANGER(50),
    DISASTER(75),
    LASTDAY(100);

    public final int priority;

    Level(int priority) {
        this.priority = priority;
    }
}
