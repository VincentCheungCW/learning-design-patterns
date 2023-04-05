package 单例模式;

/**
 * 懒汉式，线程不安全
 *
 */
public class Singleton {
  // 1、私有化构造方法
  private Singleton() {
  }

  // 2、定义一个静态变量指向自己类型
  private static Singleton instance;

  // 3、对外提供一个公共的方法获取实例
  public static Singleton getInstance() {
// 判断为 null 的时候再创建对象
    if (instance == null) {
      instance = new Singleton();
    }
    return instance;
  }
}