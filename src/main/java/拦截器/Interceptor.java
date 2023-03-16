package 拦截器;

import java.lang.reflect.Method;

/**
 * 利用JDK动态代理实现的拦截器
 * 只把该接口暴露给用户
 * 程序设计中往往用拦截器代替动态代理
 * Created by Jiang on 2019-10-29.
 */
public interface Interceptor {
    public boolean before(Object proxy, Object target, Method method, Object[] args);
    public void around(Object proxy, Object target, Method method, Object[] args);
    public void after(Object proxy, Object target, Method method, Object[] args);
}
