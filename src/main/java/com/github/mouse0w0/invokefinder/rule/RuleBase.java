package com.github.mouse0w0.invokefinder.rule;

public abstract class RuleBase implements Rule {

    private final Level level;
    private final String message;

    public RuleBase(Level level, String message) {
        this.level = level;
        this.message = message;
    }

    public Level getLevel() {
        return level;
    }

    public String getMessage() {
        return message;
    }
}
