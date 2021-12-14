package com.course.auto.framework.cases.alarm;

import com.course.auto.framework.annotation.*;
import org.junit.jupiter.api.Assertions;

public class TestAlarm {

    @AutoTest
    @CaseDesc(desc = "测试报警流程，本程序会出错导致报警触发", owner = "张三")
    @CaseTitle("报警能力测试")
    @CheckPoint("判断是否除0")
    @CheckPoint("检查报警是否被触发")
    @CheckPoint("检查是否请求成功")
    @CaseTag(key = "xxx", val = "yyy")
    public void testNormal() {
        System.out.println("TestAlarm.testNormal");
        int i = 1 / 0;
        Assertions.assertEquals(1, 0);
    }

    @AutoTest
    @CaseDesc(desc = "测试报警流程，本程序会出错导致报警触发", owner = "李四")
    @CaseTitle("报警能力测试")
    @CheckPoint("判断是否除0")
    @CheckPoint("检查报警是否被触发")
    @CheckPoint("检查是否请求成功")
    @CaseTag(key = "xxx", val = "yyy")
    public void test1() {
        System.out.println("TestAlarm.testNormal");
//        int i = 1/0;
//        Assertions.assertEquals(1,0);
    }

    @AutoTest
    @CaseDesc(desc = "测试报警流程，本程序会出错导致报警触发", owner = "王五")
    @CaseTitle("报警能力测试")
    @CheckPoint("判断是否除0")
    @CheckPoint("检查报警是否被触发")
    @CheckPoint("检查是否请求成功")
    @CaseTag(key = "xxx", val = "yyy")
    public void test2() {
        System.out.println("TestAlarm.testNormal");
//        int i = 1/0;
        Assertions.assertEquals(1, 0);
    }
}
