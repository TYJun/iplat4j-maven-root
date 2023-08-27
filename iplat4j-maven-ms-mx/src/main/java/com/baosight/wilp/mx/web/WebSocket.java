package com.baosight.wilp.mx.web;

import com.alibaba.fastjson.JSONObject;
import com.baosight.wilp.mx.ps.domain.Tag;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * WebSocket 服务类
 * @author: panlingfeng
 * @createDate: 2021/8/13 10:12 上午
 */
@ServerEndpoint("/ws/{id}")
public class WebSocket {

    public final static AtomicInteger onlineCount = new AtomicInteger(0); //记录当前在线连接数
    public final static Map<String, WebSocket> clients = new ConcurrentHashMap<>(); //客户端容器
    private Session session; //会话对象
    private String id; //客户端编号
    private List<Tag> tags; //标签

    /**
     * 将在客户端与服务端建立连接时执行
     *
     * @return
     * @author panlingfeng
     * @date 2021/8/13 11:29 上午
     * @params session 会话对象
     * @params id 客户端编号
     */
    @OnOpen
    public void onOpen(Session session, @PathParam("id") String id) {
        this.id = id;
        this.session = session;
        clients.put(id, this);
        onlineCount.incrementAndGet(); // 在线数加1
    }

    /**
     * 将在链接关闭时执行
     *
     * @return
     * @author panlingfeng
     * @date 2021/8/13 11:27 上午
     * @params session 会话对象
     */
    @OnClose
    public void onClose(Session session) {
        if (clients.containsKey(id)) {
            clients.remove(id);
            onlineCount.decrementAndGet(); // 在线数减1
        }
    }

    /**
     * 将在服务端收到消息时执行
     *
     * @return
     * @author panlingfeng
     * @date 2021/8/13 11:27 上午
     * @params message 客户端发送过来的消息
     */
    @OnMessage
    public void onMessage(String message) throws IOException {
        if (message.startsWith("[")) {
            tags = JSONObject.parseArray(message, Tag.class);
        }
    }

    /**
     * 将在链接发生错误时执行
     *
     * @return
     * @author panlingfeng
     * @date 2021/8/13 1:06 下午
     * @params session 会话对象
     * @params error
     */
    @OnError
    public void onError(Session session, Throwable error) {
        error.printStackTrace();
    }

    public void sendMessage(String message) throws IOException {
        session.getAsyncRemote().sendText(message);
    }

    public void sendMessage(Object message) throws IOException {
        String json = JSONObject.toJSONString(message);
        session.getAsyncRemote().sendText(json);
    }

    /**
     * 推送到指定客户端
     *
     * @return
     * @author panlingfeng
     * @date 2021/8/13 1:10 下午
     * @params message 消息
     * @params id 客户端编号
     */
    public void sendMessage(String message, String id) throws IOException {
        for (WebSocket item : clients.values()) {
            if (item.id.equals(id))
                item.session.getAsyncRemote().sendText(message);
        }
    }

    /**
     * 群推消息
     *
     * @return
     * @author panlingfeng
     * @date 2021/8/13 1:21 下午
     * @params message 消息
     */
    public void sendMessageAll(String message) throws IOException {
        for (WebSocket item : clients.values()) {
            item.session.getAsyncRemote().sendText(message);
        }
    }

    public List<Tag> getTags() {
        return tags;
    }

    public void setTags(List<Tag> tags) {
        this.tags = tags;
    }
}
