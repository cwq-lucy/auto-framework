package com.course.auto.framework.engine;

import com.course.auto.framework.annotation.CaseSelector;
import com.course.auto.framework.annotation.DingTalkAlarm;
import com.course.auto.framework.annotation.ReportConfig;
import com.course.auto.framework.engine.filter.CaseGroupDiscoveryFilter;
import com.course.auto.framework.engine.filter.CaseTagDiscoveryFilter;
import com.course.auto.framework.engine.listener.FailureListener;
import com.course.auto.framework.model.FailureInfo;
import com.course.auto.framework.model.SummaryResult;
import com.course.auto.framework.util.ReflectUtils;
import com.course.auto.framework.util.RequiredUtils;
import org.junit.jupiter.api.extension.BeforeTestExecutionCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.platform.engine.TestSource;
import org.junit.platform.engine.discovery.DiscoverySelectors;
import org.junit.platform.engine.support.descriptor.MethodSource;
import org.junit.platform.launcher.LauncherDiscoveryRequest;
import org.junit.platform.launcher.TestIdentifier;
import org.junit.platform.launcher.core.LauncherDiscoveryRequestBuilder;
import org.junit.platform.launcher.core.LauncherFactory;
import org.junit.platform.launcher.listeners.SummaryGeneratingListener;
import org.junit.platform.launcher.listeners.TestExecutionSummary;

import java.lang.reflect.Method;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

//测试用例引擎扩展类 ，实现测试执行回调逻辑前类
public class CaseEngineExtension implements BeforeTestExecutionCallback {


    @Override
    public void beforeTestExecution(ExtensionContext context) throws Exception {
//        System.out.println("CaseSelectension.beforeTestExecution");
        //1.先拿到runPayRedLine()方法
        Method testMethod = context.getRequiredTestMethod();
        //2.基于反射拿到其他注解
        CaseSelector selector = invalidedSelector(testMethod.getAnnotation(CaseSelector.class));
        boolean isAlarmSet = testMethod.isAnnotationPresent(DingTalkAlarm.class);

        // 构造发现用例的请求实体
        LauncherDiscoveryRequest request = LauncherDiscoveryRequestBuilder.request()
                //选择器，DiscoverySelectors.selectPackage(selector.scanPackage())基于包名进行选择
                .selectors(DiscoverySelectors.selectPackage(selector.scanPackage()))
                //过滤器，
                .filters(new CaseTagDiscoveryFilter(selector),
                        new CaseGroupDiscoveryFilter(selector))
                .build();

        //生成测试报告
        SummaryGeneratingListener summaryGeneratingListener = new SummaryGeneratingListener();

        //处理失败的监听器，自定义的
        if (isAlarmSet) {
            DingTalkAlarm dingTalkAlarm = testMethod.getAnnotation(DingTalkAlarm.class);
            //case运行失败，监听回调
            FailureListener failureListener = new FailureListener(dingTalkAlarm.token(), dingTalkAlarm.callback());

            //返回失败信息failureListener  收集失败信息生成报告
            LauncherFactory.create().execute(request, summaryGeneratingListener, failureListener);
        } else {
            //运行成功不返回信息   收集成功信息生成报告
            LauncherFactory.create().execute(request, summaryGeneratingListener);
        }


        boolean isReportConfigSet = testMethod.isAnnotationPresent(ReportConfig.class);
        if (isReportConfigSet) {
            ReportConfig reportConfig = testMethod.getAnnotation(ReportConfig.class);
            SummaryResult summaryResult = transToSummaryResult(summaryGeneratingListener.getSummary());
            summaryResult.setToken(reportConfig.token());

            //工具类处理实例，报告配置回调  提交执行测试报告及测试结果
            ReflectUtils.newInstance(reportConfig.callback()).postExecutionSummary(summaryResult);

        }

//        TestExecutionSummary summary = summaryGeneratingListener.getSummary();
//        SummaryResult summaryResult = transToSummaryResult(summary);

    }

    private SummaryResult transToSummaryResult(TestExecutionSummary summary) {
        SummaryResult summaryResult = new SummaryResult();
        summaryResult.setAbortedCount(summary.getTestsAbortedCount());
        summaryResult.setFailedCount(summary.getTestsFailedCount());
        summaryResult.setFailureInfoList(parseFailureInfoList(summary.getFailures()));
        summaryResult.setFoundCount(summary.getTestsFoundCount());
        summaryResult.setSkippedCount(summary.getTestsStartedCount());
        summaryResult.setSucceededCount(summary.getTestsSucceededCount());
        summaryResult.setTimeFinished(summary.getTimeFinished());
        summaryResult.setTimeStarted(summary.getTimeStarted());
        summaryResult.setTotalFailureCount(summary.getTotalFailureCount());

        return summaryResult;

    }


    private List<FailureInfo> parseFailureInfoList(List<TestExecutionSummary.Failure> failures) {
        return failures.stream().map(failure -> {
            TestIdentifier testIdentifier = failure.getTestIdentifier();
            Optional<TestSource> optionalTestSource = testIdentifier.getSource();
            TestSource testSource = optionalTestSource.get();
            MethodSource methodSource = (MethodSource) testSource;

            FailureInfo failureInfo = new FailureInfo();
            failureInfo.setClassName(methodSource.getClassName());
            failureInfo.setMethodName(methodSource.getMethodName());
            failureInfo.setParameterTypes(methodSource.getMethodParameterTypes());
            failureInfo.setThrowable(failure.getException());

            return failureInfo;
        }).collect(Collectors.toList());
    }

    private CaseSelector invalidedSelector(CaseSelector selector) {
        RequiredUtils.requiredNotNull(selector, "not null");
        RequiredUtils.requireNotNullOrEmpty(selector.scanPackage(), "not null");

        //TODO 需要判断key和val都不能为空，写了一个为前提条件
        selector.key();
        selector.val();

        return selector;
    }
}
