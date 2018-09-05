package com.thinkgem.jeesite.freetek.util.annotation;

import com.thinkgem.jeesite.common.utils.StringUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * @author Young
 * @version 2016/9/27
 */
public class NotEmptyValidator implements ConstraintValidator<NotEmpty, Object>{

    @Override
    public void initialize(NotEmpty constraintAnnotation) {

    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
        if (value == null || StringUtils.isEmpty(value.toString())){
            return false;
        }
        return true;
    }
}
