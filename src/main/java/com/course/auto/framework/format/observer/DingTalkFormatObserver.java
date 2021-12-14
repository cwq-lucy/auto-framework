package com.course.auto.framework.format.observer;


import com.course.auto.framework.annotation.DingTalkAlarm;
import com.course.auto.framework.format.FormatObserver;
import com.course.auto.framework.util.RequiredUtils;

import java.lang.reflect.Method;

public class DingTalkFormatObserver implements FormatObserver {

    @Override
    public void format(Method testMethod) {
        boolean isDingTalkAlarmSet = testMethod.isAnnotationPresent(DingTalkAlarm.class);
        if (!isDingTalkAlarmSet) {
            return;
        }

        DingTalkAlarm dingTalkAlarm = testMethod.getAnnotation(DingTalkAlarm.class);
        RequiredUtils.requireNotNullOrEmpty(dingTalkAlarm.token(), "DingTalkAlarm 'token' should not be null or empty");
    }
}