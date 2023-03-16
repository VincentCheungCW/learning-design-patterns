package 动态代理.Cglib动态代理;

/**
 * Created by Jiang on 2019-10-29.
 */
public class Test {
  public static void main(String[] args) {
    //关联真实类与代理类
    DjImpl proxy = (DjImpl) CglibProxyFactory.getProxy(DjImpl.class);
    proxy.saySomething();
    proxy.saySomethingElse();
  }
}
