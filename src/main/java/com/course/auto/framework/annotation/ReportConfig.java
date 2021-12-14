package com.course.auto.framework.annotation;

import com.course.auto.framework.model.ReportType;
import com.course.auto.framework.report.callback.ReportCallback;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface ReportConfig {

    ReportType type() default ReportType.DING_TALK;

    String token();

    Class<? extends ReportCallback> callback();
}
