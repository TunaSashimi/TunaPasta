package com.tunaPasta18;

import org.junit.Test;
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
import java.util.Locale;

/**
 * @author TunaSashimi
 * @date 2021/7/26 17:32
 * @Copyright 2021 TunaSashimi. All rights reserved.
 * @Description
 */
class ASMUnitTest {
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

    /**
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
         * 当任何一个方法执行时就回调这个apiIn
         *
         * @param access
         * @param name
         * @param descriptor
         * @param signature
         * @param exceptions
         * @return
         */
        @Override
        public MethodVisitor visitMethod(int access, String name, String descriptor, String signature, String[] exceptions) {
            MethodVisitor methodVisitor = super.visitMethod(access, name, descriptor, signature, exceptions);
            return new MethodVisitorTest(api, methodVisitor, access, name, descriptor);
        }
    }

    /**
     * 用来分析方法的信息
     */
    static class MethodVisitorTest extends AdviceAdapter {

        /**
         * Constructs a new {@link AdviceAdapter}.
         *
         * @param api           the ASM API version implemented by this visitor. Must be one of {@link
         *                      Opcodes#ASM4}, {@link Opcodes#ASM5}, {@link Opcodes#ASM6} or {@link Opcodes#ASM7}.
         * @param methodVisitor the method visitor to which this adapter delegates calls.
         * @param access        the method's access flags (see {@link Opcodes}).
         * @param name          the method's name.
         * @param descriptor    the method's descriptor (see {@link Type Type}).
         */
        protected MethodVisitorTest(int api, MethodVisitor methodVisitor, int access, String name, String descriptor) {
            super(api, methodVisitor, access, name, descriptor);
        }

        int s;
        int e;

        @Override
        protected void onMethodEnter() {
            super.onMethodEnter();

            invokeStatic(Type.getType("Ljava/lang/System"), new Method("currentTimeMillis", "()J"));
            s = newLocal(Type.LONG_TYPE);
            storeLocal(s);

        }

        @Override
        protected void onMethodExit(int opcode) {
            super.onMethodExit(opcode);

            invokeStatic(Type.getType("Ljava/lang/System"), new Method("currentTimeMillis", "()J"));
            e = newLocal(Type.LONG_TYPE);
            storeLocal(e);
        }
    }
}
