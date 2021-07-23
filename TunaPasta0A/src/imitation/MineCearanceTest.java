package imitation;

public class MineCearanceTest {
    static int[][] mPanel;

    public static void main(String[] args) {
        getMpanel();
        showStatus();
        mineClear();
    }

    // 按输入的参数,获得设置好的布雷面板并存入mpanel[][]内~
    public static void getMpanel() {
        for (; ; )
            try {
                System.out.println("请用.分隔输入布雷面板行数,列数和地雷数,布置地雷密度不超过75%）");
                String[] s = new java.util.Scanner(System.in).nextLine().split("\\.");
                int r = Integer.parseInt(s[0]), c = Integer.parseInt(s[1]), n = Integer.parseInt(s[2]);
                if (r > 0 && c > 0 && n > 0 && n <= r * c * 0.75) {
                    mPanel = new MineHelperTest(new int[r][c], n).layoutMines();
                    break;
                }
                throw new Exception();        // 只要不break出去,总会有错误产生~~
            } catch (Exception e) {
                System.err.println("输入有错误,请检查后重新输入");
            }
    }

    // 显示当前的扫雷状况
    public static void showStatus() {
        System.out.println();
        for (int i = 0; i < mPanel[0].length; i++)
            System.out.printf((i == 0) ? "%3d" : "%2d", i);
        System.out.println();
        for (int i = 0; i < mPanel.length; i++) {
            System.out.printf("%2d", i);
            for (int j = 0; j < mPanel[0].length; j++) {
                if (mPanel[i][j] <= 8)        //	未被用户标记的区域
                    System.out.print("*" + " ");
                if (mPanel[i][j] >= 10)        //	被用户标记且不为雷区域
                    System.out.print(mPanel[i][j] % 10 + " ");
            }
            System.out.println();
        }
    }

    //扫雷的过程~
    public static void mineClear() {
        label:
        for (; ; ) {
            //雷数+用户标记数=总区域数,说明扫雷完成,跳出循环~
            int total = 0;
            for (int i = 0; i < mPanel.length; i++)
                for (int j = 0; j < mPanel[0].length; j++) {
                    if (mPanel[i][j] == -1 || mPanel[i][j] >= 10)
                        total++;
                    if (total == mPanel.length * mPanel[0].length) {
                        System.err.println("你赢了~");
                        break label;
                    }
                }
            //反复执行的标记+刷新的过程~
            try {
                System.out.println();
                System.out.println("请用.分隔输入布雷面板行数和列数");
                String[] s = new java.util.Scanner(System.in).nextLine().split("\\.");
                int i = Integer.parseInt(s[0]), j = Integer.parseInt(s[1]);
                if (mPanel[i][j] == -1) {
                    System.err.println("游戏失败");
                    System.exit(0);
                } else if (mPanel[i][j] == 0) {
                    mPanel[i][j] += 10;
                    if (i - 1 >= 0)
                        mPanel[i - 1][j] += 10;
                    if (i - 1 >= 0 && j - 1 >= 0)
                        mPanel[i - 1][j - 1] += 10;
                    if (i - 1 >= 0 && j + 1 < mPanel[0].length)
                        mPanel[i - 1][j + 1] += 10;
                    if (i + 1 < mPanel.length)
                        mPanel[i + 1][j] += 10;
                    if (i + 1 < mPanel.length && j - 1 >= 0)
                        mPanel[i + 1][j - 1] += 10;
                    if (i + 1 < mPanel.length && j + 1 < mPanel[0].length)
                        mPanel[i + 1][j + 1] += 10;
                    if (j - 1 >= 0)
                        mPanel[i][j - 1] += 10;
                    if (j + 1 < mPanel[0].length)
                        mPanel[i][j + 1] += 10;
                    mPanel[i][j] = mPanel[i][j] + 10;
                    showStatus();
                } else if (mPanel[i][j] >= 10) {
                    System.err.println("此处已标记为安全区域");
                } else {
                    mPanel[i][j] += 10;
                    showStatus();
                }
            } catch (Exception e) {
                System.err.println("输入有误,请重新输入");
            }
        }
    }
}
