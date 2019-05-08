package com.dist.base.utils;

import org.apache.commons.lang3.StringUtils;

import java.util.List;

/**
 * object对象工具
 */
public abstract class ObjectUtil {

    /**
     * 传入的对象都不为null
     *
     * @param args
     * @return
     */
    public static boolean isNonNull(Object... args) {

        for (Object arg : args) {
            if (arg == null) {
                return false;
            }
            if (arg instanceof String) {
                if (StringUtils.isBlank((String) arg)) {
                    return false;
                }
            }
            if (arg instanceof List) {
                List list = (List) arg;
                if (list.size() <= 0) {
                    return false;
                }
            }

        }

        return true;
    }


}
