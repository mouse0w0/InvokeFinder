package com.github.mouse0w0.invokefinder.rule;

import com.github.mouse0w0.invokefinder.misc.MethodInvoke;

public interface Rule {

    boolean test(MethodInvoke invoke);

    Level getLevel();

    String getMessage();
}
