package 拦截器;

import JDK动态代理.Dj;
import JDK动态代理.DjImpl;

/**
 * Created by Jiang on 2019-10-29.
 */
public class Test {
    public static void main(String[] args) {
        Dj proxy = (Dj)InvocationHandlerImpl.bind(
                new DjImpl(),
                "拦截器.MyInterceptor");
        proxy.saySomething();
        proxy.saySomethingElse();
    }
}
