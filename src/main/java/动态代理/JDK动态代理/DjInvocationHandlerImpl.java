package 动态代理.JDK动态代理;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * Created by Jiang on 2019-10-29.
 * 在JDK动态代理中，依靠InvocationHandler去实现代理逻辑类。
 * 两个关键步骤：
 * 1.实现代理逻辑（implement InvocationHandler接口，override invoke方法）
 * 2.建立代理对象和真实对象的关系 Proxy.newProxyInstance() 传入InvocationHandler实现类
 */
public class DjInvocationHandlerImpl implements InvocationHandler {
    //真实对象
    private final Object target;

    public DjInvocationHandlerImpl(Object target) {
        this.target = target;
    }

    /**
     * 重写invoke方法，实现代理逻辑
     *
     * @param proxy  代理对象，即bind方法生成的对象
     * @param method 当前调度的方法
     * @param args
     * @return
     * @throws Throwable
     */
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Object obj;
        // 在这里过滤特定方法
        if (method.getName().equalsIgnoreCase("saySomething")) {
            System.out.println("Before真实对象的方法");
            //调用真实对象的方法
            obj = method.invoke(target, args);
            System.out.println("After真实对象的方法");
        } else {
            obj = method.invoke(target, args);
        }
        return obj;
    }
}
