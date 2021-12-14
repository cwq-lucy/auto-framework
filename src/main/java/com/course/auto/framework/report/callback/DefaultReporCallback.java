package com.course.auto.framework.report.callback;

import com.course.auto.framework.Template.TemplateFacade;
import com.course.auto.framework.http.HttpFacade;
import com.course.auto.framework.model.SummaryResult;
import com.google.common.collect.Maps;

import java.text.SimpleDateFormat;
import java.util.Map;
import java.util.stream.Collectors;

public class DefaultReporCallback implements ReportCallback {


    @Override
    public void postExecutionSummary(SummaryResult summaryResult) {
        //转换时间戳格式
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        Map<String, Object> templateMapping = Maps.newHashMap();
        templateMapping.put("successful_count", summaryResult.getSucceededCount());
        templateMapping.put("failed_count", summaryResult.getFailedCount());
        templateMapping.put("total_fount", summaryResult.getFoundCount());
        templateMapping.put("start_time", sdf.format(Long.parseLong(String.valueOf(summaryResult.getTimeStarted()))));
        templateMapping.put("end_time", sdf.format(Long.parseLong(String.valueOf(summaryResult.getTimeStarted()))));
        templateMapping.put("failure_reason", summaryResult.getFailureInfoList().stream()
                //以stream方式处理异常信息，收集者将正在运行的异常信息添加收集起来
                .map(f -> f.getThrowable().getMessage()).collect(Collectors.joining(";")));

        String reportText = TemplateFacade.replaceTemplate("default_report_template", templateMapping);
        Map<String, Object> param = Maps.newHashMap();
        param.put("msgtype", "text");

        Map<String, Object> data = Maps.newHashMap();
        data.put("content", reportText);

        param.put("text", data);
        String response = HttpFacade.doPostJson(DING_TALK_ROOT_URL + summaryResult.getToken(), param);

    }

    private static final String DING_TALK_ROOT_URL = "https://oapi.dingtalk.com/robot/send?access_token=";

}
