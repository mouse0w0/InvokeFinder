package com.github.mouse0w0.invokefinder.misc;

import com.github.mouse0w0.invokefinder.rule.*;
import com.moandjiezana.toml.Toml;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class ConfigLoader {

    public static List<Rule> readRules(Path path) throws IOException {
        Toml config = new Toml();
        try (InputStream inputStream = Files.newInputStream(path)) {
            config.read(inputStream);
        }
        return readRules(config);
    }

    public static List<Rule> readRules(Toml config) {
        List<Rule> rules = new ArrayList<>();
        for (Toml rule : config.getTables("rules")) {
            String type = rule.getString("type");
            Level level = Level.valueOf(rule.getString("level").toUpperCase());
            String message = rule.getString("message");
            switch (type.toLowerCase()) {
                case "method":
                    rules.add(new MethodRule(level, message, rule.getString("class"), rule.getString("method")));
                    break;
                case "class":
                    rules.add(new ClassRule(level, message, Set.copyOf(rule.getList("classes"))));
                    break;
                case "package":
                    rules.add(new PackageRule(level, message, Set.copyOf(rule.getList("packages"))));
                    break;
                default:
                    break;
            }
        }
        return rules;
    }
}
