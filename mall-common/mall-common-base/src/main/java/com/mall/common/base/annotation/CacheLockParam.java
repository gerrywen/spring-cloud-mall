package com.mall.common.base.annotation;

import java.lang.annotation.*;

/**
 * 锁的参数
 * @author wenguoli
 * @date 2019/9/27 14:28
 */
@Target({ElementType.PARAMETER, ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface CacheLockParam {
    /**
     * 字段名称
     *
     * @return String
     */
    String name() default "";
}
