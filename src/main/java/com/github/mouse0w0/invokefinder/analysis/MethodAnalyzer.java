package com.github.mouse0w0.invokefinder.analysis;

import org.objectweb.asm.Label;
import org.objectweb.asm.MethodVisitor;

public class MethodAnalyzer extends MethodVisitor {

    protected final ClassAnalyzer classAnalyzer;

    private int currentLineNumber = -1;

    public MethodAnalyzer(int api, ClassAnalyzer classAnalyzer) {
        super(api);
        this.classAnalyzer = classAnalyzer;
    }

    public MethodAnalyzer(int api, MethodVisitor methodVisitor, ClassAnalyzer classAnalyzer) {
        super(api, methodVisitor);
        this.classAnalyzer = classAnalyzer;
    }

    @Override
    public void visitLineNumber(int line, Label start) {
        currentLineNumber = line;
        super.visitLineNumber(line, start);
    }

    @Override
    public void visitMethodInsn(int opcode, String owner, String name, String descriptor, boolean isInterface) {
        classAnalyzer.acceptMethodInsn(currentLineNumber, owner, name, descriptor);
        super.visitMethodInsn(opcode, owner, name, descriptor, isInterface);
    }
}
