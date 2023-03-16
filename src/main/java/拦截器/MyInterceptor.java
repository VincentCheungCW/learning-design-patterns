package 拦截器;

import java.lang.reflect.Method;

/**
 * 用户实现Interceptor接口
 * Created by Jiang on 2019-10-29.
 */
public class MyInterceptor implements Interceptor {
    @Override
    public boolean before(Object proxy, Object target, Method method, Object[] args) {
        if(method.getName().equalsIgnoreCase("saySomething")){
            System.out.println("Before真实对象的方法");
            //用around方法替代真实方法
            return false;
        }else {
            return true;
        }
    }

    @Override
    public void around(Object proxy, Object target, Method method, Object[] args) {
        System.out.println("取代了被代理对象的方法");
    }

    @Override
    public void after(Object proxy, Object target, Method method, Object[] args) {
        if(method.getName().equalsIgnoreCase("saySomething")){
            System.out.println("After真实对象的方法");
        }
    }
}
