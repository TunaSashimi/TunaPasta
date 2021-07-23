package com.tunaPasta18.request;

/**
 * @author Tunasashimi
 * @date 2018/9/13 14:05
 * @Copyright 2018 TunaSashimi. All rights reserved.
 * @Description
 */
public class WebRequest {
    /**
     * 用户公钥
     */
    public static String PUBLIC_KEY = "MFwwDQYJKoZIhvcNAQEBBQADSwAwSAJBAJ7A81THraSlmOVRG7HlDqySbSjeh6eIiH5EX3ZXX0aVOY4JOVqcOM3ldKzWWZS1Tw/Tam1Dkc8DfE4Q5Fv1VaMCAwEAAQ==";
    /**
     * 公共请求参数
     */
    //接口方法名称
//    public static String METHOD = "faceDetect";
    //金科开发平台申请的客户端ID
    public static String CLIENT_ID = "6cefa1d799dc4f90b616815b828ff3f2";
    //长度最长为32位，数字和字符串的组合，请求唯一标识（建议用UUID）,调用方生成
    public static String REQUEST_ID = "fe50aeef314943d5ac6b6baca580f21e";
    //渠道号
    public static String CHANNEL = "xinanbank";
    //二级渠道号
    public static String CHANNEL_2 = "xinanbank_mobile_bank";
    //应用标识
    public static String APP_ID = "app001";
    //AES密钥（RSA公钥加过密）
    public static String ENCODE_KEY = "f8qJ6bl2xDVBRfeVfCbdFTdjT0DjJrnbn8xbeQzoN-gjY-tqkO4OoItkLGvQTA7YMIgZxdMKDpj9Txw3wW6q6g";
    //访问令牌
    public static String ACCESS_TOKEN = "MmYxMDE0MjJkOGUwNDJjOWIyNzZkYjIwM2RhYmEyOTQyMDE4MDkxNw";
    //签名（对所有字段除sign外非Null字段，以key值进行字母排序进行加签）
    public static String SIGN = "_G9SAtxLmN0LvguCnkxqNBDaqXCJRT7vw5JH6rmJ0d4";
    //签名的摘要算法，默认值为：sha256(非必须)
    public static String SIGN_METHOD = "sha256";
    //时间戳，精确到毫秒（System.currentTime)
//    public static String TIME_STAMP = "1536719847142";
    //响应格式，默认为json(非必须)
    public static String FORMAT = "json";
    //接口版本号，默认值1.0
    public static String VERSION = "1.0";
    //请求URL
    public static String URI = "/monsa/api/recognition";
    //请求方法
    public static String HTTP_METHOD = "这里是请求http方法信息";


    //请求方法
    public static String COMMENTS = "这里是备注信息这里是备注信息这里是备注信息这里是备注信息";

    //json格式业务数据密文
    public static String REQUEST_DATA = "";


    /**
     * 方法请求参数(人脸检测)
     */

    //sdk版本号(非必须)
    public static String SDK_VERSION = "8.0";
    //设备ID(非必须)
    public static String DEVICE_ID = "device001";
    //设备机型(非必须)
    public static String DEVICE_MODEL = "MBP2017";
    //客户端平台类型与版本(非必须)
    public static String DEVICEOS_AND_VERSION = "android 8.0";
    //标示图片1类别
    public static String IMG_TYPE = "1";
    //图片1格式
    public static String IMG_FORMAT = "jpg";
    //Base64图片编码
    public static String IMG_DATA = "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";

    //是否返回多人脸数组
    public static String FACE_SET = "true";
    //客户唯一编号
    public static String PERSON_ID = "person001";
    //二级渠道号(非必须)
    public static String SECOND_CHANNEL = "xinanbank_mobile_bank";
    //二级渠道名(非必须)
    public static String SECOND_CHANNEL_NAME = "这里是二级渠道名";
    //备注(非必须)
    public static String DESCRIPTION = "这里是描述";

}
