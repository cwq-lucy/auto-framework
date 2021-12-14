package com.course.auto.framework.driver;


import com.course.auto.framework.annotation.*;
import com.course.auto.framework.driver.model.Order;
import okhttp3.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.*;

import java.io.IOException;
import java.lang.reflect.Parameter;

public class App {

    @AutoTest
    @CheckPoint("xxx")
    @CaseTitle("xxxx")
    @CaseTag(key = "project", val = "meituan")
    @CaseDesc(desc = "wwwww", owner = "qa")
    public void testNormal() {
        String name = null;
        Integer id = null;
        // data.csv
        // name, id
        // jim , 1
        // zhangsan,2
        // xxxx,xxxx
        // 1.解析数据文件文件, data.csv
        // 2.按照csv的列定义做赋值给name,id.
        // 3.循环调用
        String resp = call1(name, id);
    }

    //    @AutoTest
    @CheckPoint("xxx")
    @CaseTitle("xxxx")
    @CaseDesc(desc = "wwwww", owner = "qa")
    @CaseTag(key = "xxx", val = "xxx")
    @DataDriver(path = "data/demo1.yml") // 每一行数据都要来自动的调用一下 本方法并将数据按照参数的名字做赋值
    public void testNew(@DataParam("name") String name, @DataParam("id") Integer id) {
        // 1.去@DataDriver注解中找到path路径下的资源文件
        // 2.解析资源文件，存成为List<T>
        // 3.将List中的每个数据按照方法签名的参数名或类型做赋值。
        // 4.实现循环调用
        String resp = call1(name, id);
        System.out.println("resp = " + resp);
    }

    //  @AutoTest
    @CaseTitle("1111")
    @CaseDesc(desc = "1111", owner = "1111")
    @CheckPoint("1111")
    @CaseTag(key = "project", val = "meituan")
    @CaseTag(key = "module", val = "order")
    @CaseTag(key = "level", val = "redline")
    @DataDriver(path = "data/demo1.yml")
    public void testNew2(@DataParam("url") String url) {
        String URL = url.replace("\"", "");

        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(URL)
                .addHeader("Accept", "*/*")
                .addHeader("vvToken", "c063288bc412b8c30082b0290f30ed62")
                .get()  //默认为GET请求，可以不写
                .build();
        final Call call = client.newCall(request);
        try {
            Response response = call.execute();
            ResponseBody responseBody = response.body();
            System.out.println("responseBody.string() = " + responseBody.string());
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
        // Assertions.assertEquals("青火互联科技","青火互联科技");
    }

    //  @AutoTest
    @CaseTitle("1111")
    @CaseDesc(desc = "1111", owner = "1111")
    @CheckPoint("1111")
    @CaseTag(key = "project", val = "meituan")
    @CaseTag(key = "module", val = "order")
    @CaseTag(key = "level", val = "redline")
    @DataDriver(path = "data/demo1.yml")
    public void testNew22(@DataParam("url") String url, @DataParam("jsonStr") String jsonStr) {
        String URL = url.replace("\"", "");
        MediaType JSON = MediaType.parse("application/json;charset=UTF-8; charset=utf-8");

        RequestBody body = RequestBody.create(JSON, jsonStr);
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(URL)
                .addHeader("Content-Type", "application/json")
                .addHeader("vvToken", "c063288bc412b8c30082b0290f30ed62")
                .post(body)
                .build();

        final Call call = client.newCall(request);
        try {
            Response response = call.execute();
            ResponseBody responseBody = response.body();
            System.out.println("responseBody.string() = " + responseBody.string());
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }


    public String call1(String name, Integer id) {
        // 假设是我们业务要真实去调用的接口或方法
        return name + "-" + id;
    }


    //    @AutoTest
    @CheckPoint("xxx")
    @CaseTitle("xxxx")
    @CaseDesc(desc = "wwwww", owner = "qa")
    @CaseTag(key = "xxx", val = "xxx")
    @DataDriver(path = "data/demo3.yml")
    public void testNew3(@DataParam("name") String name, @DataParam("id") Integer id, @DataParam("age") Integer age) {
        System.out.println(String.format("App.testNew3 name=%s, age=%s, id=%s", name, age, id));
    }

    @AutoTest
    @CheckPoint("xxx")
    @CaseTitle("xxxx")
    @CaseDesc(desc = "wwwww", owner = "qa")
    @CaseTag(key = "xxx", val = "xxx")
    public void normal() {
        System.out.println("App.normal");
    }

    @CheckPoint("xxx")
    @CaseTitle("xxxx")
    @CaseDesc(desc = "wwwww", owner = "qa")
    @CaseTag(key = "xxx", val = "xxx")
    @DataDriver(path = "data/demo2.yml")
    public void testObjectDriver(@DataParam("merchantId") String merchantId, @DataParam("order") Order order) {
        System.out.println("App.testObjectDriver merchantId = " + merchantId + " ,order = " + order);
    }

    @Test
    @ExtendWith(Xxxx.class)
    public void test1111(String name) {

        System.out.println("App.test1111 name=" + name);
    }

    static class Xxxx implements ParameterResolver {

        @Override
        public boolean supportsParameter(ParameterContext parameterContext, ExtensionContext extensionContext) throws ParameterResolutionException {
            return true;
        }

        @Override
        public Object resolveParameter(ParameterContext parameterContext, ExtensionContext extensionContext) throws ParameterResolutionException {
            Parameter parameter = parameterContext.getParameter();
            System.out.println("parameter = " + parameter);
            return "hello";
        }
    }
}
