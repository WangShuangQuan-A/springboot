package com.bootdo.system.controller;

import com.bootdo.system.config.damain.WiselyMessage;
import com.bootdo.system.config.damain.WiselyResponse;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

/**
 * webSocket
 *
 * @author wangshuangquan
 * @create 2017-12-04 11:14
 */

@Controller
public class WebsocketController {

    @MessageMapping("/welcome") //当浏览器向服务器发送请求时，通过@MessageMapping映射。类似RequestMapping
    @SendTo("/tocic/getResponse") //当服务端有消息时，会对订阅了@SendTo中的路径的浏览器发送消息
    public WiselyResponse say(WiselyMessage message) {
        return new WiselyResponse("welcome, " + message.getName() + "!");
    }
}
