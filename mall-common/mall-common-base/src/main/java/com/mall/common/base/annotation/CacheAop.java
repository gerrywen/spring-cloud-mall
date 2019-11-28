package com.mall.common.base.annotation;

import java.lang.annotation.*;

/**
 * @author wenguoli
 * @date 2019/9/27 14:27
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface CacheAop {
    String[] value() default {};;//redis_key前半部分
    String op() default "";//执行的操作CachePut,CacheEvict,Cacheable
}
