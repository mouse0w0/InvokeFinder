package com.github.mouse0w0.invokefinder.misc;

public class MethodInvoke {

    private final String className;
    private final String classSource;
    private final String method;
    private final String methodDescriptor;
    private final int lineNumber;
    private final String invokedMethodOwner;
    private final String invokedMethod;
    private final String invokedMethodDescriptor;

    public MethodInvoke(String className, String classSource, String method, String methodDescriptor, int lineNumber, String invokedMethodOwner, String invokedMethod, String invokedMethodDescriptor) {
        this.className = className.replace('/', '.');
        this.classSource = classSource;
        this.method = method;
        this.methodDescriptor = methodDescriptor;
        this.lineNumber = lineNumber;
        this.invokedMethodOwner = invokedMethodOwner.replace('/', '.');
        this.invokedMethod = invokedMethod;
        this.invokedMethodDescriptor = invokedMethodDescriptor;
    }

    public String getClassName() {
        return className;
    }

    public String getClassSource() {
        return classSource;
    }

    public String getMethod() {
        return method;
    }

    public String getMethodDescriptor() {
        return methodDescriptor;
    }

    public int getLineNumber() {
        return lineNumber;
    }

    public String getInvokedMethodOwner() {
        return invokedMethodOwner;
    }

    public String getInvokedMethod() {
        return invokedMethod;
    }

    public String getInvokedMethodDescriptor() {
        return invokedMethodDescriptor;
    }
}
