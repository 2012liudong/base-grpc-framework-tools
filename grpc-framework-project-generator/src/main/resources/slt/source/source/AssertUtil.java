package ${package};

import cn.hutool.core.util.StrUtil;
import ${commonModule}.exception.AppException;

/**
 * @Title: ${package}.AppAssertUtil
 * @Description
 * @author liudong
 * @date 2022-09-16 10:34 p.m.
 */
public final class AssertUtil {

    public static void isTrue(boolean expression, String message) {
        if (expression) {
            return;
        }
        throw new AppException(message);
    }

    public static void iTrue(boolean expression, Integer code,String message) {
        if (expression) {
            return;
        }
        throw new AppException(code, message);
    }

    public static void isFalse(boolean expression, String message) {
        if (expression) {
            throw new AppException(message);
        }
    }

    public static void isFalse(boolean expression, Integer code,String message) {
        if (expression) {
            throw new AppException(code,message);
        }
    }

    public static void isEmpty(String object, String message) {
        if (StrUtil.isEmpty(object)) {
            throw new AppException(message);
        }
    }
}
