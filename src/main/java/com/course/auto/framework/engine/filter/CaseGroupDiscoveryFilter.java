package com.course.auto.framework.engine.filter;


import com.course.auto.framework.annotation.CaseGroup;
import com.course.auto.framework.annotation.CaseSelector;
import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.engine.descriptor.TestMethodTestDescriptor;
import org.junit.platform.engine.FilterResult;

import java.lang.reflect.Method;


public class CaseGroupDiscoveryFilter extends AbstractDiscoveryFilter {

    //构造器 CaseGroupDiscoveryFilter
    public CaseGroupDiscoveryFilter(CaseSelector selector) {
        super(selector);
    }

    //prefile  前置过滤器  team和group 通过StringUtils工具类判断不能为空
    @Override
    protected boolean preFilter(CaseSelector selector) {
        return StringUtils.isNotBlank(selector.team()) && StringUtils.isNotBlank(selector.group());
    }

    /**
     * onapply()方法 参数descriptor   通过desscriptor.getTestMethod()获取方法
     */

    @Override
    protected FilterResult onApply(TestMethodTestDescriptor descriptor) {

        Method testMethod = descriptor.getTestMethod();

        boolean isCaseGroupSet = testMethod.isAnnotationPresent(CaseGroup.class);


        //判断!isCaseGroupSet   casegroup不存在则返回false
        if (!isCaseGroupSet) {
            return FilterResult.includedIf(false);
        }

        CaseGroup casegroup = testMethod.getAnnotation(CaseGroup.class);

        //判断选择的文件下的方法 中team() 和 group（） 方法与   设定的team（）和group（）规则是否一致
        if (selector.team().equals(casegroup.team()) && selector.group().equals(casegroup.group())) {
            return FilterResult.includedIf(true);
        }
        return FilterResult.includedIf(false);
    }

}
