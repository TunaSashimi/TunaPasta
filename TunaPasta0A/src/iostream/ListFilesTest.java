package iostream;

import java.io.*;

//列出某一目录下所有文件和包含pathname时的文件
public class ListFilesTest {
    public static void main(String[] args) {
        System.out.println("列出src下所有的文件：");// 包括文件夹
        String[] allfiles = new File("src/").list();
        for (String f : allfiles)
            System.out.println(f);

        System.out.println("路径文件夹名包含e,文件名含de的文件：");

        String[] files = new File("src/iostream").list(new FilenameFilter() {
            public boolean accept(File f, String s) {//二参的
                return f.getName().matches(".*e.*") & s.matches(".*de.*");
            }
        });
        for (String s : files)
            System.out.println(s);
    }
}
//  只能返回文件名符合规则的listFiles(FileFilter filter) 

//		File[] files = new File("d:/").listFiles(new FileFilter() {
//			public boolean accept(File f) {
//				return f.getName().matches(".*are.*");// 正则,包含are字串的文件名
//			}
//		});
//		for (File f : files)
//			System.out.println(f.getName());
