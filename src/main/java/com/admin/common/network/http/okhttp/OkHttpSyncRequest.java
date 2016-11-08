package com.admin.common.network.http.okhttp;

import com.admin.common.network.http.HttpRequest;
import com.admin.common.network.http.HttpResponse;
import com.admin.common.network.http.SyncRequest;
import okhttp3.*;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * Created by cyc on 2016/11/7.
 */
@Service
public class OkHttpSyncRequest implements SyncRequest {

    OkHttpClient client = new OkHttpClient.Builder()
            .connectTimeout(10, TimeUnit.SECONDS)
            .writeTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .build();

    @Override
    public HttpResponse get(HttpRequest httpRequest) {
        String url = httpRequest.getUrl();

        if (StringUtils.isNotEmpty(url)) {
            try {
                Request.Builder requestBuilder = new Request.Builder().url(url);
                Map<String, String> headers = httpRequest.getHeaders();

                if (MapUtils.isNotEmpty(headers)) {
                    for (Map.Entry<String, String> entry : headers.entrySet()) {
                        requestBuilder.addHeader(entry.getKey(), entry.getValue());
                    }
                }

                Request request = requestBuilder.build();

                Response response = client.newCall(request).execute();

                return OkHttpUtils.getHttpResponse(response);
            } catch (IOException e) {
                e.printStackTrace();
                return null;
            }

        }

        return null;
    }

    @Override
    public HttpResponse post(HttpRequest httpRequest) {
        String url = httpRequest.getUrl();

        if (StringUtils.isNotEmpty(url)) {
            try {
                Request.Builder requestBuilder = new Request.Builder().url(url);
                Map<String, String> headers = httpRequest.getHeaders();

                if (MapUtils.isNotEmpty(headers)) {
                    for (Map.Entry<String, String> entry : headers.entrySet()) {
                        requestBuilder.addHeader(entry.getKey(), entry.getValue());
                    }
                }

                RequestBody requestBody = OkHttpUtils.getRequestBody(httpRequest);
                Request request = requestBuilder.post(requestBody).build();

                Response response = client.newCall(request).execute();

                return OkHttpUtils.getHttpResponse(response);
            } catch (IOException e) {
                e.printStackTrace();
                return null;
            }

        }

        return null;
    }



}
