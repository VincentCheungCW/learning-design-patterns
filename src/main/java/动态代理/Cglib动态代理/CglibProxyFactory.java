package 动态代理.Cglib动态代理;

import org.mockito.cglib.proxy.Enhancer;

public class CglibProxyFactory {

    public static Object getProxy(Class<?> clazz) {
        // 由cglib提供的Enhancer
        Enhancer enhancer = new Enhancer();
        // 设置类加载器
        enhancer.setClassLoader(clazz.getClassLoader());
        // 设置真实类为超类(基本思想)
        enhancer.setSuperclass(clazz);
        // 设置代理拦截器为callback
        enhancer.setCallback(new MethodInterceptorImpl());
        return enhancer.create();
    }
}