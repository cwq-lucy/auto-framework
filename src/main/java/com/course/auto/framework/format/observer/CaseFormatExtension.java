package com.course.auto.framework.format.observer;

import org.junit.jupiter.api.extension.BeforeTestExecutionCallback;
import org.junit.jupiter.api.extension.ExtensionContext;


/**
 * 用例的格式化检查就在此处理
 */
public class CaseFormatExtension implements BeforeTestExecutionCallback {

    @Override
    public void beforeTestExecution(ExtensionContext context) {
        FormatManager.INSTANCE.doFormatCheck(context.getRequiredTestMethod());
    }
}