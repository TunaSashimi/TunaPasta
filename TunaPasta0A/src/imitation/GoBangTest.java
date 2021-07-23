package imitation;

//扫描落子点版~
public class GoBangTest {
    static char[][] a = new char[15][15];

    public static void main(String[] args) {
        char f = 'x';
        System.out.println("=============简单版的五子棋游戏==============");
        label:
        for (chessboard(); ; )
            try {
                System.out.println(f + "方请落子！请用.分隔横纵坐标输入");
                String[] s = new java.util.Scanner(System.in).nextLine().split("\\.");
                int x = Integer.parseInt(s[0]), y = Integer.parseInt(s[1]);
                if (a[x][y] != '\u0000')
                    System.err.println("此处已落子,请重新输入");
                else {
                    a[x][y] = (f == 'x') ? 'x' : 'o';
                    chessboard();
                    for (int i = x - 4; i <= x; i++)
                        for (int j = y - 4; j <= y; j++)
                            if (i >= 0 && i + 4 < a.length && j >= 0 && (Math.abs(a[i][j] + a[i + 1][j] + a[i + 2][j] + a[i + 3][j] + a[i + 4][j] - 577.5) == 22.5)
                                    || i >= 0 && j >= 0 && j + 4 < a[i].length && (Math.abs(a[i][j] + a[i][j + 1] + a[i][j + 2] + a[i][j + 3] + a[i][j + 4] - 577.5) == 22.5)
                                    || i >= 0 && i + 4 < a.length && j >= 0 && j + 4 < a[i].length && (Math.abs(a[i][j] + a[i + 1][j + 1] + a[i + 2][j + 2] + a[i + 3][j + 3] + a[i + 4][j + 4] - 577.5) == 22.5)
                                    || i + 4 - 2 * (4 - (x - i)) >= 0 && i + 8 - 2 * (4 - (x - i)) < a.length && j >= 0 && j + 4 < a[i + 4 - 2 * (4 - (x - i))].length && (Math.abs(a[i + 8 - 2 * (4 - (x - i))][j] + a[i + 7 - 2 * (4 - (x - i))][j + 1] + a[i + 6 - 2 * (4 - (x - i))][j + 2] + a[i + 5 - 2 * (4 - (x - i))][j + 3] + a[i + 4 - 2 * (4 - (x - i))][j + 4] - 577.5) == 22.5)) {
                                System.err.println(f + "方赢");
                                break label;
                            }
                    f = (f == 'x') ? 'o' : 'x';
                }
            } catch (Exception e) {
                System.err.println("坐标有误,请重新输入！");
            }
        System.err.println("游戏结束，退出游戏");
    }

    public static void chessboard() {
        for (int i = 0; i < a.length; i++)
            System.out.printf((i == 0) ? "%3d" : "%2d", i);
        System.out.println();
        for (int i = 0; i < a.length; i++) {
            System.out.printf("%2d", i);
            for (int j = 0; j < a[i].length; j++)
                System.out.print(a[i][j] == '\u0000' ? (j == a[i].length - 1 ? '-' + " " + "\n" : '-' + " ") : (j == a[i].length - 1 ? a[i][j] + " " + "\n" : a[i][j] + " "));
        }
    }
}

//扫描全局版的代码~
//for (int i = 0; i < a.length; i++)
//	for (int j = 0; j < a[i].length; j++)
//		if (i >= 2&& i < a.length - 2&& (Math.abs(a[i - 2][j] + a[i - 1][j]+ a[i][j] + a[i + 1][j]+ a[i + 2][j] - 577.5) == 22.5)
//				|| j >= 2&& j < a.length - 2&& (Math.abs(a[i][j - 2] + a[i][j - 1]+ a[i][j] + a[i][j + 1]+ a[i][j + 2] - 577.5) == 22.5)
//				|| i >= 2&& i < a.length - 2&& j >= 2&& j < a.length - 2&& ((Math.abs(a[i - 2][j - 2]+ a[i - 1][j - 1] + a[i][j]+ a[i + 1][j + 1] + a[i + 2][j + 2]- 577.5) == 22.5) 
//				|| (Math.abs(a[i + 2][j - 2]+ a[i + 1][j - 1] + a[i][j]+ a[i - 1][j + 1]+ a[i - 2][j + 2] - 577.5) == 22.5))) {
//			System.err.println(f + "方赢");
//			break label;