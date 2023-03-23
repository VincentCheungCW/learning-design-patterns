package 线程池;

import com.google.common.util.concurrent.ThreadFactoryBuilder;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;


public class NameThreadPool {

  public static void main(String[] args) {
    String threadNamePrefix = "java-patterns";
    // 初始化线程池的时候需要显示命名（设置线程池名称前缀），有利于定位问题。
    // 默认情况下创建的线程名字类似 pool-1-thread-n 这样的，没有业务含义。
    // 给线程池里的线程命名可以利用 guava 的 ThreadFactoryBuilder
    // 也可以自己实现 ThreadFactory
    ThreadFactory threadFactory = new ThreadFactoryBuilder()
        .setNameFormat(threadNamePrefix + "-%d")
        .setDaemon(true).build();
    int maximumPoolSize = 5;
    int corePoolSize = 5;
    LinkedBlockingQueue workQueue = new LinkedBlockingQueue();
    long keepAliveTime = 1000;
    ExecutorService threadPool = new ThreadPoolExecutor(
        corePoolSize,
        maximumPoolSize,
        keepAliveTime,
        TimeUnit.MINUTES,
        workQueue,
        threadFactory);

  }
}
