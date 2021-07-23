package java8;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.util.Base64;
import java.util.UUID;

/**
 * @author Tunasashimi
 * @date 2018/9/11 17:43
 * @Copyright 2018 TunaSashimi. All rights reserved.
 * @Description
 */
public class Base64Test {
    private static String coding64;

    public static void main(String[] args) throws IOException {
        try {
            // 编码
            coding64 = Base64.getEncoder().encodeToString("I want to encrypt".getBytes("utf-8"));
            System.out.println(coding64);

            byte[] b = new byte[6];
            final String s = Base64.getEncoder().encodeToString(b);

            // 解码
            byte[] asBytes = Base64.getDecoder().decode(coding64);
            System.out.println(new String(asBytes, "utf-8")); // 输出为: I want to encrypt


            //URL编码是我们经常会面对的需求，但由于URL对反斜线“/”有特殊的意义，因此URL编码需要替换掉它，使用下划线替换。
            //使用基本的编码器，那么输出可能会包含反斜线“/”字符，但是如果使用URL编码器，那么输出的内容对URL来说是安全的。
            String basicEncoded = Base64.getEncoder().encodeToString("subjects?abcd".getBytes("utf-8"));
            System.out.println("Using Basic Alphabet==>" + basicEncoded);

            String urlEncoded = Base64.getUrlEncoder().encodeToString("subjects?abcd".getBytes("utf-8"));
            System.out.println("Using URL Alphabet==>" + urlEncoded);

            //MIME编码器会使用基本的字母数字产生BASE64输出，而且对MIME格式友好：每一行输出不超过76个字符，而且每行以“\r\n”符结束。
            StringBuilder sb = new StringBuilder();
            for (int t = 0; t < 10; ++t) {
                sb.append(UUID.randomUUID().toString());
            }

            byte[] toEncode = sb.toString().getBytes("utf-8");
            String mimeEncoded = Base64.getMimeEncoder().encodeToString(toEncode);
            System.out.println("mimeEncoded==>" + mimeEncoded);

            //
            wrapping();

        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    public static void wrapping() throws IOException {
        String src = "This is the content of any resource read from somewhere" +
                " into a stream. This can be text, image, video or any other stream.";

        // 编码器封装OutputStream, 文件/tmp/buff-base64.txt的内容是BASE64编码的形式
        try (OutputStream os = Base64.getEncoder().wrap(new FileOutputStream("/tmp/buff-base64.txt"))) {
            os.write(src.getBytes("utf-8"));
        }

        // 解码器封装InputStream, 以及以流的方式解码, 无需缓冲
        // is being consumed. There is no need to buffer the content of the file just for decoding it.
        try (InputStream is = Base64.getDecoder().wrap(new FileInputStream("/tmp/buff-base64.txt"))) {
            int len;
            byte[] bytes = new byte[100];
            while ((len = is.read(bytes)) != -1) {
                System.out.print(new String(bytes, 0, len, "utf-8"));
            }
        }
    }
}