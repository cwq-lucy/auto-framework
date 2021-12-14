package com.course.auto.framework.cases.order;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class App {
    public static void main(String[] args) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        Class clazz = Student.class;
//        Method[] methods = clazz.getDeclaredMethods();
//
//        for(Method  method  : methods){
//            System.out.println(method);
//        }
//
//        Constructor constructor = clazz.getConstructor(String.class,int.class,String.class);
//        Object obj = constructor.newInstance("小明",12,"男");
//        System.out.println("obj = " + obj);

        Method method = clazz.getDeclaredMethod("eat", String.class);

        method.setAccessible(true);

        method.invoke(clazz.newInstance(), "apple");

    }
}
