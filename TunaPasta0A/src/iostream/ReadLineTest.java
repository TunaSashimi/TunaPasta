package iostream;

/**
 * 用IO包中的类打开并读取一个文本文件，每次读取一行内容。
 * 将每行作为一个String输入放入String数组里面返回。
 */
public class ReadLineTest {
    public String[] r(java.io.InputStream in) throws java.io.IOException {
        String[] arr = new String[0];
        java.io.BufferedReader br = new java.io.BufferedReader(new java.io.InputStreamReader(in));
        for (int i = 0; ; i++) {
            String s = br.readLine();
            if (s == null)
                break;
            arr = java.util.Arrays.copyOf(arr, arr.length + 1);
            arr[i] = s;
        }
        return arr;
    }

    public static void main(String[] args) throws java.io.IOException {
        String[] arr = new ReadLineTest().r(new java.io.FileInputStream("src/iostream/readlineTest.java"));
        for (String str : arr)
            System.out.println(str);
    }
}
//		String[]brr=new String[i+1];
//		System.arraycopy(arr, 0, brr, 0,arr.length);
//		arr = new String[1+i];
//		System.arraycopy(brr, 0, arr, 0,brr.length);
