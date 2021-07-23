package com.tunaPasta18.util;


import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

/**
 * @ClassName: AESUtils
 * @Description:
 * @author: ex-yinshaobo001
 * @date: 2018/9/12 上午10:19
 */
public class AESUtil {

    private static final String IV_STRING = "hmAsUlwwBRmotlOY";

    private static final String CHARSET = "UTF-8";

    /**
     * 加密明文字符串
     *
     * @param data
     * @param key
     * @return
     */
    public static String encryptString(String data, String key) {
        byte[] byts = null;
        try {
            byts = encryptByts(data.getBytes(CHARSET), key.getBytes(CHARSET));
        } catch (Exception e) {
            e.printStackTrace();
        }

        return byts == null ? null : Base64HV.getEncoder().encodeToString(byts);
    }

    /**
     * 解密密文字符串
     *
     * @param data
     * @param key
     * @return
     */
    public static String decryptString(String data, String key) {
        String decryptStr = null;
        try {
            byte[] byts = decryptByts(Base64HV.getDecoder().decode(data), key.getBytes(CHARSET));

            decryptStr = new String(byts, CHARSET);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return decryptStr;
    }

    /**
     * 加密byte
     *
     * @param data
     * @param keyBytes
     * @return
     */
    public static byte[] encryptByts(byte[] data, byte[] keyBytes) {
        return cipherOpt(data, keyBytes, Cipher.ENCRYPT_MODE);
    }

    /**
     * 解密byte
     *
     * @param data
     * @param keyBytes
     * @return
     */
    public static byte[] decryptByts(byte[] data, byte[] keyBytes) {
        return cipherOpt(data, keyBytes, Cipher.DECRYPT_MODE);
    }

    /**
     * AES加解密操作
     *
     * @param dataByts
     * @param keyByts
     * @param mode
     * @return
     */
    private static byte[] cipherOpt(byte[] dataByts, byte[] keyByts, int mode) {
        SecretKeySpec secretKey = new SecretKeySpec(keyByts, "AES");

        try {
            byte[] initParam = IV_STRING.getBytes(CHARSET);

            IvParameterSpec ivParameterSpec = new IvParameterSpec(initParam);

            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");

            cipher.init(mode, secretKey, ivParameterSpec);

            return cipher.doFinal(dataByts);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }


}
