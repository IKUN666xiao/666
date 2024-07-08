package com.tutorial.boot_demo;

import jakarta.websocket.*;
import jakarta.websocket.server.ServerEndpoint;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;


import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArraySet;

@Component
@ServerEndpoint("/websocket")
public class WebSocketServer {

    private Logger logger = LoggerFactory.getLogger(this.getClass());
    private int count=0;
    private Session session;
    private static ConcurrentHashMap<String, Session> sessionsByDeviceId = new ConcurrentHashMap<>();
    private String userId;

    private static CopyOnWriteArraySet<WebSocketServer> webSockets = new CopyOnWriteArraySet<>();
    private static ConcurrentHashMap<String, Session> sessionPool = new ConcurrentHashMap<String, Session>();

    @OnOpen
    public void onOpen(Session session) {
        try {
            this.session = session;
            this.userId = userId;
            webSockets.add(this);
            sessionPool.put(userId, session);
            logger.info("有新的连接，总数为:" + webSockets.size());
        } catch (Exception e) {
        }
    }

    @OnClose
    public void onClose() {
        try {
            webSockets.remove(this);
            sessionPool.remove(this.userId);
            logger.info("连接断开，总数为:" + webSockets.size());
        } catch (Exception e) {
        }
    }

    @OnMessage
    public void onMessage(String message, Session session) {
        logger.info("收到客户端消息: " + message);

        // 假设消息中包含了设备ID
        String name = extractDeviceIdFromMessage(message);

        if (name!= null && !name.isEmpty()) {
            // 将设备ID和会话关联起来，以便跟踪哪些设备已经发送了消息
            sessionsByDeviceId.put(name, session);

            // 检查是否已经收到来自两个不同设备的消息
            if (sessionsByDeviceId.size() >= 2) {
                // 广播"开始游戏"的消息
                sendAllMessage("开始游戏");

                // 清空记录，准备下一次游戏
                sessionsByDeviceId.clear();
            }
        } else {
            logger.warn("无法识别的设备消息: " + message);
        }
    }

    private String extractDeviceIdFromMessage(String message) {
        // 实现逻辑来从消息中提取设备ID
        // 例如，假设消息格式为 "deviceId:messageContent"
        String[] parts = message.split(":");
        return parts.length > 0 ? parts[0] : null;
    }

    public void sendAllMessage(String message) {
        logger.info("广播消息:" + message);
        for (WebSocketServer webSocket : webSockets) {
            try {
                if (webSocket.session.isOpen()) {
                    webSocket.session.getAsyncRemote().sendText(message);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

}