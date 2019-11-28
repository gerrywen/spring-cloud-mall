package com.mall.common.base.validate;

import com.mall.common.base.annotation.CustomerValidate;
import org.springframework.util.ObjectUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * 不使用框架validate 则直接用切面实现
 * @author wenguoli
 * @date 2019/9/27 14:30
 */
public class CustomerValidator implements ConstraintValidator<CustomerValidate, Object> {
    private String rule;//验证规则
    private String value;//字典值


    @Override
    public void initialize(CustomerValidate constraintAnnotation) {
        rule = constraintAnnotation.rule();
        value = constraintAnnotation.value();
    }

    @Override
    public boolean isValid(Object s, ConstraintValidatorContext constraintValidatorContext) {
        if (ObjectUtils.isEmpty(s)) {
            return true;
        }
        return ValidateRules._valid(s.toString(), rule, value);
    }

}
