静态代理中，我们对目标对象的每个方法的增强都是手动完成的，
非常不灵活且麻烦(需要对每个目标类都写一个代理类）。 
实际应用场景非常少，日常开发几乎看不到使用静态代理的场景。
从 JVM 层面来说，静态代理在编译时就将接口、实现类、代理类这些都变成了一个个实际的 class 文件。

静态代理实现步骤:
 - 定义一个接口及其实现类；
 - 创建一个代理类同样实现这个接口将目标对象注入进代理类，然后在代理类的对应方法调用目标类中的对应方法。 
这样的话，我们就可以通过代理类屏蔽对目标对象的访问，并且可以在目标方法执行前后做一些自己想做的事情。
