package current;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class StupidProductTest {
	public static void main(String[] args) throws ParseException {

		//一个傻逼产品导致的问题
//		CalendarTest

		//    /**
//     * 检测选择是否符合规则
//     * <p>
//     * 不逾期情况
//     * 要还未来的期数只能还最近的当期和全还
//     * <p>
//     * 逾期情况
//     * 要还未来的,必须按顺序从最早的一期开始还
//     * 有其他待还款发生逾期了,提示先还逾期的
//     *
//     * @param repaymentsBeanList
//     * @return
//     */
//    private boolean checkRule(List<RepaymentsBean> repaymentsBeanList) {
//        //检查金额
//        if (Double.parseDouble(cart_total_money.getText().toString()) <= 0) {
//            ToastUtil.s("金额异常");
//            return false;
//        }
//
//        ///没有逾期
//        if (!isOverdue) {
//            int checkNum = 0;
//            for (int i = 0; i < repaymentsBeanList.size(); i++) {
//                RepaymentsBean repaymentsBean = repaymentsBeanList.get(i);
//                //未选本期
//                if (repaymentsBean.isIs_current_period() && !repaymentsBean.isCheck()) {
//                    ToastUtil.s("未选本期");
//                    return false;
//                }
//                if (repaymentsBean.isCheck()) {
//                    checkNum++;
//                }
//                //没有全选
//                if (i == repaymentsBeanList.size() - 1) {
//                    if (checkNum != 1 || checkNum != optionNum) {
//                        ToastUtil.s("只能本期或者全选");
//                        return false;
//                    } else {
//                        return true;
//                    }
//                }
//            }
//        }
//
//        ///有逾期
//
//        //检测已选可选首期
//        int firstOverDue = 0;
//        for (int i = 0; i < repaymentsBeanList.size(); i++) {
//            RepaymentsBean repaymentsBean = repaymentsBeanList.get(i);
//            if (repaymentsBean.getStatus() == CODE_HAS_OVERDUE) {
//                if (repaymentsBean.isCheck()) {
//                    firstOverDue = i;
//                    //已选可选首期是最后一期直接过
//                    if (firstOverDue == repaymentsBeanList.size() - 1) {
//                        return true;
//                    }
//                    break;
//                } else {
//                    ToastUtil.s("必须从最早开始选择");
//                    return false;
//                }
//            }
//        }
//
//        //检测选择连续,从firstOverDue + 1开始,选了false之后有没有再次选true
//        boolean unChecked = false;
//        for (int i = firstOverDue + 1; i < repaymentsBeanList.size(); i++) {
//            RepaymentsBean repaymentsBean = repaymentsBeanList.get(i);
//            if (!repaymentsBean.isCheck()) {
//                unChecked = true;
//            }
//            if (unChecked && repaymentsBean.isCheck()) {
//                ToastUtil.s("必须连续选择");
//                return false;
//            }
//        }
//
//        //全选给过
//        int checkNum = 0;
//        for (int i = 0; i < repaymentsBeanList.size(); i++) {
//            RepaymentsBean repaymentsBean = repaymentsBeanList.get(i);
//            if (repaymentsBean.isCheck()) {
//                checkNum++;
//            }
//            //
//            if (i == repaymentsBeanList.size() - 1) {
//                //checkNum==optionNum,或者checkNum<=本期和首期的差值+1
//                if (checkNum == optionNum || checkNum <= currentPeriod - firstOverDue + 1) {
//                    return true;
//                } else {
//                    ToastUtil.s("只能到本期或者全选");
//                    return false;
//                }
//            }
//        }
//        return false;
//    }
	}
}
