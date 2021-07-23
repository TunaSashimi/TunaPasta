package com.tunaPasta18.util;


import java.security.MessageDigest;

/**
 * @ClassName: SignUtils
 * @Description:
 * @author: ex-yinshaobo001
 * @date: 2018/9/12 上午10:28
 */
public class SignUtil {

    /**
     * 使用SHA256加签
     *
     * @param data
     * @param format
     * @return
     * @throws Exception
     */
    public static String signSHA256Base64(String data, String format) throws Exception {
        return Base64HV.getUrlEncoder().encodeToString(signSHA256(data, "SHA-256", format))
                .replace("=", "")
                .replace("\n", "")
                ;
    }

    private static byte[] signSHA256(String data, String alg, String format) throws Exception {
        MessageDigest messageDigest;
        messageDigest = MessageDigest.getInstance(alg);
        messageDigest.update(data.getBytes(format));
        return messageDigest.digest();
    }
}
