package 类执行顺序;

/**
 * Created by Jiang on 2019-10-31.
 */
public class HelloB extends HelloA {
    public HelloB() {
        System.out.println("子类构造函数");
    }

    {
        System.out.println("子类类变量");
    }

    static {
        System.out.println("子类静态区");
    }
}
