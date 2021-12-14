package com.course.auto.framework.annotation;

import java.lang.annotation.*;

@Target({ElementType.ANNOTATION_TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface CheckPoints {

    CheckPoint[] value();
}