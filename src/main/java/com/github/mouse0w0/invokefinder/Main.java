package com.github.mouse0w0.invokefinder;

import com.github.mouse0w0.invokefinder.analysis.ClassAnalyzer;
import com.github.mouse0w0.invokefinder.misc.ConfigLoader;
import com.github.mouse0w0.invokefinder.misc.SimpleLogger;
import com.github.mouse0w0.invokefinder.rule.Level;
import com.github.mouse0w0.invokefinder.rule.Rules;
import com.google.common.collect.Streams;
import joptsimple.OptionParser;
import joptsimple.OptionSet;
import joptsimple.OptionSpec;
import org.objectweb.asm.ClassReader;
import org.objectweb.asm.Opcodes;

import java.nio.file.Path;
import java.util.List;
import java.util.jar.JarFile;

public class Main {

    public static void main(String[] args) throws Exception {
        OptionParser parser = new OptionParser();

        OptionSpec<String> targetSpec = parser.acceptsAll(List.of("target", "t"), "Target file.").withRequiredArg();
        OptionSpec<String> configSpec = parser.acceptsAll(List.of("config", "c"), "Config file.").withRequiredArg();
        OptionSpec<String> outputSpec = parser.acceptsAll(List.of("output", "o"), "Output file.").withRequiredArg().defaultsTo("result.log");
        OptionSpec<String> levelSpec = parser.acceptsAll(List.of("level", "l"), "Output level.").withRequiredArg().defaultsTo("INFO");

        OptionSet optionSet = parser.parse(args);

        Path target = Path.of(optionSet.valueOf(targetSpec));
        Path config = Path.of(optionSet.valueOf(configSpec)); // TODO: support multi config.
        Path output = Path.of(optionSet.valueOf(outputSpec));
        Level level = Level.valueOf(optionSet.valueOf(levelSpec).toUpperCase());

        System.out.println("Target: " + target.toAbsolutePath());
        System.out.println("Config: " + config.toAbsolutePath());
        System.out.println("Output: " + output.toAbsolutePath());

        SimpleLogger logger = new SimpleLogger(output, level);
        Rules rules = new Rules(logger, ConfigLoader.readRules(config));
        ClassAnalyzer analyzer = new ClassAnalyzer(Opcodes.ASM7, rules);

        try (var jarFile = new JarFile(target.toFile())) {
            var it = Streams.stream(jarFile.entries().asIterator())
                    .filter(jarEntry -> jarEntry.getName().endsWith(".class"))
                    .iterator();
            while (it.hasNext()) {
                try (var inputStream = jarFile.getInputStream(it.next())) {
                    var cr = new ClassReader(inputStream);
                    cr.accept(analyzer, 0);
                }
            }
        }

        logger.close();
        System.out.println("Finished.");
    }
}
