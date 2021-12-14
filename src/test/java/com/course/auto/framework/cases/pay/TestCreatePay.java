package com.course.auto.framework.cases.pay;

import com.course.auto.framework.annotation.*;
import org.junit.jupiter.api.Assertions;

public class TestCreatePay {
    @AutoTest
    @CaseTitle("1111")
    @CaseDesc(desc = "1111", owner = "1111")
    @CheckPoint("1111")
    @CaseTag(key = "project", val = "meituan")
    @CaseTag(key = "module", val = "pay")
    @CaseTag(key = "level", val = "redline")

    public void test1() {
        System.out.println("TestCreatePay.test1");
    }

    @AutoTest
    @CaseTitle("2222")
    @CaseDesc(desc = "2222", owner = "2222")
    @CheckPoint("2222")
    @CaseTag(key = "project", val = "toutiao")
    @CaseTag(key = "module", val = "pay")
    @CaseTag(key = "level", val = "normal")

    public void test2(String name) {
        int i = 0 / 2;
        Assertions.assertEquals("code", "1");
        System.out.println("TestCreatePay.test2");
    }

    @AutoTest
    @CaseTitle("3333")
    @CaseDesc(desc = "3333", owner = "3333")
    @CheckPoint("3333")
    @CaseTag(key = "project", val = "toutiao")
    @CaseTag(key = "module", val = "pay")
    @CaseTag(key = "level", val = "redline")

    public void test3() {
        System.out.println("TestCreatePay.test3");
    }
}
