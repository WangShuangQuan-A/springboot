package com.bootdo.system.controller;

import com.bootdo.oa.domain.Response;
import com.bootdo.system.config.damain.WiselyMessage;
import com.bootdo.system.config.damain.WiselyResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.simp.annotation.SendToUser;
import org.springframework.stereotype.Controller;

/**
 * webSocket
 *
 * @author wangshuangquan
 * @create 2017-12-04 11:14
 */

@Controller
public class WebsocketController {

    @Autowired
    private SimpMessagingTemplate template;

    @MessageMapping("/chat") //当浏览器向服务器发送请求时，通过@MessageMapping映射。类似RequestMapping
    public void say(String message) {

        template.convertAndSendToUser("chat1","/chat",new Response("dsadadsada"));
    }
}
