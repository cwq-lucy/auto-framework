package com.course.auto.framework.cases.account;

import com.course.auto.framework.annotation.*;

public class TestAccount {

    @AutoTest
    @CaseTitle("44444")
    @CaseDesc(desc = "4444", owner = "444")
    @CheckPoint("4444")
    @CaseTag(key = "project", val = "toutiao")
    @CaseGroup(team = "abc", group = "123")
    public void test4() {
        System.out.println("TestAccount.test4()");
    }

}
