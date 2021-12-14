package com.course.auto.framework.alarm;

import com.course.auto.framework.annotation.DingTalkAlarm;
import com.course.auto.framework.model.FailureResult;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.TestExecutionExceptionHandler;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.stream.Collectors;

public class AlarmExtension implements TestExecutionExceptionHandler {

    @Override
    public void handleTestExecutionException(ExtensionContext extensionContext, Throwable throwable) throws Throwable {

        Method testMethod = extensionContext.getRequiredTestMethod();
        Class<?> testClass = extensionContext.getRequiredTestClass();

        String parameterTypes = Arrays.stream(testMethod.getParameterTypes()).map(Class::getName).collect(Collectors.joining(","));

        DingTalkAlarm talkAlarm = testMethod.getAnnotation(DingTalkAlarm.class);
        FailureResult failureResult = FailureResult.of(testClass.getName(), testMethod.getName(), parameterTypes, talkAlarm.token(), throwable);
        AlarmFacade.doAlarm(failureResult);

    }
}
