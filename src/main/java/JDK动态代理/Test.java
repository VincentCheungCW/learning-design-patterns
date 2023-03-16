package JDK动态代理;

/**
 * Created by Jiang on 2019-10-29.
 */
public class Test {
    public static void main(String[] args) {
        //真实对象
        DjImpl target = new DjImpl();
        //关联真实对象，生成代理对象
        Dj proxy = (Dj) DjProxyFactory.getProxy(target);
        //调用接口方法，实际已被代理重写
        proxy.saySomething();
        proxy.saySomethingElse();
    }
}
