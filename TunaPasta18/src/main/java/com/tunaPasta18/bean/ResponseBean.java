package com.tunaPasta18.bean;

public class ResponseBean {

    /**
     * REQUEST_ID : null
     * responseData : ZWM4ZDljNDNjZWIyNGJhZTlhNzA5NjdjN2VkMGQ0NDEyMDE4MDkwNg
     * responseMessage : 成功
     * ENCODE_KEY : null
     * responseCode : 000000
     */
    private String requestId;
    private String encodeKey;

    private String responseCode;
    private String responseData;
    private String responseMessage;

    public String getRequestId() {
        return requestId;
    }
    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }

    public String getEncodeKey() {
        return encodeKey;
    }
    public void setEncodeKey(String encodeKey) {
        this.encodeKey = encodeKey;
    }

    public void setResponseMessage(String responseMessage) {
        this.responseMessage = responseMessage;
    }

    public String getResponseCode() {
        return responseCode;
    }
    public void setResponseCode(String responseCode) {
        this.responseCode = responseCode;
    }

    public String getResponseData() {
        return responseData;
    }

    public void setResponseData(String responseData) {
        this.responseData = responseData;
    }
    public String getResponseMessage() {
        return responseMessage;
    }


    @Override
    public String toString() {
        return "TokenBean{" +
                "REQUEST_ID='" + requestId + '\'' +
                ", ENCODE_KEY='" + encodeKey + '\'' +
                ", responseCode='" + responseCode + '\'' +
                ", responseData='" + responseData + '\'' +
                ", responseMessage='" + responseMessage + '\'' +
                '}';
    }
}
