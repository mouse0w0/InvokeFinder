package com.github.mouse0w0.invokefinder.misc;

import com.github.mouse0w0.invokefinder.rule.Level;

public interface Logger {

    void log(Level level, String message, MethodInvoke invoke);
}
