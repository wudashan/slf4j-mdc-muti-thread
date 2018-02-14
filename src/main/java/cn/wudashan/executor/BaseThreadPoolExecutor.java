package cn.wudashan.executor;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 基本线程池类
 * @author wudashan
 */
public class BaseThreadPoolExecutor {

    private final ThreadPoolExecutor threadPoolExecutor;

    public BaseThreadPoolExecutor() {
        this.threadPoolExecutor = new ThreadPoolExecutor(10, 10, 0, TimeUnit.SECONDS, new ArrayBlockingQueue<>(10));
    }

    public void execute(Runnable runnable) {
        runnable = new BaseRunnable(runnable);
        threadPoolExecutor.execute(runnable);
    }

    public void shutdown() {
        threadPoolExecutor.shutdown();
    }




}
