package 消息推送.SSE;

import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Consumer;


public class ServerSentEvent {
  private static Map<String, SseEmitter> sseEmitterMap = new ConcurrentHashMap<>();
  private static AtomicInteger count;

  /**
   * 创建连接
   */
  public static SseEmitter connect(String userId) {
    try {
      // 设置超时时间，0表示不过期。默认30秒
      SseEmitter sseEmitter = new SseEmitter(0L);
      // 注册回调
      sseEmitter.onCompletion(completionCallBack(userId));
      sseEmitter.onError(errorCallBack(userId));
      sseEmitter.onTimeout(timeoutCallBack(userId));
      sseEmitterMap.put(userId, sseEmitter);
      count.getAndIncrement();
      return sseEmitter;
    } catch (Exception e) {
//      log.info("创建新的sse连接异常，当前用户：{}", userId);
    }
    return null;
  }

  private static Runnable timeoutCallBack(String userId) {
    return null;
  }

  private static Consumer<Throwable> errorCallBack(String userId) {
    return null;
  }

  private static Runnable completionCallBack(String userId) {
    return null;
  }

  /**
   * 给指定用户发送消息
   */
  public static void sendMessage(String userId, String message) {

    if (sseEmitterMap.containsKey(userId)) {
      try {
        sseEmitterMap.get(userId).send(message);
      } catch (IOException e) {
//        log.error("用户[{}]推送异常:{}", userId, e.getMessage());
        removeUser(userId);
      }
    }
  }

  private static void removeUser(String userId) {

  }

}
