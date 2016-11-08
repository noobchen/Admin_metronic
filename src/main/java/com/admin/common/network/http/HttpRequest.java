package com.admin.common.network.http;

import java.util.Map;

/**
 * Created by cyc on 2016/11/7.
 */
public class HttpRequest {
    //请求行
    protected String url;
    //请求头
    protected Map<String, String> headers;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Map<String, String> getHeaders() {
        return headers;
    }

    public void setHeaders(Map<String, String> headers) {
        this.headers = headers;
    }
}
