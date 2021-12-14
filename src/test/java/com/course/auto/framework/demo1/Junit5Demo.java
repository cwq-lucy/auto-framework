package com.course.auto.framework.demo1;

import org.junit.jupiter.api.*;

public class Junit5Demo {

    @BeforeAll
    public static void beforeAll() {
        System.out.println("Junit5Demo.beforeAll");
    }

    @BeforeEach
    public void beforeTest() {
        System.out.println("Junit5Demo.beforeTest");
    }

    @Test
    public void test1() {
        System.out.println("Junit5Demo.test1");
    }

    @Test
    public void test2() {
        System.out.println("Junit5Demo.test2");
    }

    @Test
    public void test3() {
        System.out.println("Junit5Demo.test3");
    }

    @AfterEach
    public void afterTest() {
        System.out.println("Junit5Demo.afterTest");
    }

    @AfterAll
    public static void afterAll() {
        System.out.println("Junit5Demo.afterAll");
    }
}