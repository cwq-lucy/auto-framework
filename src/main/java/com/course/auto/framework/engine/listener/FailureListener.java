package com.course.auto.framework.engine.listener;


import com.course.auto.framework.alarm.callback.AlarmCallback;
import com.course.auto.framework.model.FailureResult;
import com.course.auto.framework.util.ReflectUtils;
import org.junit.platform.engine.TestExecutionResult;
import org.junit.platform.engine.TestSource;
import org.junit.platform.engine.support.descriptor.MethodSource;
import org.junit.platform.launcher.TestExecutionListener;
import org.junit.platform.launcher.TestIdentifier;

import java.util.Optional;

public class FailureListener implements TestExecutionListener {

    private String token;

    private Class<? extends AlarmCallback> alarmCallback;

    public FailureListener(String token, Class<? extends AlarmCallback> alarmCallback) {
        this.token = token;
        this.alarmCallback = alarmCallback;
    }


    @Override
    public void executionFinished(TestIdentifier testIdentifier, TestExecutionResult testExecutionResult) {


        TestExecutionResult.Status status = testExecutionResult.getStatus();
        // 基于status做判断,当status==Status.FAILED时,表示这个用例执行失败.此刻我们在批量运行用例时就拿到了单个用例执行失败后的一个回调
        // 既然在这里做了失败的回调，那此时就可以将必要的信息处理之后，做发出(报警)
        if (TestExecutionResult.Status.FAILED != status) {
            return;

        }

        //1,将回调回来的信息做一个封装!
        Optional<TestSource> optional = testIdentifier.getSource();
        if (!optional.isPresent()) {
            return;
        }
        TestSource testSource = optional.get();
        if (!(testSource instanceof MethodSource)) {
            return;
        }

        MethodSource methodSource = (MethodSource) testSource;
        String className = methodSource.getClassName();
        String methodName = methodSource.getMethodName();
        String parameterTypes = methodSource.getMethodParameterTypes();


        Optional<Throwable> throwableOptional = testExecutionResult.getThrowable();
        Throwable throwable = throwableOptional.get();

        FailureResult failureResult = FailureResult.of(className, methodName, parameterTypes, this.token, throwable);

        ReflectUtils.newInstance(alarmCallback).postExecutionFalure(failureResult);

    }

}