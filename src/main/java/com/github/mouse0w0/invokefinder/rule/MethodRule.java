package com.github.mouse0w0.invokefinder.rule;

import com.github.mouse0w0.invokefinder.misc.MethodInvoke;

public class MethodRule extends RuleBase {

    private final String className;
    private final String method;

    public MethodRule(Level level, String message, String className, String method) {
        super(level, message);
        this.className = className;
        this.method = method;
    }

    @Override
    public boolean test(MethodInvoke invoke) {
        return invoke.getInvokedMethodOwner().equals(className) && invoke.getInvokedMethod().equals(method);
    }
}
