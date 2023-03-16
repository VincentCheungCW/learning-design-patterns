package 动态代理.JDK动态代理;

/**
 * Created by Jiang on 2019-10-29.
 * 被代理的类。
 */
public class DjImpl implements Dj {
    @Override
    public void saySomething() {
        System.out.println("Dj Khaled!");
    }

    @Override
    public void saySomethingElse() {
        System.out.println("Another one!");
    }
}
