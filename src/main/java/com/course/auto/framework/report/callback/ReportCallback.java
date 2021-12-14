package com.course.auto.framework.report.callback;

import com.course.auto.framework.model.SummaryResult;

public interface ReportCallback {

    //提交执行报告方法  参数为报告结果
    void postExecutionSummary(SummaryResult summaryResult);

}
