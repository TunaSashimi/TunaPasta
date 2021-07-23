package com.tunaPasta10.dot;

import android.content.Context;

/**     
 * 类名称：DotFactory   
 * 类描述：烟火加工厂
 * 创建人：anan   
 * 创建时间：2012-12-16 下午1:06:09   
 * 修改人：anan  
 * 修改时间：2012-12-16 下午1:06:09   
 * 修改备注：   
 * @version        
 * */
public class DotFactory {

	public DotFactory() {

	}

	public Dot makeDot(Context context, int kind, int x, int y) {

		int red = (int) (Math.random() * 255);
		int green = (int) (Math.random() * 255);
		int blue = (int) (Math.random() * 255);

		int col = 0xff000000 | red << 16 | green << 8 | blue;

		Dot dot = null;

//		if (Math.random() < 0.5) {
//
//			switch (kind % 6 + 1) {
//			case 1:
//				dot = new DotOne(context, col, x, y);
//				break;
//			case 2:
//				dot = new DotTwo(context, col, x, y);
//				break;
//			case 3:
//				dot = new DotThree(context, col, x, y);
//				break;
//			case 4:
//				dot = new DotFour(context, col, x, y);
//				break;
//			case 5:
//				dot = new DotFive(context, col, x, y);
//				break;
//			case 6:
//				dot = new DotSix(context, col, x, y);
//				break;
//			}
//
//		} else {
//			// 帧动画烟火
//			dot = new DotAnimFW(context, col, x, y);
//		}

		dot = new DotAnimFW(context, col, x, y);

		return dot;
	}
}
