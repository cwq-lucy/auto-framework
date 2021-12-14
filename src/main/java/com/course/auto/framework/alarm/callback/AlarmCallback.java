package com.course.auto.framework.alarm.callback;

import com.course.auto.framework.model.FailureResult;

public interface AlarmCallback {

    void postExecutionFalure(FailureResult failureResult);
}
