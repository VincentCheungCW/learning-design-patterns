package 单例模式;


/**
 * 枚举实现单例
 */
public enum Singleton4 {
  INSTANCE;

  public void doSomething(String str) {
    System.out.println(str);
  }
}

//  枚举在 java 中与普通类一样，都能拥有字段与方法，而且枚举实例创建是 线程安全的，
//  在任何情况下，它都是一个单例，可以直接通过如下方式调 用获取实例:
//  Singleton singleton = Singleton.INSTANCE;
//优点:简单，高效，线程安全，可以避免通过反射破坏枚举单例