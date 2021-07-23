package com.tunaPasta18.util;


import javax.crypto.Cipher;
import java.io.ByteArrayOutputStream;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.X509EncodedKeySpec;

/**
 * RSA工具类
 *
 * @author zhy
 */
public class RSAUtil {
    private static final String CHARSET = "UTF-8";

    private static final int MAX_ENCRYPT_BLOCK = 53;

    private static final int MAX_DECRYPT_BLOCK = 64;

    /**
     * 获取公钥
     *
     * @param publicKey
     * @return
     */
    private static RSAPublicKey getPublicKey(String publicKey) {
        // 通过X509编码的Key指令获得公钥对象
        KeyFactory keyFactory = null;
        try {
            keyFactory = KeyFactory.getInstance("RSA");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }

        X509EncodedKeySpec x509KeySpec = new X509EncodedKeySpec(Base64HV.getDecoder().decode(publicKey));

        RSAPublicKey key = null;
        try {
            key = (RSAPublicKey) keyFactory.generatePublic(x509KeySpec);
        } catch (InvalidKeySpecException e) {
            e.printStackTrace();
            return null;
        }

        return key;
    }

    /**
     * 分块加密
     *
     * @param cipher
     * @param datas
     * @param maxBlock
     * @return
     */
    private static byte[] doFinalByBlock(Cipher cipher, byte[] datas, int maxBlock) {
        int offset = 0;
        byte[] buff;
        int i = 0;
        byte[] result = null;
        try {
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            while (datas.length > offset) {
                if (datas.length - offset > maxBlock) {
                    buff = cipher.doFinal(datas, offset, maxBlock);
                } else {
                    buff = cipher.doFinal(datas, offset, datas.length - offset);
                }
                out.write(buff, 0, buff.length);
                i++;
                offset = i * maxBlock;
            }
            result = out.toByteArray();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 公钥加密
     *
     * @param data
     * @param publicKey
     * @return
     */
    public static String encryptByPublic(String data, String publicKey) {
        try {
            Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
            cipher.init(Cipher.ENCRYPT_MODE, getPublicKey(publicKey));

            byte[] result = null;
            byte[] origByts = data.getBytes(CHARSET);
            if (origByts.length > MAX_ENCRYPT_BLOCK) {
                result = doFinalByBlock(cipher, data.getBytes(CHARSET), MAX_ENCRYPT_BLOCK);
            } else {
                result = cipher.doFinal(origByts);
            }
            return Base64HV.getEncoder().encodeToString(result);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 公钥解密
     *
     * @param base64Data
     * @param publicKey
     * @return
     */
    public static String decryptByPublic(String base64Data, String publicKey) {
        try {
            Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
            cipher.init(Cipher.DECRYPT_MODE, getPublicKey(publicKey));

            byte[] result = null;
            byte[] origByts = Base64HV.getDecoder().decode(base64Data);
            if (origByts.length > MAX_DECRYPT_BLOCK) {
                result = doFinalByBlock(cipher, origByts, MAX_ENCRYPT_BLOCK);
            } else {
                result = cipher.doFinal(origByts);
            }

            return new String(result);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

}