package com.github.mouse0w0.invokefinder.analysis;

import com.github.mouse0w0.invokefinder.misc.MethodInvoke;
import com.github.mouse0w0.invokefinder.misc.MethodInvokeConsumer;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.MethodVisitor;

public class ClassAnalyzer extends ClassVisitor {

    protected final MethodInvokeConsumer methodInvokeConsumer;

    public ClassAnalyzer(int api, MethodInvokeConsumer methodInvokeConsumer) {
        super(api);
        this.methodInvokeConsumer = methodInvokeConsumer;
    }

    public ClassAnalyzer(int api, ClassVisitor classVisitor, MethodInvokeConsumer methodInvokeConsumer) {
        super(api, classVisitor);
        this.methodInvokeConsumer = methodInvokeConsumer;
    }

    private String className;
    private String source;

    private String visitingMethodName;
    private String visitingMethodDescriptor;

    @Override
    public void visit(int version, int access, String name, String signature, String superName, String[] interfaces) {
        this.className = name;
        super.visit(version, access, name, signature, superName, interfaces);
    }

    @Override
    public void visitSource(String source, String debug) {
        this.source = source;
        super.visitSource(source, debug);
    }

    @Override
    public MethodVisitor visitMethod(int access, String name, String descriptor, String signature, String[] exceptions) {
        visitingMethodName = name;
        visitingMethodDescriptor = descriptor;
        return new MethodAnalyzer(api, super.visitMethod(access, name, descriptor, signature, exceptions), this);
    }

    public void acceptMethodInsn(int lineNumber, String invokedMethodOwner, String invokedMethod, String invokedMethodDescriptor) {
        methodInvokeConsumer.accept(new MethodInvoke(className, source, visitingMethodName, visitingMethodDescriptor, lineNumber, invokedMethodOwner, invokedMethod, invokedMethodDescriptor));
    }
}
