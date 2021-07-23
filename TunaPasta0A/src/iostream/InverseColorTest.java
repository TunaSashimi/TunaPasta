package iostream;

/**
 * 把一个BMP文件反色。BMP 前54个字节 跳过去。23(原色) ==> ff - 23(反色)
 */
public class InverseColorTest {
    public static void main(String[] args) throws Exception {
        java.io.RandomAccessFile raf = new java.io.RandomAccessFile("f:/1234.bmp", "rw");
        byte[] b = new byte[(int) raf.length()];//实时计算大小
        int l = raf.read(b);
        raf.seek(54);
        for (int i = 54; i < l; i++)
            raf.write(0xff - b[i]);
    }
}
