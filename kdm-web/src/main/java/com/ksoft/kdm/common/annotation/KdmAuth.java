package com.ksoft.kdm.common.annotation;

import java.lang.annotation.*;

/**
 * @author zhangjianbo
 * @date 2017/6/1
 */
@Documented
@Target({ElementType.METHOD})
@Inherited
@Retention(RetentionPolicy.RUNTIME)
public @interface KdmAuth {
    String role() default "";
}
