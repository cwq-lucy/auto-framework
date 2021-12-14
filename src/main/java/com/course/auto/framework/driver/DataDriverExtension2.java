package com.course.auto.framework.driver;

import com.course.auto.framework.annotation.DataDriver;
import com.course.auto.framework.annotation.DataParam;
import com.course.auto.framework.model.DataEntity;
import com.course.auto.framework.util.YmlUtils;
import com.google.common.collect.Lists;
import com.google.gson.Gson;

import org.junit.jupiter.api.extension.*;
import org.junit.platform.commons.support.AnnotationSupport;

import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.List;
import java.util.stream.Stream;

/**
 * 补充视频部分
 */
public class DataDriverExtension2 implements TestTemplateInvocationContextProvider {

    @Override
    public boolean supportsTestTemplate(ExtensionContext extensionContext) {
        return extensionContext.getTestMethod()
                .filter(method -> AnnotationSupport.isAnnotated(method, DataDriver.class))
                .isPresent();
    }

    @Override
    public Stream<TestTemplateInvocationContext> provideTestTemplateInvocationContexts(ExtensionContext extensionContext) {
        //通过extensionContext.getRequiredTestMethod();获取testMethod方法
        Method testMethod = extensionContext.getRequiredTestMethod();
        //通过testMethod.getAnnotation(DataDriver.class); 反射获取到dataDriver yam文件的path路径
        DataDriver dataDriver = testMethod.getAnnotation(DataDriver.class);
        List<DataEntity> dataEntities = YmlUtils.read(dataDriver.path());
        return dataEntities.stream().map(DataInvocationContext::new);
    }

    static class DataInvocationContext implements TestTemplateInvocationContext, ParameterResolver {
        private DataEntity dataEntity;

        public DataInvocationContext(DataEntity dataEntity) {
            this.dataEntity = dataEntity;
        }

        /**
         * @param parameterContext
         * @param extensionContext
         * @return
         * @throws ParameterResolutionException
         */
        @Override
        public Object resolveParameter(ParameterContext parameterContext, ExtensionContext extensionContext) throws ParameterResolutionException {
            Parameter parameter = parameterContext.getParameter();
            DataParam dataParam = parameter.getAnnotation(DataParam.class);
            if (dataEntity.isKeyExists(dataParam.value())) {
                // 就是我们需要去做数据设置的
                DataEntity.Entity entity = dataEntity.getEntity(dataParam.value());

                if (entity.isJavaBean()) {
                    return new Gson().fromJson(entity.getVal(), parameter.getType());
                }

                return parseToJavaType(entity.getVal(), parameter.getType());

            }
            throw new IllegalStateException("none type");
        }

        private Object parseToJavaType(String val, Class<?> type) {
            switch (type.getName()) {
                case "java.lang.Integer":
                    return Integer.valueOf(val);
                case "java.lang.Long":
                    return Long.valueOf(val);
                case "java.lang.Boolean":
                    return Boolean.valueOf(val);
                case "java.lang.String":
                    return val;
                default:
                    throw new IllegalArgumentException("have not support type=" + type.getName());
            }
        }

        @Override
        public String getDisplayName(int invocationIndex) {
            return "data driver:" + invocationIndex;
        }

        @Override
        public List<Extension> getAdditionalExtensions() {
            return Lists.newArrayList(this);
        }

        @Override
        public boolean supportsParameter(ParameterContext parameterContext, ExtensionContext extensionContext) throws ParameterResolutionException {
            return extensionContext.getTestMethod()
                    .filter(method -> AnnotationSupport.isAnnotated(method, DataDriver.class))
                    .isPresent();
        }
    }
}
