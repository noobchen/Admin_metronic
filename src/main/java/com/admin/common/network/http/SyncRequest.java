package com.admin.common.network.http;

/**
 * Created by cyc on 2016/11/7.
 */
public interface SyncRequest {

    HttpResponse get(HttpRequest httpRequest);

    HttpResponse post(HttpRequest httpRequest);
}
