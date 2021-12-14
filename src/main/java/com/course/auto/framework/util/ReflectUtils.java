package com.course.auto.framework.util;

import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.extension.ParameterResolver;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Optional;

public class ReflectUtils {

    public static <T> T newInstance(Class<T> clazz) {
        try {
            return clazz.newInstance();

        } catch (Exception e) {
            throw new IllegalStateException(e);
        }

    }


    //如果方法名相同，但是参数列表不一致时这里的获取，
    public static Method getMethod(String className, String methodName) {
        RequiredUtils.requireNotNullOrEmpty(className, "class name should not be null or empty.");
        RequiredUtils.requireNotNullOrEmpty(methodName, "method name should not be null or empty.");
        try {
            Class<?> clazz = Class.forName(className);
            Method[] methods = clazz.getDeclaredMethods();
            Optional<Method> optionalMethod = Arrays.stream(methods)
                    .filter(method -> method.getName().equals(methodName))
                    .peek(method -> method.setAccessible(true))
                    .findFirst();
            return optionalMethod.orElse(null);
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
    }


}
