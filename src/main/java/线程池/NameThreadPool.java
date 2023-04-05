package 线程池;

import com.google.common.util.concurrent.ThreadFactoryBuilder;

import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
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
    Future<?> future = threadPool.submit(new Runnable() {
      @Override
      public void run() {
        // task1
      }
    });

    try {
      // future.get()方法会阻塞当前线程直到任务完成
      Object o = future.get();
    } catch (InterruptedException e) {
      throw new RuntimeException(e);
    } catch (ExecutionException e) {
      throw new RuntimeException(e);
    }

    Future<?> future2 = threadPool.submit(new Callable<Integer>() {
      @Override
      public Integer call() throws Exception {
        // task2
        return 0;
      }
    });

    threadPool.execute(new Runnable() {
      @Override
      public void run() {
        // task3
      }
    });

    List<Runnable> list = threadPool.shutdownNow();
//    threadPool.shutdown();

  }
}
