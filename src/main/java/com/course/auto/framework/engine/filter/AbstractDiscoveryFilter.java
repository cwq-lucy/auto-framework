package com.course.auto.framework.engine.filter;

import com.course.auto.framework.annotation.CaseSelector;
import org.junit.jupiter.engine.descriptor.TestMethodTestDescriptor;
import org.junit.platform.engine.FilterResult;
import org.junit.platform.engine.TestDescriptor;
import org.junit.platform.launcher.PostDiscoveryFilter;


//重写AbstractDiscoveryFilter类  选择完添加过滤器PostDiscoveryFilter
public abstract class AbstractDiscoveryFilter implements PostDiscoveryFilter {


    protected CaseSelector selector;

    public AbstractDiscoveryFilter(CaseSelector selector) {

        this.selector = selector;
    }

    protected abstract boolean preFilter(CaseSelector selector);

    protected abstract FilterResult onApply(TestMethodTestDescriptor descriptor);


    /**
     * @param testDescriptor
     * @return 筛选结果 apply()方法 参数testDescriptor  根据测算文件夹为筛选条件
     * 先判断文件夹再判断文件夹是否包含测试方法   !(testDescriptor instanceof TestMethodTestDescriptor)
     * 如果存在则 return onApply方法
     */

    @Override
    public FilterResult apply(TestDescriptor testDescriptor) {

        if (!preFilter(this.selector)) {
            return FilterResult.includedIf(true);
        }
        if (!(testDescriptor instanceof TestMethodTestDescriptor)) {
            return FilterResult.includedIf(false);
        }

        return onApply((TestMethodTestDescriptor) testDescriptor);
    }


}
