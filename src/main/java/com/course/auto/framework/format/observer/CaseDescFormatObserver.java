package com.course.auto.framework.format.observer;

import com.course.auto.framework.annotation.CaseDesc;
import com.course.auto.framework.exception.IllegalFormatException;
import com.course.auto.framework.format.FormatObserver;
import com.course.auto.framework.util.RequiredUtils;


import java.lang.reflect.Method;

public class CaseDescFormatObserver implements FormatObserver {

    @Override
    public void format(Method testMethod) {
        boolean isCaseDescSet = testMethod.isAnnotationPresent(CaseDesc.class);
        if (!isCaseDescSet) {
            throw new IllegalFormatException("@CaseDesc should be set, eg: @CaseDesc(desc = \"属于哪个需求，\", owner = \"zhangsan\")");
        }

        CaseDesc ds = testMethod.getAnnotation(CaseDesc.class);
        RequiredUtils.requireNotNullOrEmpty(ds.desc(), "CaseDesc 'desc' should not be null or empty");
        RequiredUtils.requireNotNullOrEmpty(ds.owner(), "CaseDesc 'owner' should not be null or empty");
    }
}