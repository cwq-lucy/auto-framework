package com.course.auto.framework.alarm;

import com.course.auto.framework.alarm.service.AlarmService;
import com.course.auto.framework.model.FailureResult;

public class AlarmFacade {

    public static void doAlarm(FailureResult failureResult) {

        new AlarmService().doAlarm(failureResult);


    }
}
