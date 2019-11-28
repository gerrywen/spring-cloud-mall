package com.mall.common.base.annotation;


import com.mall.common.base.validate.CustomerValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

/**
 * @author wenguoli
 * @date 2019/9/27 14:28
 */
@Target({ElementType.METHOD, ElementType.FIELD, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = CustomerValidator.class)
@Documented
public @interface CustomerValidate {
    String message() default "";

    String rule() default "";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    String value() default "";
}
