package com.tunaPasta17.activity;


public class NetworkOption {
    //公共参数
    private String AES_IV;
    private String publicKey;

    //双录
    private String groupId;
    private String compareType;
    private Double yztThreshold;
    private Integer policeThreshold;

    private String clientId;
    private String channel;
    private String channel2;
    private String appId;

    private String secondChannel;

    public NetworkOption() {

        String stringValue = ConfigProvider.getBuildConfigStringValue("AES_IV");
        if (stringValue != null) {
            AES_IV = stringValue;
        }

        stringValue = ConfigProvider.getBuildConfigStringValue("PUBLIC_KEY");
        if (stringValue != null) {
            publicKey = stringValue;
        }

        stringValue = ConfigProvider.getBuildConfigStringValue("GROUP_ID");
        if (stringValue != null) {
            groupId = stringValue;
        }

        stringValue = ConfigProvider.getBuildConfigStringValue("COMPARE_TYPE");
        if (stringValue != null) {
            compareType = stringValue;
        }

        Double doubleValue = ConfigProvider.getBuildConfigDoubleValue("YZT_THRES_HOLD");
        if (doubleValue != null) {
            yztThreshold = doubleValue;
        }

        Integer integerValue = ConfigProvider.getBuildConfigIntegerValue("POLICE_THRES_HOLD");
        if (stringValue != null) {
            policeThreshold = integerValue;
        }
        //
        stringValue =  ConfigProvider.getBuildConfigStringValue("APP_ID");
        if (stringValue != null) {
            appId = stringValue;
        }
        stringValue =  ConfigProvider.getBuildConfigStringValue("CLIENT_ID");
        if (stringValue != null) {
            clientId = stringValue;
        }
        stringValue =  ConfigProvider.getBuildConfigStringValue("CHANNLE");
        if (stringValue != null) {
            channel = stringValue;
        }
        stringValue =  ConfigProvider.getBuildConfigStringValue("CHANNLE2");
        if (stringValue != null) {
            channel2 = stringValue;
        }
    }

    public String getAES_IV() {
        return AES_IV;
    }

    public void setAES_IV(String AES_IV) {
        this.AES_IV = AES_IV;
    }

    public String getPublicKey() {
        return publicKey;
    }

    public void setPublicKey(String publicKey) {
        this.publicKey = publicKey;
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public String getCompareType() {
        return compareType;
    }

    public void setCompareType(String compareType) {
        this.compareType = compareType;
    }

    public Double getYztThreshold() {
        return yztThreshold;
    }

    public void setYztThreshold(Double yztThreshold) {
        this.yztThreshold = yztThreshold;
    }

    public Integer getPoliceThreshold() {
        return policeThreshold;
    }

    public void setPoliceThreshold(Integer policeThreshold) {
        this.policeThreshold = policeThreshold;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public String getChannel() {
        return channel;
    }

    public void setChannel(String channel) {
        this.channel = channel;
    }

    public String getChannel2() {
        return channel2;
    }

    public void setChannel2(String channel2) {
        this.channel2 = channel2;
    }

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getSecondChannel() {
        return secondChannel;
    }

    public void setSecondChannel(String secondChannel) {
        this.secondChannel = secondChannel;
    }
}

