package com.course.auto.framework.cases.run;

import com.course.auto.framework.alarm.callback.DefaultAlarmCallback;
import com.course.auto.framework.annotation.CaseSelector;
import com.course.auto.framework.annotation.DingTalkAlarm;
import com.course.auto.framework.annotation.ReportConfig;
import com.course.auto.framework.model.TokenConst;
import com.course.auto.framework.report.callback.DefaultReporCallback;

public class RunAlarmClass {

    @DingTalkAlarm(token = TokenConst.DEFAULT, callback = DefaultAlarmCallback.class)
    @ReportConfig(token = TokenConst.DEFAULT, callback = DefaultReporCallback.class)
    @CaseSelector(scanPackage = "com.course.auto.framework.cases.order")
    public void select1() {


    }


}
