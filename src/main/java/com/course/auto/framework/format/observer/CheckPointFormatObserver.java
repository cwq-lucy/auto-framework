package com.course.auto.framework.format.observer;


import com.course.auto.framework.annotation.CheckPoint;
import com.course.auto.framework.exception.IllegalFormatException;
import com.course.auto.framework.format.FormatObserver;
import com.course.auto.framework.util.RequiredUtils;

import java.lang.reflect.Method;

public class CheckPointFormatObserver implements FormatObserver {

    @Override
    public void format(Method testMethod) {
        CheckPoint[] checkPoints = testMethod.getAnnotationsByType(CheckPoint.class);
        if (checkPoints.length == 0) {
            throw new IllegalFormatException("@CheckPoint should be set, eg: @CheckPoint(\"检查点\")");
        }

        for (CheckPoint checkPoint : checkPoints) {
            RequiredUtils.requireNotNullOrEmpty(checkPoint.value(), "CheckPoint 'value' should not be null or empty");
        }
    }
}