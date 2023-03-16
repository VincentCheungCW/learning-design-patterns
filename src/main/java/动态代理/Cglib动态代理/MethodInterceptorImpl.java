package 动态代理.Cglib动态代理;

import org.mockito.cglib.proxy.Enhancer;
import org.mockito.cglib.proxy.MethodInterceptor;
import org.mockito.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * Created by Jiang on 2019-10-29.
 */
public class MethodInterceptorImpl implements MethodInterceptor {

  /**
   * 实现MethodInterceptor接口的方法intercept
   *
   * @param proxy
   * @param method
   * @param args
   * @param methodProxy
   * @return
   * @throws Throwable
   */
  @Override
  public Object intercept(Object proxy, Method method, Object[] args,
                          MethodProxy methodProxy) throws Throwable {
    Object result;
    if (methodProxy.getSignature().getName().equalsIgnoreCase("saySomething")) {
      System.out.println("Before真实对象的方法");
      //调用超类（即真实类）的方法
      result = methodProxy.invokeSuper(proxy, args);
      System.out.println("After真实对象的方法");
    } else {
      result = methodProxy.invokeSuper(proxy, args);
    }
    return result;
  }
}
