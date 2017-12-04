package com.bootdo.system.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.AbstractWebSocketMessageBrokerConfigurer;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;

@Configuration
@EnableWebSocketMessageBroker
/**
 * 通过EnableWebSocketMessageBroker 开启使用STOMP协议来传输基于代理(message broker)的消息,此时浏览器支持使用@MessageMapping 就像支持@RequestMapping一样。
 */

public class WebSocketConfig extends AbstractWebSocketMessageBrokerConfigurer{

    /**
     * endPoint 注册STOMP协议节点(endPoint),并映射指定的URl
     * @param registry
     */
    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        //注册一个STOMP 协议的endpoint,并指定使用SockJS协议。
        registry.addEndpoint("/endpointWisely").withSockJS();
        //registry.addEndpoint("/test").withSockJS();
    }

    /**
     * 配置消息代理(Message broker)
     * @param registry
     */
    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        //广播式应配置一个/topic 消息代理
        registry.enableSimpleBroker("/topic","/user");
//        registry.setApplicationDestinationPrefixes("/app");  //设置客户端订阅消息的基础路径
        registry.setUserDestinationPrefix("/user/");
    }
}
