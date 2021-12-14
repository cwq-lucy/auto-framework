package com.course.auto.framework.engine.filter;

import com.course.auto.framework.annotation.CaseSelector;
import com.course.auto.framework.annotation.CaseTag;
import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.engine.descriptor.TestMethodTestDescriptor;
import org.junit.platform.engine.FilterResult;

import java.lang.reflect.Method;
import java.util.Arrays;


//用例标签选择后过滤   继承  提取选择后过滤的标签
public class CaseTagDiscoveryFilter extends AbstractDiscoveryFilter {
    public CaseTagDiscoveryFilter(CaseSelector selector) {
        super(selector);
    }

    //前置过滤器
    @Override
    protected boolean preFilter(CaseSelector selector) {
        //判断是否默认为空 key&&val   return true
        return StringUtils.isNoneBlank(selector.key()) && StringUtils.isNoneBlank(selector.val());
    }

    @Override
    protected FilterResult onApply(TestMethodTestDescriptor descriptor) {
        //testMethod 方法为test5()
        Method testMethod = descriptor.getTestMethod();

        //通过反射获取所有的caseTag
        CaseTag[] tags = testMethod.getAnnotationsByType(CaseTag.class);
        //判断tag selector的key和val是否相等的  累计次数
        long selectedTagCount = Arrays.stream(tags)
                .filter(tag -> tag.key().equals(selector.key()) &&
                        tag.val().equals(selector.val()))
                .count();

        return selectedTagCount > 0 ? FilterResult.includedIf(true) : FilterResult.includedIf(false);

    }


}
