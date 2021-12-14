package com.course.auto.framework.demo1;


import com.course.auto.framework.alarm.callback.DefaultAlarmCallback;
import com.course.auto.framework.annotation.*;
import org.junit.jupiter.api.*;

public class TestDemo {

    /**
     * junit5 原生为我们提供的一些基础能力，以注解的形式进行扩展和使用
     */
    @Test
    @DisplayName("xxxxxxx")
    @Tag("normal")
    @Tag("P0")
    @Tag("P0")
    @Tag("pay")
    @Tag("order")
    @Tag("pre-online")
    @Tag("red-line")
    @Timeout(1000)
    @RepeatedTest(3)
    public void test111() {
        System.out.println("TestDemo.testNormal");
        int i = 1 / 0;
    }

    //        @Test
    @AutoTest
    @CaseTitle("说清楚这个case是干啥的") // 首先给case加标题便于后续查找和区分，必填
    @CaseDesc(desc = "术语哪个需求，", owner = "zhangsan")
    @DingTalkAlarm(token = "", callback = DefaultAlarmCallback.class)
    @CheckPoint("这是第一个检查点，表示这条case的测试重点之1") // 为case添加检查点，必填
    @CheckPoint("这是第一个检查点，表示这条case的测试重点之2")
    @CaseTag(key = "project", val = "meituan") // 为case添加标签方便后续运行时做筛选，必填
    @CaseTag(key = "module", val = "pay")
    @CaseTag(key = "level", val = "normal")
    public void test222() {
        // 预期的case编写样式.
        // 1.要扩展出自己的标识
        // TODO 2.扩展出去之后要用于做什么？给到平台，做case管理，做case评审时使用
        // 3.有必填项，也有非必填，那必填项该如何控制其必填？ 不管是否为必填项，必要的参数校验也是需要处理的
    }

    @AutoTest
    @CaseTitle("说清楚这个case是干啥的") // 首先给case加标题便于后续查找和区分，必填
    @CaseDesc(desc = "术语哪个需求，", owner = "zhangsan")
    @DingTalkAlarm(token = "", callback = DefaultAlarmCallback.class)
//    @DingTalkAlarm(token = "")
    @CheckPoint("这是第一个检查点，表示这条case的测试重点之1") // 为case添加检查点，必填
    @CheckPoint("这是第一个检查点，表示这条case的测试重点之2")
//    @CaseTag(key = "", val = "meituan") // 为case添加标签方便后续运行时做筛选，必填
//    @CaseTag(key = "xxx", val = "") // 为case添加标签方便后续运行时做筛选，必填
    @CaseTag(key = "project", val = "meituan") // 为case添加标签方便后续运行时做筛选，必填
    @CaseTag(key = "module", val = "pay")
//    @CaseTag(key = "", val = "xxx")
    public void test333() {
        // 3.有必填项，也有非必填，那必填项该如何控制其必填？ 不管是否为必填项，必要的参数校验也是需要处理的

        // IllegalFormatException

        System.out.println("TestDemo.test333 ========================= ");
    }
}