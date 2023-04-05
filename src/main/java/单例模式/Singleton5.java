package 单例模式;

/**
 * 双重检验锁 double-checked locking 方式实现单例模式
 */
public class Singleton5 {

  // 2、定义一个静态变量指向自己类型
  // volatile 用来禁止指令重排序
  private volatile static Singleton5 uniqueInstance;

  // 1、私有化构造方法
  private Singleton5() {
  }

  // 3、对外提供一个公共的方法获取实例
  public static Singleton5 getUniqueInstance() {
    // 第一重检查是否为 null
    // 先判断对象是否已经实例过，没有实例化过才进入加锁代码
    if (uniqueInstance == null) {
      //类对象加锁
      synchronized (Singleton5.class) {
        // 第二重检查是否为 null
        if (uniqueInstance == null) {
          // new 关键字创建对象不是原子操作
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


//这里的双重检查是指两次非空判断，锁指的是 synchronized 加锁，
// 为什么 要进行双重判断，其实很简单，第一重判断，如果实例已经存在，
// 那么就 不再需要进行同步操作，而是直接返回这个实例，如果没有创建，
// 才会进 入同步块，同步块的目的与之前相同，目的是为了防止有多个线程同时调 用时，
// 导致生成多个实例，有了同步块，每次只能有一个线程调用访问同 步块内容，
// 当第一个抢到锁的调用获取了实例之后，这个实例就会被创 建，之后的所有调用都不会进入同步块，
// 直接在第一重判断就返回了单 例。

//    关于内部的第二重空判断的作用，当多个线程一起到达锁位置时，进行锁 竞争，
//    其中一个线程获取锁，如果是第一次进入则为 null，会进行单例对 象的创建，
//    完成后释放锁，其他线程获取锁后就会被空判断拦截，直接返 回已创建的单例对象。


