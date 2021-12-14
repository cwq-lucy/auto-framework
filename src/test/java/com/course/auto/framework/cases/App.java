package com.course.auto.framework.cases;

import com.alibaba.fastjson.JSONObject;
import org.junit.jupiter.api.Test;

import java.text.SimpleDateFormat;
import java.util.Date;

public class App {
    //转换时间格式Dome
    @Test
    public void test() {
        long timeStamp = System.currentTimeMillis();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String sd = sdf.format(Long.parseLong(String.valueOf(timeStamp)));
        String sd2 = sdf.format(new Date(Long.parseLong(String.valueOf(timeStamp))));

        System.out.println("sd = " + sd2);

        System.out.println("timeStamp = " + timeStamp);
    }


    /**
     * json提取key value值
     */
    @Test
    public void jsonToJAVA() {
        System.out.println("json字符串转java代码");
        String jsonStr = "{\"password\":\"\",\"username\":\"张三\"}";
        JSONObject jsonObj = JSONObject.parseObject(jsonStr);
        String username = jsonObj.getString("username");
        String password = jsonObj.toJSONString("password");
        System.out.println("json--->java\n username=" + username
                + "\t password=" + password);
    }

}
