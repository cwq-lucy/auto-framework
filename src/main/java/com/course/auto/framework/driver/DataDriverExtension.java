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

public class DataDriverExtension implements TestTemplateInvocationContextProvider {

    @Override
    public boolean supportsTestTemplate(ExtensionContext extensionContext) {
        // 相当于我们之前写Handler时的 preHandle的前置判断,也就是讲if这种判断与业务代码剥离的设计，跟我们玩的一样
        return extensionContext.getTestMethod()
                .filter(method -> AnnotationSupport.isAnnotated(method, DataDriver.class)) // 判断要被执行的方法之上被标识了注解 @DataDriver
                .isPresent();
        // 很粗暴
        // return true;
    }

    @Override
    public Stream<TestTemplateInvocationContext> provideTestTemplateInvocationContexts(ExtensionContext context) {
        // 1.在这里要去找到DataDriver所指定的path路径下的文件，并解析出来
        // 2.解析出来的数据是List<T>
        // 3.那么我们要做的事就是将 T-> new DataDriverInvocationContext()
        // 4.最后将所有List中的T都转成了DataDriverInvocationContext之后，整体装进Stream做返回
        Method testMethod = context.getRequiredTestMethod();
        DataDriver driver = testMethod.getAnnotation(DataDriver.class);

        /**
         * - name: zhangsan1111
         *   id: 123
         *   age: 43
         *
         *   这是一个DataEntity,也就是我们要发起测试的一组数据。注意这里都是跟我们的方法的参数名对应的。
         */
        List<DataEntity> dataEntities = YmlUtils.read(driver.path());
        return dataEntities.stream().map(DataDriverInvocationContext::new);
    }

    // 是一个testTemplate(测试模板)，但是要包含着我们的测试数据。
    static class DataDriverInvocationContext implements TestTemplateInvocationContext, ParameterResolver {

        private DataEntity dataEntity;

        // 在构造一个测试模板时，把测试数据 带过来。
        public DataDriverInvocationContext(DataEntity dataEntity) {

            this.dataEntity = dataEntity;
        }

        @Override
        public boolean supportsParameter(ParameterContext parameterContext, ExtensionContext extensionContext) throws ParameterResolutionException {
            return extensionContext.getTestMethod()
                    .filter(method -> AnnotationSupport.isAnnotated(method, DataDriver.class)) // 判断要被执行的方法之上被标识了注解 @DataDriver
                    .isPresent();

            // 很粗暴
            // return true;
        }

        // 将name: zhangsan1111（yml） -> String name = "zhangsan1111";
        @Override
        public Object resolveParameter(ParameterContext parameterContext, ExtensionContext extensionContext) throws ParameterResolutionException {
            // 在这里完成每一个数据到参数的转化，比如从资源 文件中拿到的数据是 key=name, value=zhangsan..
            // 那么我们要做的事就是 找 参数名是 name的，然后返回zhangsan.
//            System.out.println("parameterContext ==== " + parameterContext);
            Parameter parameter = parameterContext.getParameter();
            DataParam dataParam = parameter.getAnnotation(DataParam.class);

            // 我们虽然写的是name,但是是在java文件里写的，生成class文件之后，这个参数名就被改为了arg0
            if (this.dataEntity.isKeyExists(dataParam.value())) {
                DataEntity.Entity entity = this.dataEntity.getEntity(dataParam.value());

                // 针对我们自定义的Java Bean时的处理逻辑
                if (entity.isJavaBean()) {
                    return new Gson().fromJson(entity.getVal(), parameter.getType());
                }

                // 要做几种基本数据类型的转化, Integer,Long,Boolean, 如果你是 2020-09-07 12:12:12
                return transForJavaType(entity.getVal(), parameter.getType());
            }

            throw new IllegalStateException("parameter name for " + dataParam.value() + " not exists.");
        }

        private Object transForJavaType(String val, Class<?> type) {
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
                    throw new IllegalStateException("have not support this type = " + type.getName());
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
    }

}
