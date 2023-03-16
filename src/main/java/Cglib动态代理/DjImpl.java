package Cglib动态代理;

/**
 * Created by Jiang on 2019-10-29.
 * 被代理的类(必须是非抽象类)
 * 注意：不需要实现接口
 */
public class DjImpl {
    public void saySomething() {
        System.out.println("Dj Khaled!");
    }

    public void saySomethingElse() {
        System.out.println("Another one!");
    }
}
