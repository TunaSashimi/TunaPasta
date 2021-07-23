package com.tunaPasta02.entity;
public class MineHelper {
	private int[][] panel = new int[10][10]; // 第一维长度为布雷面板的行数r,第二维长度为布雷面板的列数c
	private int n; // 布置面板中的地雷数 n
	public MineHelper(int n) {
		this.n = n;
	}
	// 得到地雷位置,并把数组中的地雷位置值修改为-1
	private void setMine() {
		int count = 0;
		for (; count < n;) {
			int i = (int) (Math.random() * panel.length), j = (int) (Math.random() * panel[0].length);
			if (panel[i][j] != -1) {
				panel[i][j] = -1;
				count++;
			}
		}
	}
	// 周围环境布局,把数组中的剩下位置值修改为周围的地雷数;
	private void setSurround() {
		System.out.println(" 自动生成环境布局	");
		for (int i = 0; i < panel.length; i++) {
			for (int j = 0; j < panel[i].length; j++) {
				if (panel[i][j] != -1) {
					int count = 0;
					if (i - 1 >= 0 && panel[i - 1][j] == -1)
						count++;
					if (i - 1 >= 0 && j - 1 >= 0 && panel[i - 1][j - 1] == -1)
						count++;
					if (i - 1 >= 0 && j + 1 < panel[0].length
							&& panel[i - 1][j + 1] == -1)
						count++;
					if (i + 1 < panel.length && panel[i + 1][j] == -1)
						count++;
					if (i + 1 < panel.length && j - 1 >= 0
							&& panel[i + 1][j - 1] == -1)
						count++;
					if (i + 1 < panel.length && j + 1 < panel[0].length
							&& panel[i + 1][j + 1] == -1)
						count++;
					if (j - 1 >= 0 && panel[i][j - 1] == -1)
						count++;
					if (j + 1 < panel[0].length && panel[i][j + 1] == -1)
						count++;
					panel[i][j] = count; // 完成赋值~
				}
				System.out.print(panel[i][j] == -1 ? '^' + " " : panel[i][j]+ " "); // 测试得到的布雷面板是否正确~
			}
			System.out.println();
		}
	}
	// 布雷过程就是两者的结合,并返回设置好的布雷面板;
	public String[] layoutMines() {
		setMine();
		setSurround();
		String []panels=new String[100];
		for (int i = 0; i < panel.length; i++) {
			for (int j = 0; j < panel[i].length; j++) {
				panels[i*10+j]=panel[i][j]+"";
			}
		}
		return panels;
	}
}
