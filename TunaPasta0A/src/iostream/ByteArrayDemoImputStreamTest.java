package iostream;

public class ByteArrayDemoImputStreamTest {
    public static void main(String[] args) {
        String s = "ABcdef";// 要求将大写字符串转换为小写
        byte[] b = s.getBytes();
        java.io.ByteArrayInputStream in = new java.io.ByteArrayInputStream(b);
        java.io.ByteArrayOutputStream out = new java.io.ByteArrayOutputStream();
        // 大写转小写,只能一个字节一个字节的读
        int c;
        // public int read()从输入流中读取下一个数据字节返回一个 0 到 255 范围内的 int 字节值。
        while ((c = in.read()) != -1) { // 如果因为到达流末尾而没有可用的字节，则返回值 -1。
            int ch = Character.toLowerCase((char) c);
            // public void write(int b)将指定的字节写入此 byte 数组输出流。
            out.write(ch);
        }
        System.out.println("内容为:" + new String(out.toByteArray()));
        //内存中的字节数组作为源的IO流
    }
}
