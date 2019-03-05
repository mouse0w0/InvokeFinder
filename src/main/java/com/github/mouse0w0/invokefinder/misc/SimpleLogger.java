package com.github.mouse0w0.invokefinder.misc;

import com.github.mouse0w0.invokefinder.rule.Level;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Path;

public class SimpleLogger implements Logger {

    private final Writer writer;
    private final Level level;

    public SimpleLogger(Path path, Level level) {
        this.level = level;
        try {
            if (!Files.exists(path))
                Files.createFile(path);
            this.writer = new OutputStreamWriter(Files.newOutputStream(path));
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    @Override
    public void log(Level level, String message, MethodInvoke invoke) {
        if (level.priority >= this.level.priority) {
            try {
                writer.write("[" + level + "] " + message + "\n");
                writer.write("Invoke source: " + invoke.getClassName() + "." + invoke.getMethod() + invoke.getInvokedMethodDescriptor() + " (" + invoke.getClassSource() + ":" + invoke.getLineNumber() + ")\n");
                writer.write("Invoked method: " + invoke.getInvokedMethodOwner() + "." + invoke.getInvokedMethod() + invoke.getInvokedMethodDescriptor() + "\n");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void close() {
        try {
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
