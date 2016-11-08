package com.admin.common.network.http;

/**
 * Created by cyc on 2016/11/7.
 */
public interface AsyncRequest {
    void get(HttpRequest httpRequest, AsyncCallback callback);

    void post(HttpRequest httpRequest, AsyncCallback callback);
}
