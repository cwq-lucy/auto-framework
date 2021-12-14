package com.course.auto.framework.annotation;

import com.course.auto.framework.alarm.AlarmExtension;
import com.course.auto.framework.alarm.callback.AlarmCallback;
import org.junit.jupiter.api.extension.ExtendWith;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.ANNOTATION_TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@ExtendWith(AlarmExtension.class)
public @interface DingTalkAlarm {

    String token();

    Class<? extends AlarmCallback> callback();


}