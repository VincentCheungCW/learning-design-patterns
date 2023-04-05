package 单例模式;

import java.lang.reflect.Constructor;

/**
 * * 所谓的饿汉式，类一加载就创建对象，无锁，容易产生垃圾对象，浪费内存空间。
 */
public class SingleTon1 {
  // 1、私有化构造方法
  private SingleTon1() {
  }

  // 2、定义一个静态变量指向自己类型
  private static final SingleTon1 instance = new SingleTon1();

  //线程安全但不能延迟加载
  // 3、对外提供一个公共的方法获取实例
  public static SingleTon1 getInstance() {
    return instance; //原子操作
  }

  /**
   * 可以使用反射破坏单例
   */
  public static void main(String[] args) throws Exception {
    // 获取空参构造方法
    Constructor<SingleTon1> declaredConstructor =
        SingleTon1.class.getDeclaredConstructor(null);
    // 设置强制访问
    declaredConstructor.setAccessible(true);
    // 创建实例
    SingleTon1 singleton = declaredConstructor.newInstance();

    System.out.println("反射创建的实例" + singleton);
    System.out.println("正常创建的实例" + SingleTon1.getInstance());
    System.out.println("正常创建的实例" + SingleTon1.getInstance());
  }
}



