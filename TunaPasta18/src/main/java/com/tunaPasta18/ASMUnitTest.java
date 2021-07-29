package com.tunaPasta18;

import org.junit.Test;
import org.objectweb.asm.AnnotationVisitor;
import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.Type;
import org.objectweb.asm.commons.AdviceAdapter;
import org.objectweb.asm.commons.Method;

import java.io.FileInputStream;
import java.io.FileOutputStream;

/**
 * @author TunaSashimi
 * @date 2021/7/26 17:32
 * @Copyright 2021 TunaSashimi. All rights reserved.
 * @Description
 */
public class ASMUnitTest {
    @Test
    public void test() {
        try {
            FileInputStream fis = new FileInputStream("/Users/Tunasashimi/Develop/workspace-studio/TunaPasta/TunaPasta18/src/main/java/com/tunaPasta18/InjectTest.class");

            //获取一个分析器 用于分析class文件
            ClassReader classReader = new ClassReader(fis);
            ClassWriter classWriter = new ClassWriter(ClassWriter.COMPUTE_FRAMES);

            //开始插桩
            classReader.accept(new ClassVisitorTest(Opcodes.ASM7, classWriter), ClassReader.EXPAND_FRAMES);
            byte[] bytes = classWriter.toByteArray();

            FileOutputStream fos = new FileOutputStream("/Users/Tunasashimi/Develop/workspace-studio/TunaPasta/TunaPasta18/src/main/java/com/tunaPasta18/InjectTest2.class");
            fos.write(bytes);
            fos.close();
            fis.close();

        } catch (Exception e) {

        }
    }

    /*
     * 用来分析类信息
     */
    static class ClassVisitorTest extends ClassVisitor {
        public ClassVisitorTest(int api) {
            super(api);
        }

        public ClassVisitorTest(int api, ClassVisitor classVisitor) {
            super(api, classVisitor);
        }

        /**
         * 当任何一个方法执行时就回调这个api
         */
        @Override
        public MethodVisitor visitMethod(int access, String name, String descriptor, String signature, String[] exceptions) {
            MethodVisitor methodVisitor = super.visitMethod(access, name, descriptor, signature, exceptions);
            return new MethodVisitorTest(api, methodVisitor, access, name, descriptor);
        }

        @Override
        public AnnotationVisitor visitAnnotation(String descriptor, boolean visible) {
            return super.visitAnnotation(descriptor, visible);
        }
    }

    /*
     * 用来分析方法的信息
     */
    static class MethodVisitorTest extends AdviceAdapter {
        protected MethodVisitorTest(int api, MethodVisitor methodVisitor, int access, String name, String descriptor) {
            super(api, methodVisitor, access, name, descriptor);
        }

        int startTime, endTime;

        @Override
        protected void onMethodEnter() {
            super.onMethodEnter();
            if (!inject) {
                return;
            }
            invokeStatic(Type.getType("Ljava/lang/System"), new Method("currentTimeMillis", "()J"));
            startTime = newLocal(Type.LONG_TYPE);
            storeLocal(startTime);
        }

        @Override
        protected void onMethodExit(int opcode) {
            super.onMethodExit(opcode);
            if (!inject) {
                return;
            }

            //
            invokeStatic(Type.getType("Ljava/lang/System"), new Method("currentTimeMillis", "()J"));
            endTime = newLocal(Type.LONG_TYPE);
            storeLocal(endTime);

            //
//            getStatic(Type.getType("Ljava/lang/System"), "out", Type.getType("Ljava/io/PrintStream"));
//            newInstance(Type.getType("Ljava/lang/StringBuilder"));
//
//            dup();
//
//            invokeConstructor(Type.getType("Ljava/lang/StringBuilder"), new Method("<init>", "()v"));
//            visitLdcInsn("execute time=");

        }

        boolean inject = true;

        /*
         * 每读到一个注解就执行一次
         */
        @Override
        public AnnotationVisitor visitAnnotation(String descriptor, boolean visible) {
            System.out.println(getName() + "==>" + descriptor);
            if ("Lcom/tunaPasta18/ASMTest;".equals(descriptor)) {
                inject = true;
            }
            return super.visitAnnotation(descriptor, visible);
        }
    }
}
