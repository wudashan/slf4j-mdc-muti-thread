package cn.wudashan.executor;

import cn.wudashan.util.MDCUtil;

import java.util.Map;

/**
 * 基本任务类
 * @author wudashan
 */
public class BaseRunnable implements Runnable {

    private final Runnable runnable;

    private final Map<String, String> map;

    public BaseRunnable(Runnable runnable) {
        this.runnable = runnable;
        this.map = MDCUtil.getCopyOfContextMap();
    }

    @Override
    public void run() {
        for (Map.Entry<String, String> entry : map.entrySet()) {
            MDCUtil.put(entry.getKey(), entry.getValue());
        }
        runnable.run();
        for (Map.Entry<String, String> entry : map.entrySet()) {
            MDCUtil.remove(entry.getKey());
        }

    }


}
