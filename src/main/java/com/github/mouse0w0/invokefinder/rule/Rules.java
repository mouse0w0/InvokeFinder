package com.github.mouse0w0.invokefinder.rule;

import com.github.mouse0w0.invokefinder.misc.Logger;
import com.github.mouse0w0.invokefinder.misc.MethodInvoke;
import com.github.mouse0w0.invokefinder.misc.MethodInvokeConsumer;

import java.util.List;

public class Rules implements MethodInvokeConsumer {

    private final Logger logger;
    private final List<Rule> rules;

    public Rules(Logger logger, List<Rule> rules) {
        this.logger = logger;
        this.rules = rules;
    }

    @Override
    public void accept(MethodInvoke invoke) {
        for (var rule : rules) {
            if (rule.test(invoke)) {
                logger.log(rule.getLevel(), rule.getMessage(), invoke);
            }
        }
    }
}
