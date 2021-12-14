package com.course.auto.framework.http;

import com.course.auto.framework.http.service.HttpService;

import java.util.Map;

public class HttpFacade {
    public HttpFacade() {
    }

    public String doPostForm(String url, Map<String, String> headers, Map<String, Object> params) {
        return null;
    }

    public static String doPostJson(String url, Object data) {
        return doPostJson(url, null, data);
    }

    public static String doPostJson(String url, Map<String, String> headers, Object data) {
        return new HttpService().doPostJson(url, headers, data);
    }
}
