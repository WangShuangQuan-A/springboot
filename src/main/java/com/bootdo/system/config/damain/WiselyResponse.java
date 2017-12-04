package com.bootdo.system.config.damain;

/**
 * 服务器向浏览器发送消息
 *
 * @author wangshuangquan
 * @create 2017-12-04 11:10
 */
public class WiselyResponse {

    private String responseMessage;

    public WiselyResponse(String responseMessage) {
        this.responseMessage = responseMessage;
    }

    public String getResponseMessage(String responseMessage) {
        return responseMessage;
    }

    public void setResponseMessage(String responseMessage) {
        this.responseMessage = responseMessage;
    }
}
