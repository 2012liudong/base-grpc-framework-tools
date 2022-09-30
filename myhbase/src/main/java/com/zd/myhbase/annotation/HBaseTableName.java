package com.zd.myhbase.annotation;

import java.lang.annotation.*;

/**
 * @Title: com.shukun.data.common.entity.hbase.annotation.HBaseTableName
 * @Description Hbase表相关的解析标
 * @author liudong
 * @date 2022-08-03 4:27 p.m.
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE, ElementType.ANNOTATION_TYPE})
public @interface HBaseTableName {

    String value() default "";

    String family() default "";

}
