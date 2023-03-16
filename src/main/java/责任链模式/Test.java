package 责任链模式;

import 动态代理.JDK动态代理.Dj;
import 动态代理.JDK动态代理.DjImpl;
import 拦截器.InvocationHandlerImpl;

/**
 * Created by Jiang on 2019-10-29.
 * 责任链模式：一个对象在一条链上被多个拦截器处理，适用于一个对象在多个角色中传递的场景
 */
public class Test {
    public static void main(String[] args) {
        Dj proxy1 = (Dj) InvocationHandlerImpl.bind(
                new DjImpl(),
                "责任链模式.Interceptor1");
        Dj proxy2 = (Dj) InvocationHandlerImpl.bind(
                proxy1,
                "责任链模式.Interceptor2");
        Dj proxy3 = (Dj) InvocationHandlerImpl.bind(
                proxy2,
                "责任链模式.Interceptor3");
        proxy3.saySomething();
    }
}
