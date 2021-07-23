package current;

public class CompareVersionTest {
    public static void main(String[] args) {
        System.out.println(checkName("1", "1"));
    }

    /**
     * 1.  主版本号
     * 2.  次版本号
     * 3.  修正版本号
     * 4.  编译版本号
     * 例如：2.1.3 ，3.7.5，10.2.0
     * <p>
     * 版本号比较
     *
     * @param version1
     * @param version2
     * @return 0代表相等，1代表version1大于version2，-1代表version1小于version2
     */
    public static int compareVersion(String version1, String version2) throws Exception {
        if (version1.equals(version2)) {
            return 0;
        }
        String[] version1Array = version1.split("\\.");
        String[] version2Array = version2.split("\\.");
        int index = 0;
        // 获取最小长度值
        int minLen = Math.min(version1Array.length, version2Array.length);
        int diff = 0;
        // 循环判断每位的大小
        while (index < minLen
                && (diff = Integer.parseInt(version1Array[index])
                - Integer.parseInt(version2Array[index])) == 0) {
            index++;
        }
        if (diff == 0) {
            // 如果位数不一致，比较多余位数
            for (int i = index; i < version1Array.length; i++) {
                if (Integer.parseInt(version1Array[i]) > 0) {
                    return 1;
                }
            }

            for (int i = index; i < version2Array.length; i++) {
                if (Integer.parseInt(version2Array[i]) > 0) {
                    return -1;
                }
            }
            return 0;
        } else {
            return diff > 0 ? 1 : -1;
        }
    }

    /**
     * 检查字符串1，是否包含在字符串2的字段里面
     *
     * @param name1
     * @param name2
     * @return true 代表包含
     */
    public static boolean checkName(String name1, String name2) {
        if (name1 != null && name2 != null) {
            if (name1.equals(name2)) {
                return true;
            }
            String[] name2Array = name2.split("\\;");
            if (name2Array != null && name2Array.length > 0) {

                System.out.println("length==>" + name2Array.length);

                for (int i = 0; i < name2Array.length; i++) {
                    if (name1.equals(name2Array[i])) {
                        return true;
                    }
                }
            }
        }
        return false;
    }
}
