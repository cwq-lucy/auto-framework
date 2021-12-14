package com.course.auto.framework.annotation;

import java.lang.annotation.*;

@Target({ElementType.ANNOTATION_TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Repeatable(CheckPoints.class)
public @interface CheckPoint {

    String value();
}