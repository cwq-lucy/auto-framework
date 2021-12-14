package com.course.auto.framework.alarm.callback;

import com.course.auto.framework.alarm.AlarmFacade;
import com.course.auto.framework.model.FailureResult;

public class DefaultAlarmCallback implements AlarmCallback {


    @Override
    public void postExecutionFalure(FailureResult failureResult) {
        AlarmFacade.doAlarm(failureResult);

    }
}
