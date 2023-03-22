package 单例模式;

/**
 * 双重检验锁方式实现单例模式
 */
public class Singleton5 {

  // volatile 用来禁止指令重排序
  private volatile static Singleton5 uniqueInstance;

  private Singleton5() {
  }

  public static Singleton5 getUniqueInstance() {
    //先判断对象是否已经实例过，没有实例化过才进入加锁代码
    if (uniqueInstance == null) {
      //类对象加锁
      synchronized (Singleton5.class) {
        if (uniqueInstance == null) {
          uniqueInstance = new Singleton5();
        }
      }
    }
    return uniqueInstance;
  }
}

//uniqueInstance = new Singleton(); 这段代码其实是分为三步执行：
// 1.为 uniqueInstance 分配内存空间
// 2.初始化 uniqueInstance
// 3.将 uniqueInstance 指向分配的内存地址
// 由于 JVM 具有指令重排的特性，执行顺序有可能变成 1->3->2。
// 指令重排在单线程环境下不会出现问题，但是在多线程环境下会导致一个线程获得还没有初始化的实例。
// 例如，线程 T1 执行了 1 和 3，此时 T2 调用 getUniqueInstance() 后发现 uniqueInstance 不为空，
// 因此返回 uniqueInstance，但此时 uniqueInstance 还未被初始化。

// 把uniqueInstance定义为volatile可以解决这个问题
