package com.course.auto.framework.format.observer;

import com.course.auto.framework.format.FormatObserver;
import org.assertj.core.util.Lists;

import java.lang.reflect.Method;
import java.util.List;

public enum FormatManager {
    INSTANCE;

    private final List<FormatObserver> observers;

    //case管理器
    FormatManager() {
        this.observers = Lists.newArrayList(
                new CaseTagFormatObserver(),
                new CheckPointFormatObserver(),
                new DingTalkFormatObserver(),
                new CaseDescFormatObserver(),
                new CaseTitleFormatObserver(),
                new CaseGroupFormatObserver());
    }

    public void doFormatCheck(Method testMethod) {
        for (FormatObserver observer : this.observers) {
            observer.format(testMethod);
        }
        //System.out.println("testMethod = " + testMethod);
    }

}