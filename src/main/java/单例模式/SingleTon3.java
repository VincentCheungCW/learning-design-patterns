package 单例模式;

/**
 *  静态内部类实现单例
 */
public class SingleTon3 {
    // 1、私有化构造方法
    private SingleTon3() {
    }

    // 定义静态内部类
    private static class Holder{
        private static SingleTon3 instance = new SingleTon3();
    }

    // 2、对外提供获取实例的公共方法
    //利用静态私有内部类，线程安全且可以延迟加载，不必加锁
    public static SingleTon3 getInstance(){
        return Holder.instance;
    }
}

