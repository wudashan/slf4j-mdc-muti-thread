package cn.wudashan.util;

import org.slf4j.MDC;

import java.util.Collections;
import java.util.Map;

/**
 * MDC工具类，屏蔽异常和空指针
 * @author wudashan
 */
public class MDCUtil {

    public static Map<String, String> getCopyOfContextMap() {
        Map<String, String> result;
        try {
            result = MDC.getCopyOfContextMap();
        } catch (RuntimeException ignored) {
            result = Collections.emptyMap();
        }
        if (result == null) {
            result = Collections.emptyMap();
        }
        return result;
    }

    public static void put(String key, String value) {
        try {
            MDC.put(key, value);
        } catch (RuntimeException ignored) {}
    }

    public static void remove(String key) {
        try {
            MDC.remove(key);
        } catch (RuntimeException ignored) {}
    }

}
