package com.thinkgem.jeesite.freetek.util.annotation;
import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

/**
 * 不能为空校验
 * 使用方法 直接加@NotEmpty(message = "错误描述信息")
 * @author Young
 * @version 2016/9/27
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target( { ElementType.FIELD, ElementType.PARAMETER })
@Constraint(validatedBy = { NotEmptyValidator.class })
public @interface NotEmpty {

    String message() default "{com.thinkgem.jeesite.freetek.util.annotation.NotEmpty.message}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
