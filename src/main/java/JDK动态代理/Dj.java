package JDK动态代理;

/**
 * Created by Jiang on 2019-10-29.
 * JDK动态代理是java.lang.reflect包提供的方式，必须借助一个接口才能产生代理对象。
 * 被代理的类要实现该接口。
 */
public interface Dj {
    public void saySomething();
    public void saySomethingElse();
}
