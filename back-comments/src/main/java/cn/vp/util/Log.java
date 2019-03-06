package cn.vp.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 日志封装类
 */
public class Log {
    static Logger logger = LoggerFactory.getLogger(Log.class);

    public static void i(Class<?> clazz, String message) {
        if (clazz != null)
            logger = LoggerFactory.getLogger(clazz);
        logger.info(message);
    }

    public static void e(Class<?> clazz, String message) {
        if (clazz != null)
            logger = LoggerFactory.getLogger(clazz);
        logger.error(message);
    }

}
