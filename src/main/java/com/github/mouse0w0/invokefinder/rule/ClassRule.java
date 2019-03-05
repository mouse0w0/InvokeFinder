package com.github.mouse0w0.invokefinder.rule;

import com.github.mouse0w0.invokefinder.misc.MethodInvoke;

import java.util.Set;

public class ClassRule extends RuleBase {

    private final Set<String> classes;

    public ClassRule(Level level, String message, Set<String> packages) {
        super(level, message);
        this.classes = packages;
    }

    @Override
    public boolean test(MethodInvoke invoke) {
        return classes.stream().anyMatch(s -> invoke.getInvokedMethodOwner().equals(s));
    }
}
