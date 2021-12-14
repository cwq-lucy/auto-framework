package com.course.auto.framework.cases.order;


import com.alibaba.fastjson.JSONObject;
import com.course.auto.framework.annotation.*;
import okhttp3.*;
import org.junit.jupiter.api.Assertions;

import java.io.IOException;
import java.util.UUID;

public class TestCreateOrder {

    UUID uuid = UUID.randomUUID();
    private String code = null;
    final String name = "Content-Type";
    final String value = "application/json";


    @CaseTitle("GetUser")
    @CaseDesc(desc = "GetUser", owner = "GetUser")
    @CheckPoint("GetUser")
    @CaseTag(key = "project", val = "meituan")
    @CaseTag(key = "module", val = "order")
    @CaseTag(key = "level", val = "redline")
    @DataDriver(path = "data\\demo2.yml")
    public void testGetUser(@DataParam("url") String url, @DataParam("vvToken") String vvToken) {
        String URL = url.replace("\"", "");
        String VVToken = vvToken.replace("\"", "");
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(URL)
                .header(name, value)
                .header("vvToken", VVToken)
                .get()
                .build();
        final Call call = client.newCall(request);
        try {
            Response response = call.execute();
            ResponseBody responseBody = response.body();
            String responseData = responseBody.string();
            System.out.println("responseBody.string(testGetUser) = " + responseData);

            //json根据key取出value值
            JSONObject jsonObj = JSONObject.parseObject(responseData);
            code = jsonObj.getString("code");
            // System.out.println("code = " + code);

        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
        Assertions.assertEquals("1", code);

    }


//    //  @AutoTest
//    @CaseTitle("PostFenLei")
//    @CaseDesc(desc = "PostFenLei", owner = "PostFenLei")
//    @CheckPoint("PostFenLei")
//    @CaseTag(key = "project", val = "meituan")
//    @CaseTag(key = "module", val = "order")
//    @CaseTag(key = "level", val = "redline")
//    @DataDriver(path = "data\\demo1.yml")
//    public void testPostFenLei(@DataParam("url") String url,@DataParam("jsonStr") String jsonStr) {
//        String URL = url.replace("\"", "");
//        MediaType JSON = MediaType.parse("application/json;charset=UTF-8; charset=utf-8");
//
//        RequestBody body = RequestBody.create(JSON, jsonStr);
//        OkHttpClient client = new OkHttpClient();
//        Request request = new Request.Builder()
//                .url(URL)
//                .addHeader("Content-Type","application/json")
//                .addHeader("vvToken", "12bb65b49ab84ad41a1b3824d2e3dac9")
//                .post(body)
//                .build();
//
//        final Call call = client.newCall(request);
//        try {
//            Response response = call.execute();
//            ResponseBody responseBody = response.body();
//            System.out.println("responseBody.string() = " + responseBody.string());
//        } catch (IOException e) {
//            throw new IllegalStateException(e);
//        }
//        //Assertions.assertEquals("code","4163");
//
//    }

//    //  @AutoTest
//    @CaseTitle("PostFenLei")
//    @CaseDesc(desc = "PostFenLei", owner = "PostFenLei")
//    @CheckPoint("PostFenLei")
//    @CaseTag(key = "project", val = "meituan")
//    @CaseTag(key = "module", val = "order")
//    @CaseTag(key = "level", val = "redline")
//    @DataDriver(path = "data\\demo1.yml")
//    public void testPostFenLei1(@DataParam("url") String url, @DataParam("vvToken") String vvToken, @DataParam("classCode") String classCode,
//                                @DataParam("classDesc") String classDesc, @DataParam("className") String className) {
//        String URL = url.replace("\"", "");
//        String VVToken = vvToken.replace("\"", "");
//
//        RequestBody requestBody = new MultipartBody.Builder()
//                .setType(MultipartBody.FORM)
//                .addFormDataPart("classCode", classCode +uuid)
//                .addFormDataPart("classDesc", classDesc +uuid)
//                .addFormDataPart("className", className +uuid)
//                .build();
//
//        Request request = new Request.Builder()
//                .header("Content-Type", "application/json")
//                .addHeader("vvToken", VVToken)
//                .url(URL)
//                .post(requestBody)
//                .build();
//
//        try {
//            OkHttpClient client = new OkHttpClient();
//            Response response = client.newCall(request).execute();
//            System.out.println("responseBody.string(testPostFenLei1) = " + response.body().string());
//        } catch (IOException e) {
//            throw new IllegalStateException(e);
//        }
//        Assertions.assertEquals("1","1");
//    }


//    //  @AutoTest
//    @CaseTitle("PostFenLei")
//    @CaseDesc(desc = "PostFenLei", owner = "PostFenLei")
//    @CheckPoint("PostFenLei")
//    @CaseTag(key = "project", val = "meituan")
//    @CaseTag(key = "module", val = "order")
//    @CaseTag(key = "level", val = "redline")
//    @DataDriver(path = "data\\demo3.yml")
//    public void testPostFenLei2(@DataParam("url") String url, @DataParam("vvToken") String vvToken, @DataParam("classCode") String classCode,
//                                @DataParam("classDesc") String classDesc, @DataParam("className") String className) {
//        String URL = url.replace("\"", "");
//        String VVToken = vvToken.replace("\"", "");
//        RequestBody requestBody = new MultipartBody.Builder()
//                .setType(MultipartBody.FORM)
//                .addFormDataPart("classCode", classCode+uuid)
//                .addFormDataPart("classDesc", classDesc+uuid)
//                .addFormDataPart("className", className+uuid)
//                .build();
//
//        Request request = new Request.Builder()
//                .header("Content-Type", "application/json")
//                .addHeader("vvToken", VVToken)
//                .url(URL)
//                .post(requestBody)
//                .build();
//
//        try {
//            OkHttpClient client = new OkHttpClient();
//            Response response = client.newCall(request).execute();
//            System.out.println("responseBody.string(testPostFenLei2) = " + response.body().string());
//        } catch (IOException e) {
//            throw new IllegalStateException(e);
//        }
//        Assertions.assertEquals("1","1");
//    }


//    //  @AutoTest
//    @CaseTitle("PostFenLei")
//    @CaseDesc(desc = "PostFenLei", owner = "PostFenLei")
//    @CheckPoint("PostFenLei")
//    @CaseTag(key = "project", val = "meituan")
//    @CaseTag(key = "module", val = "order")
//    @CaseTag(key = "level", val = "redline")
//    @DataDriver(path = "data\\demo4.yml")
//    public void testPostFenLei3(@DataParam("url") String url, @DataParam("vvToken") String vvToken, @DataParam("classCode") String classCode,
//                                @DataParam("classDesc") String classDesc, @DataParam("className") String className) {
//        String URL = url.replace("\"", "");
//        String VVToken = vvToken.replace("\"", "");
//        RequestBody requestBody = new MultipartBody.Builder()
//                .setType(MultipartBody.FORM)
//                .addFormDataPart("classCode", classCode+uuid)
//                .addFormDataPart("classDesc", classDesc+uuid)
//                .addFormDataPart("className", className+uuid)
//                .build();
//
//        Request request = new Request.Builder()
//                .header("Content-Type", "application/json")
//                .addHeader("vvToken", VVToken)
//                .url(URL)
//                .post(requestBody)
//                .build();
//
//        try {
//            OkHttpClient client = new OkHttpClient();
//            Response response = client.newCall(request).execute();
//            System.out.println("responseBody.string(testPostFenLei3) = " + response.body().string());
//        } catch (IOException e) {
//            throw new IllegalStateException(e);
//        }
//        Assertions.assertEquals("1","1");
//    }

}
