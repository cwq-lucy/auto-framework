package com.course.auto.framework.format.observer;

import com.course.auto.framework.annotation.CaseGroup;
import com.course.auto.framework.format.FormatObserver;
import com.course.auto.framework.util.RequiredUtils;

import java.lang.reflect.Method;

public class CaseGroupFormatObserver implements FormatObserver {

    @Override
    public void format(Method testMethod) {

        boolean isCaseGroupSet = testMethod.isAnnotationPresent(CaseGroup.class);
        if (!isCaseGroupSet) {
            return;
        }

        CaseGroup caseGroup = testMethod.getAnnotation(CaseGroup.class);
        RequiredUtils.requireNotNullOrEmpty(caseGroup.team(), "CaseGroup 'team' should not be null or empty");
        RequiredUtils.requireNotNullOrEmpty(caseGroup.group(), "CaseGroup 'group' should not be null or empty");

    }
}
