package com.course.auto.framework.annotation;


import com.course.auto.framework.driver.DataDriverExtension;
import com.course.auto.framework.driver.DataDriverExtension2;
import com.course.auto.framework.format.observer.CaseFormatExtension;
import org.junit.jupiter.api.TestTemplate;
import org.junit.jupiter.api.extension.ExtendWith;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 数据驱动的注解
 */
@Target({ElementType.ANNOTATION_TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@ExtendWith(DataDriverExtension.class)
//@ExtendWith(DataDriverExtension2.class)
@ExtendWith(CaseFormatExtension.class)
@TestTemplate // 关键注解
public @interface DataDriver {
    String path();
}
