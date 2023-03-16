package 动态代理.JDK动态代理;

import java.lang.reflect.Proxy;

public class DjProxyFactory {
    public static Object getProxy(Object target) {
        // 在这里决定了特定代理逻辑DjInvocationHandlerImpl只针对特定类target生效
        return Proxy.newProxyInstance(
                target.getClass().getClassLoader(), // 目标类的类加载
                target.getClass().getInterfaces(),  // 代理需要实现的接口，可指定多个
                new DjInvocationHandlerImpl(target)   // 代理对象对应的自定义 InvocationHandler
        );
    }
}