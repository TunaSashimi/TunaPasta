package com.tunaPasta04.tool;

public class ConvertTool {

    public static String getBinary(String string) {
        if ("".equals(string))
            return "";
        try {
            string = Integer.toBinaryString(Integer.valueOf(string));
        } catch (Exception e) {
            string = "ERROR";
        }
        return string;
    }

    public static String getOctal(String string) {
        if ("".equals(string))
            return "";
        try {
            string = Integer.toOctalString(Integer.valueOf(string));
        } catch (Exception e) {
            string = "ERROR";
        }
        return string;
    }

    public static String getHex(String string) {
        if ("".equals(string))
            return "";
        try {
            string = Integer.toHexString(Integer.valueOf(string));
        } catch (Exception e) {
            string = "ERROR";
        }
        return string;
    }
}
