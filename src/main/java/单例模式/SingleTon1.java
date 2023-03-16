package 单例模式;

public class SingleTon1 {
    private static SingleTon1 instance = new SingleTon1();

    private SingleTon1() {
    }

    //线程安全但不能延迟加载
    public static SingleTon1 getInstance() {
        return instance; //原子操作
    }
}
