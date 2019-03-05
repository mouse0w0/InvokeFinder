package com.github.mouse0w0.invokefinder.rule;

import com.github.mouse0w0.invokefinder.misc.MethodInvoke;

import java.util.Set;

public class PackageRule extends RuleBase {

    private final Set<String> packages;

    public PackageRule(Level level, String message, Set<String> packages) {
        super(level, message);
        this.packages = packages;
    }

    @Override
    public boolean test(MethodInvoke invoke) {
        return packages.stream().anyMatch(s -> invoke.getInvokedMethodOwner().startsWith(s));
    }
}
