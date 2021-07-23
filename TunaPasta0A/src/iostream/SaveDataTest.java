package iostream;

public class SaveDataTest {
    // 好像是用BuffereWriter来做的花,文件是直接写到末尾的
    public static void main(String[] args) {
        // 启动线程
        // comeOverJava
        java.util.Scanner sc = new java.util.Scanner(System.in);
        String[][] a = new String[5][6];
        for (int i = 1; i < 6; i++) {
            System.out.print("请输入第" + i + "个学生的学号：");
            a[i - 1][0] = sc.nextLine();
            System.out.print("请输入第" + i + "个学生的姓名：");
            a[i - 1][1] = sc.nextLine();
            for (int j = 1; j < 4; j++) {
                System.out.print("请输入该学生的第" + j + "个成绩：");
                a[i - 1][j + 1] = sc.nextLine();
            }
            System.out.println("\n");
        }
        float avg;
        int sum;
        for (int i = 0; i < 5; i++) {
            sum = 0;
            for (int j = 2; j < 5; j++) {
                sum = sum + Integer.parseInt(a[i][j]);
            }
            avg = (float) sum / 3;
            a[i][5] = String.valueOf(avg);
        }
        String s1;
        try {
            java.io.File f = new java.io.File("f:/stud");
            if (f.exists()) {
                System.out.println("文件存在");
            } else {
                System.out.println("文件不存在，正在创建文件");
                f.createNewFile();
            }
            java.io.BufferedWriter output = new java.io.BufferedWriter(
                    new java.io.FileWriter(f));
            for (int i = 0; i < 5; i++) {
                for (int j = 0; j < 6; j++) {
                    s1 = a[i][j] + "\r\n";
                    output.write(s1);
                }
            }
            output.close();
            System.out.println("数据已写入f盘文件stud中！");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}