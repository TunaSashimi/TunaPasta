package history;

public class JiaXianTriangleTest {
    public static void main(String args[]) {
        int yh[] = new int[8];
        for (int i = 0; i < 8; i++) {
            yh[i] = 1;
            for (int j = 1; j < 8 - i; j++)
                System.out.print("  ");
            for (int j = i - 1; j > 0; j--)
                yh[j] += yh[j - 1];  //注意：算式yh[j]=yh[j-1]+yh[j];的过程中， 循环
            //要保证先算大的yh[j]值再算小的yh[j-1]值!  否则
            //yh[j-1]值增大后会影响yh[j]的增大幅度！
            for (int j = 0; j <= i; j++) {
                System.out.printf("%4d", yh[j]);
            }
            System.out.println();
        }
        System.out.println();
        System.out.println();
//另一种贾宪三角的二维数组的做法：	    
        int yh1[][] = new int[8][];
        for (int i = 0; i < 8; i++) {
            yh1[i] = new int[i + 1];      //如果是用二维数组的话，就没有先算后算之分！
            yh1[i][0] = 1;
            yh1[i][i] = 1;
        }
        for (int i = 2; i < 8; i++)
            for (int j = 1; j < i; j++)
                yh1[i][j] = yh1[i - 1][j - 1] + yh1[i - 1][j];
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j <= i; j++)
                System.out.print(yh1[i][j] + "\t");
            System.out.println();
        }
    }
}