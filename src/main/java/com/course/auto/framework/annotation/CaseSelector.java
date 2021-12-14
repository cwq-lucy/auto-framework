package com.course.auto.framework.annotation;

import com.course.auto.framework.engine.CaseEngineExtension;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.ANNOTATION_TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@ExtendWith(CaseEngineExtension.class)
@Test
public @interface CaseSelector {
    String scanPackage();

    String key() default "";

    String val() default "";

    String team() default "";

    String group() default "";

}
