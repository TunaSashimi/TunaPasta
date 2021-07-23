package iostream;

public class TotalTest {
    public static void main(String[] args) throws Exception {
        loop("src/iostream");// 入口路径,如果不是绝对路径,则为相对路径
    }

    public static void loop(String dir) throws Exception {
        java.io.File f = new java.io.File(dir);
        if (!f.exists())
            System.err.println("目录或文件不存在,请修改入口路径");
        else if (f.isDirectory()) {
            System.out.println("目录  " + dir + "  中文件列表如下: ");
            String[] s = f.list();
            for (String i : s)
                loop(dir + "/" + i);
        } else {
            System.out.println(dir);
            byte[] b = new byte[2048];
            int c = new java.io.FileInputStream(dir).read(b);
            java.io.OutputStream os = new java.io.FileOutputStream("src/iostream/totalTest.java", true);//输出文件位置
            os.write(b, 0, c = (c != -1) ? c : 0);//文件内容为空,返回c=-1;
        }
    }
}