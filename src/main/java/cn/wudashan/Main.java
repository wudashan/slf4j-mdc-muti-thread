package cn.wudashan;

import cn.wudashan.executor.MDCRunnable;
import cn.wudashan.util.MDCUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 主函数
 * @author wudashan
 */
public class Main {

    private static final Logger logger = LoggerFactory.getLogger(Main.class);
    private static final ExecutorService EXECUTOR = Executors.newSingleThreadExecutor();

    public static void main(String[] args) {

        // 入口传入请求ID
        MDCUtil.putRequestId();

        // 主线程打印日志
        logger.debug("log in main thread");

        // 异步线程打印日志，用MDCRunnable装饰Runnable
        new Thread(new MDCRunnable(new Runnable() {
            @Override
            public void run() {
                logger.debug("log in other thread");
            }
        })).start();

        // 异步线程池打印日志，用MDCRunnable装饰Runnable
        EXECUTOR.execute(new MDCRunnable(new Runnable() {
            @Override
            public void run() {
                logger.debug("log in other thread pool");
            }
        }));
        EXECUTOR.shutdown();

        // 出口移除请求ID
        MDCUtil.removeRequestId();

    }

}
