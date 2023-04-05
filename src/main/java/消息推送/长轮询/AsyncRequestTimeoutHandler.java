package 消息推送.长轮询;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.async.AsyncRequestTimeoutException;

/**
 * 当请求超过设置的超时时间，会抛出AsyncRequestTimeoutException异常，
 * 这里直接用@ControllerAdvice全局捕获统一返回即可，前端获取约定好的状态码后再次发起长轮询请求，
 * 如此往复调用。
 *
 */
@ControllerAdvice
public class AsyncRequestTimeoutHandler {

  @ResponseStatus(HttpStatus.NOT_MODIFIED)
  @ResponseBody
  @ExceptionHandler(AsyncRequestTimeoutException.class)
  public String asyncRequestTimeoutHandler(AsyncRequestTimeoutException e) {
    System.out.println("异步请求超时");
    return "304";
  }
}