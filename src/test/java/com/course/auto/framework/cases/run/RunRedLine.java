package com.course.auto.framework.cases.run;

import com.course.auto.framework.alarm.callback.DefaultAlarmCallback;
import com.course.auto.framework.annotation.CaseSelector;
import com.course.auto.framework.annotation.DingTalkAlarm;

public class RunRedLine {

    @CaseSelector(scanPackage = "com.course.auto.framework.cases.order", key = "level", val = "redline")
    @DingTalkAlarm(token = "70d088f5669bd54f7f4a183a5d28e9973eab2111f572b39e8d286c2550d4ff54", callback = DefaultAlarmCallback.class)
    public void runPayRedLine() {

    }

    @CaseSelector(scanPackage = "com.course.auto.framework.cases.account", team = "abc", group = "123")
    public void runGroup() {

    }
}
