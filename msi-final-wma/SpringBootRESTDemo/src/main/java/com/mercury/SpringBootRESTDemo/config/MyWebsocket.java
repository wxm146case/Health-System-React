package com.mercury.SpringBootRESTDemo.config;

import java.io.IOException;
import java.util.concurrent.CopyOnWriteArraySet;

import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

import org.springframework.stereotype.Component;

@ServerEndpoint(value = "/websocket")
@Component
public class MyWebsocket {
	
	
	private static CopyOnWriteArraySet<MyWebsocket> webSocketSet = new CopyOnWriteArraySet<MyWebsocket>();
	
	private Session session;

    
    @OnOpen
    public void onOpen(Session session) {
        System.out.println("连接成功");
        this.session = session;
        webSocketSet.add(this);
    }

   
    @OnClose
    public void onClose() {
    		webSocketSet.remove(this);
        System.out.println("有一连接关闭");
    }

   
    @OnMessage
    public void onMessage(String message, Session session) {
        System.out.println("来自客户端的消息:" + message);
        for (MyWebsocket item : webSocketSet) {
            try {
                item.sendMessage(message);
            } catch (IOException e) {
                
            }
        }
    }
    
    public void sendMessage(String message) throws IOException {
        this.session.getBasicRemote().sendText(message);
        
    }

    
    @OnError
    public void onError(Session session, Throwable error) {
        System.out.println("发生错误");
        error.printStackTrace();
    }


}
