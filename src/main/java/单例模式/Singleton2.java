package 单例模式;

/**
 * 线程安全的懒汉式
 */
public class Singleton2 {
    // 2、定义一个静态变量指向自己类型
    private static Singleton2 instance = null;

    // 1、私有化构造方法
    private Singleton2() {
    }

    // 3、对外提供一个公共的方法获取实例
    //线程安全，可以延迟加载，但加锁效率不高
    //每一次调用 getInstance 获取实例时 都需要加锁和释放锁
    public static synchronized Singleton2 getInstance() {
        if(instance == null){
            instance = new Singleton2();
        }
        return instance;
    }
}
