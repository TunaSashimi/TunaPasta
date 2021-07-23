package current;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.IntBuffer;
import java.nio.channels.FileChannel;

/**
 * @author Tunasashimi
 * @date 2018/12/6 15:32
 * @Copyright 2018 TunaSashimi. All rights reserved.
 * @Description
 */
public class MappedIO {

    private static int numOfInts = 4000000; //四百万
    private static int numOfUbuffInts = 20000; //两千

    //
    private abstract static class Tester {
        private String name;

        public Tester(String name) {
            this.name = name;
        }

        public static void main(String[] args) {
            for (Tester test : testers) {
                test.runTest();
            }
        }

        public void runTest() {
            System.out.println(name + ": ");
            try {
                long start = System.nanoTime();
                test();
                double duration = System.nanoTime() - start;
                System.out.format("%.5f\n", duration / 1.0e9);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        public abstract void test() throws IOException;
    }

    //
    private static Tester[] testers = {
            new Tester("Stream Write") {
                public void test() throws IOException {
                    DataOutputStream dos = new DataOutputStream(new BufferedOutputStream(new FileOutputStream(new File("temp.tmp"))));
                    for (int i = 0; i < numOfInts; i++)
                        dos.writeInt(i);
                    dos.close();
                }
            },
            new Tester("Mapped Write") {
                @Override
                public void test() throws IOException {
                    FileChannel fc = new RandomAccessFile("temp.tmp", "rw").getChannel();
                    IntBuffer ib = fc.map(FileChannel.MapMode.READ_WRITE, 0, fc.size()).asIntBuffer();
                    for (int i = 0; i < numOfInts; i++)
                        ib.put(i);
                    fc.close();
                }
            },
            new Tester("Stream Read") {
                @Override
                public void test() throws IOException {
                    DataInputStream dis = new DataInputStream(new BufferedInputStream(new FileInputStream("temp.tmp")));
                    for (int i = 0; i < numOfUbuffInts; i++)
                        dis.readInt();
                    dis.close();
                }
            },
            new Tester("Mapped Read") {
                @Override
                public void test() throws IOException {
                    FileChannel fc = new FileInputStream(new File("temp.tmp")).getChannel();
                    IntBuffer ib = fc.map(FileChannel.MapMode.READ_ONLY, 0, fc.size()).asIntBuffer();
                    while (ib.hasRemaining())
                        ib.get();
                    fc.close();
                }
            },
            new Tester("Stream Read/Write") {
                @Override
                public void test() throws IOException {
                    RandomAccessFile raf = new RandomAccessFile(new File("temp.tmp"), "rw");
                    raf.writeInt(1);
                    for (int i = 0; i < numOfUbuffInts; i++) {
                        raf.seek(raf.length() - 4);
                        raf.writeInt(raf.readInt());
                    }
                    raf.close();
                }
            },
            new Tester("Mapped Read/Write") {
                @Override
                public void test() throws IOException {
                    FileChannel fc = new RandomAccessFile(new File("temp.tmp"), "rw").getChannel();
                    IntBuffer ib = fc.map(FileChannel.MapMode.READ_WRITE, 0, fc.size()).asIntBuffer();
                    ib.put(0);
                    for (int i = 1; i < numOfUbuffInts; i++)
                        ib.put(ib.get(i - 1));
                    fc.close();
                }
            }
    };
}