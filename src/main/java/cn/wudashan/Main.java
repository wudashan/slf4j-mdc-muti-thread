package cn.wudashan;

import cn.wudashan.executor.BaseThreadPoolExecutor;
import cn.wudashan.util.MDCUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.UUID;

/**
 * 主函数
 * @author wudashan
 */
public class Main {

    private static final String KEY = "requestId";

    private static final Logger logger = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) {

        MDCUtil.put(KEY, UUID.randomUUID().toString());

        logger.debug("log in main thread");

        BaseThreadPoolExecutor executor = new BaseThreadPoolExecutor();
        executor.execute(new Runnable() {
            @Override
            public void run() {
                logger.debug("log in base thread pool");
            }
        });
        executor.shutdown();

        MDCUtil.remove(KEY);

    }

}
