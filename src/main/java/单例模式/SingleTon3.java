package 单例模式;

public class SingleTon3 {
    private SingleTon3() {
    }

    private static class Holder{
        private static SingleTon3 instance = new SingleTon3();
    }

    //利用静态私有内部类，线程安全且可以延迟加载，不必加锁
    public static SingleTon3 getInstance(){
        return Holder.instance;
    }
}
