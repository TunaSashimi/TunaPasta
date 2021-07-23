package current;

/**
 * @author Tunasashimi
 * @date 2019-11-05 13:52
 * @Copyright 2019 TunaSashimi. All rights reserved.
 * @Description
 */

public class MaskUtils {
    public static void main(String[] args) {
        //
//        System.out.println(maskEmail1("1.2345678@qq.com"));
//        System.out.println(maskEmail1("12345@qq.com"));
//        System.out.println(maskEmail1("1234@qq.com"));
//        System.out.println(maskEmail1("12@qq.com"));
//        System.out.println(maskEmail1("1@qq.com"));

        System.out.println();

        System.out.println(maskEmail2("1234567@qq.com"));
        System.out.println(maskEmail2("12345@qq.com"));
        System.out.println(maskEmail2("1234@qq.com"));
        System.out.println(maskEmail2("12@qq.com"));
        System.out.println(maskEmail2("1@qq.com"));
    }

    /**
     * 邮箱脱敏
     * <p>
     * 正则替换
     *
     * <p>
     * 前缀大于6位字符,保留前3位和后3位,中间其他位标*
     * 前缀为5或6位字符,保留前2位和后2位,中间其他位标*
     * 前缀为2或4位字符,只显示第1位,例如1234@163显示1***@163.com
     * 前缀只有1位字符,则完全展示,不标*
     * 邮箱后缀完全展示
     *
     * @param email
     * @return
     */
    public static String maskEmail1(String email) {

        String emailHead = email.substring(0, email.indexOf("@"));
        int headLength = emailHead.length();

        StringBuilder stringBuilder = new StringBuilder();
        if (headLength > 6) {
            for (int i = 0; i < headLength - 6; i++) {
                stringBuilder.append("*");
            }
        } else if (headLength > 4) {
            for (int i = 0; i < headLength - 4; i++) {
                stringBuilder.append("*");
            }
        } else if (headLength > 1) {
            for (int i = 0; i < headLength - 1; i++) {
                stringBuilder.append("*");
            }
        }


        String replacement = "$1" + stringBuilder.toString() + "$2";


        return email.replaceFirst("(^\\w{3})[^@*]+(\\w{3}@.*$)", replacement)
                .replaceFirst("(^\\w{2})[^@*]+(\\w{2}@.*$)", replacement)
                .replaceFirst("(^\\w{1})[^@*]+(@.*$)", replacement);
    }


    /**
     * 邮箱脱敏
     * <p>
     * 字符串拼接
     *
     * <p>
     * 前缀大于6位字符,保留前3位和后3位,中间其他位标*
     * 前缀为5或6位字符,保留前2位和后2位,中间其他位标*
     * 前缀为2或4位字符,只显示第1位,例如1234@163显示1***@163.com
     * 前缀只有1位字符,则完全展示,不标*
     * 邮箱后缀完全展示
     *
     * @param email
     * @return
     */
    public static String maskEmail2(String email) {
        String emailHead = email.substring(0, email.indexOf("@"));
        String emailLast = email.substring(email.indexOf("@"));

        StringBuilder stringBuilder = new StringBuilder();
        int length = emailHead.length();
        if (length > 6) {
            stringBuilder.append(emailHead, 0, 3);
            for (int i = 3; i < length - 3; i++) {
                stringBuilder.append("*");
            }
            stringBuilder.append(emailHead, length - 3, length);
        } else if (length > 4) {
            stringBuilder.append(emailHead, 0, 2);
            for (int i = 2; i < length - 2; i++) {
                stringBuilder.append("*");
            }
            stringBuilder.append(emailHead, length - 2, length);
        } else if (length > 1) {
            stringBuilder.append(emailHead, 0, 1);
            for (int i = 1; i < length; i++) {
                stringBuilder.append("*");
            }
        } else {
            stringBuilder.append(emailHead);
        }
        stringBuilder.append(emailLast);
        return stringBuilder.toString();
    }
}
