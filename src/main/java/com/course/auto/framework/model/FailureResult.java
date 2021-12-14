package com.course.auto.framework.model;

public class FailureResult {

    private String className;
    private String methodName;
    private String parameterTypes;
    private Throwable throwable;
    private String token;

    public static FailureResult of(String className, String methodName, String parameterTypes, String token, Throwable throwable) {
        FailureResult result = new FailureResult();
        result.setClassName(className);
        result.setMethodName(methodName);
        result.setParameterTypes(parameterTypes);
        result.setThrowable(throwable);
        result.setToken(token);
        return result;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    public String getParameterTypes() {
        return parameterTypes;
    }


    public void setParameterTypes(String parameterTypes) {
        this.parameterTypes = parameterTypes;
    }

    public Throwable getThrowable() {
        return throwable;
    }

    public void setThrowable(Throwable throwable) {
        this.throwable = throwable;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    @Override
    public String toString() {
        return "FailureResult{" +
                "className='" + className + '\'' +
                ", methodName='" + methodName + '\'' +
                ", parameterTypes='" + parameterTypes + '\'' +
                ", throwable=" + throwable +
                ", token='" + token + '\'' +
                '}';
    }
}
