package 类执行顺序;

/**
 * Created by Jiang on 2019-10-31.
 */
public class HelloA {
    public HelloA() {
        System.out.println("父类构造函数");
    }

    {
        System.out.println("父类类变量");
    }

    static {
        System.out.println("父类静态区");
    }
}


