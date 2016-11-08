package com.admin.common.network.http;

import java.io.InputStream;
import java.util.Map;

/**
 * Created by cyc on 2016/11/7.
 */
public class HttpResponse {
    //状态行
    private boolean successful;
    //响应头
    private Map<String, String> headers;
    //响应体
    private InputStream inputStream;
    private String respStr;

    public void setRespStr(String respStr) {
        this.respStr = respStr;
    }

    public Map<String, String> getHeaders() {
        return headers;
    }

    public boolean isSuccessful() {
        return successful;
    }

    public void setSuccessful(boolean successful) {
        this.successful = successful;
    }

    public void setHeaders(Map<String, String> headers) {
        this.headers = headers;
    }

    public InputStream getInputStream() {
        return inputStream;
    }

    public void setInputStream(InputStream inputStream) {
        this.inputStream = inputStream;
    }

    @Override
    public String toString() {
        return respStr;
    }
}
