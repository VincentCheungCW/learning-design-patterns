相比于静态代理来说，动态代理更加灵活。
我们不需要针对每个目标类都单独创建一个代理类，并且也不需要我们必须实现接口，
我们可以直接代理实现类( CGLIB 动态代理机制)。

从 JVM 角度来说，动态代理是在运行时动态生成类字节码，并加载到 JVM 中的。

就 Java 来说，动态代理的实现方式有 JDK 动态代理、CGLIB 动态代理等。

