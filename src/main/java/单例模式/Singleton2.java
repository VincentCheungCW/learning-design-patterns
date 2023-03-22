package 单例模式;

public class Singleton2 {
    private static Singleton2 instance = null;

    private Singleton2() {
    }

    //线程安全，可以延迟加载，但加锁效率不高
    public static synchronized Singleton2 getInstance() {
        if(instance == null){
            instance = new Singleton2();
        }
        return instance;
    }
}
