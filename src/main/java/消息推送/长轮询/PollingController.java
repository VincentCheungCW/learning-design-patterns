package 消息推送.长轮询;


import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import com.google.common.collect.Multimaps;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.async.DeferredResult;

import java.util.Collection;
import java.util.Date;

/**
 * 测试步骤：
 * 首先页面发起长轮询请求/polling/watch/10086监听消息更变，
 * 请求被挂起，不变更数据直至超时，再次发起了长轮询请求；
 * 紧接着手动变更数据/polling/publish/10086，
 * 长轮询得到响应，前端处理业务逻辑完成后再次发起请求，如此循环往复。
 */

/**
 * 长轮询其实原理跟轮询差不多，都是采用轮询的方式。
 * 不过，如果服务端的数据没有发生变更，会一直 hold 住请求，直到服务端的数据发生变化，
 * 或者等待一定时间超时才会返回。返回后，客户端又会立即再次发起下一次长轮询。
 */
@Controller
@RequestMapping("/polling")
public class PollingController {

  private static final Long TIME_OUT = 10000l;
  /**
   * 因为一个 ID 可能会被多个长轮询请求监听，所以采用 Guava 提供的Multimap结构存放长轮询，
   * 一个 key 可以对应多个 value。一旦监听到 key 发生变化，
   * 对应的所有长轮询都会响应。前端得到非请求超时的状态码，知晓数据变更，
   * 主动查询未读消息数接口，更新页面数据。
   * <p>
   * DeferredResult可以允许容器线程快速释放占用的资源，不阻塞请求线程，
   * 以此接受更多的请求提升系统的吞吐量，然后启动异步工作线程处理真正的业务逻辑，
   * 处理完成调用DeferredResult.setResult(200)提交响应结果。
   */
  // 存放监听某个Id的长轮询集合
  // 线程同步结构
  public static Multimap<String, DeferredResult<String>> watchRequests =
      Multimaps.synchronizedMultimap(HashMultimap.create());

  /**
   * 设置监听
   */
  @GetMapping(path = "watch/{id}")
  @ResponseBody
  public DeferredResult<String> watch(@PathVariable String id) {
    // 延迟对象设置超时时间
    DeferredResult<String> deferredResult = new DeferredResult<>(TIME_OUT);
    // 异步请求完成时移除 key，防止内存溢出
    deferredResult.onCompletion(() -> {
      watchRequests.remove(id, deferredResult);
    });
    // 注册长轮询请求
    watchRequests.put(id, deferredResult);
    return deferredResult;
  }

  /**
   * 变更数据
   */
  @GetMapping(path = "publish/{id}")
  @ResponseBody
  public String publish(@PathVariable String id) {
    // 数据变更 取出监听ID的所有长轮询请求，并一一响应处理
    if (watchRequests.containsKey(id)) {
      Collection<DeferredResult<String>> deferredResults = watchRequests.get(id);
      for (DeferredResult<String> deferredResult : deferredResults) {
        deferredResult.setResult("我更新了" + new Date());
      }
    }
    return "success";
  }
}