package club.deepblue.twilight.webscoket;

import cn.hutool.log.Log;
import cn.hutool.log.LogFactory;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;


@ServerEndpoint("/imserver/{ci_id}/{u_id}")
@Component
public class WebSocketServer {

  static Log log = LogFactory.get(WebSocketServer.class);
  /**
   * 静态变量，用来记录当前在线连接数。应该把它设计成线程安全的。
   */
  private static int onlineCount = 0;
  private static ConcurrentHashMap<Integer, Integer> roomOnlineCountMap = new ConcurrentHashMap<>();
  /**
   * concurrent包的线程安全Set，用来存放每个客户端对应的MyWebSocket对象。
   */
  private static ConcurrentHashMap<Integer, WebSocketServer> webSocketMap = new ConcurrentHashMap<>();
  private static ConcurrentHashMap<Integer, List<Integer>> roomMap = new ConcurrentHashMap<>();
  /**
   * 与某个客户端的连接会话，需要通过它来给客户端发送数据
   */
  private Session session;
  /**
   * 接收房间号
   */
  private Integer ci_id;
  //接收用户号
  private Integer u_id;

  /**
   * 连接建立成功调用的方法
   */
  @OnOpen
  public void onOpen(Session session, @PathParam("ci_id") Integer ci_id, @PathParam("u_id") Integer u_id) {

    this.u_id = u_id;

    this.session = session;

    this.ci_id = ci_id;

    if (webSocketMap.containsKey(u_id)) {
      webSocketMap.remove(u_id);
      webSocketMap.put(u_id, this);
    } else {
      webSocketMap.put(u_id, this);
      addOnlineCount();
    }

    if (roomMap.containsKey(ci_id)) {
      List<Integer> roomUsers = roomMap.get(ci_id);
      if (!roomUsers.contains(u_id)) {
        roomUsers.add(u_id);
      }
      roomMap.remove(ci_id);
      roomMap.put(ci_id, roomUsers);
      Integer roomOnlineCount = roomOnlineCountMap.get(ci_id);
      roomOnlineCount = roomOnlineCount + 1;
      roomOnlineCountMap.put(ci_id, roomOnlineCount);
    } else {
      List<Integer> roomUsers = new LinkedList<Integer>();
      if (!roomUsers.contains(u_id)) {
        roomUsers.add(u_id);
      }
      roomMap.put(ci_id, roomUsers);
      roomOnlineCountMap.put(ci_id, 1);
    }
    log.info("用户连接:" + u_id + ",当前在线人数为:" + getOnlineCount());
    log.info("用户：" + u_id + " 加入房间：" + ci_id + ",当前房间总人数：" + roomOnlineCountMap.get(ci_id));
    try {
      JSONObject jsonObject=new JSONObject();
      jsonObject.put("errcode",1101);
      for (Integer integer : roomMap.get(ci_id)) {
        webSocketMap.get(integer).sendMessage(jsonObject.toJSONString());
      }
    } catch (IOException e) {
      log.error("用户:" + u_id + ",网络异常!!!!!!");
    }
  }

  /**
   * 连接关闭调用的方法
   */
  @OnClose
  public void onClose() {
    if (webSocketMap.containsKey(u_id)) {
      webSocketMap.remove(u_id);
      //从set中删除
      subOnlineCount();
      if (roomMap.containsKey(ci_id)) {
        List<Integer> roomUsers = roomMap.get(ci_id);
        roomUsers.remove(u_id);
        roomMap.remove(ci_id);
        roomMap.put(ci_id, roomUsers);
        Integer roomOnlineCount = roomOnlineCountMap.get(ci_id);
        roomOnlineCount = roomOnlineCount - 1;
        roomOnlineCountMap.put(ci_id, roomOnlineCount);
      }
    }
    try {
      JSONObject jsonObject=new JSONObject();
      jsonObject.put("errcode",1102);
      for (Integer integer : roomMap.get(ci_id)) {
        webSocketMap.get(integer).sendMessage(jsonObject.toJSONString());
      }
    } catch (IOException e) {
      log.error("用户:" + u_id + ",网络异常!!!!!!");
    }
    log.info("用户退出:" + u_id + ",当前在线人数为:" + getOnlineCount());
    log.info("用户：" + u_id + " 退出房间：" + ci_id + ",当前房间总人数：" + roomOnlineCountMap.get(ci_id));
  }

  /**
   * 收到客户端消息后调用的方法
   *
   * @param message 客户端发送过来的消息
   */
  @OnMessage
  public void onMessage(String message, Session session) {
    log.info("用户消息:" + u_id + ",报文:" + message);
    //可以群发消息
    //消息保存到数据库、redis
    if (StringUtils.isNotBlank(message)) {
      try {

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("errcode",1100);
        //追加发送人(防止串改)
        jsonObject.put("u_id", u_id);

        jsonObject.put("message", message);

        for (Integer integer : roomMap.get(ci_id)) {
          webSocketMap.get(integer).sendMessage(jsonObject.toJSONString());
        }


      } catch (Exception e) {
        e.printStackTrace();
      }
    }
  }

  /**
   * @param session
   * @param error
   */
  @OnError
  public void onError(Session session, Throwable error) {
    log.error("用户错误:" + this.u_id + ",原因:" + error.getMessage());
    error.printStackTrace();
  }

  /**
   * 实现服务器主动推送
   */
  public void sendMessage(String message) throws IOException {
    this.session.getBasicRemote().sendText(message);
  }


  /**
   * 发送自定义消息
   */
  public static void sendInfo(String message, @PathParam("userId") String userId) throws IOException {
    log.info("发送消息到:" + userId + "，报文:" + message);
    if (StringUtils.isNotBlank(userId) && webSocketMap.containsKey(userId)) {
      webSocketMap.get(userId).sendMessage(message);
    } else {
      log.error("用户" + userId + ",不在线！");
    }
  }

  public static synchronized int getOnlineCount() {
    return onlineCount;
  }

  public static synchronized void addOnlineCount() {
    WebSocketServer.onlineCount++;
  }

  public static synchronized void subOnlineCount() {
    WebSocketServer.onlineCount--;
  }
}
