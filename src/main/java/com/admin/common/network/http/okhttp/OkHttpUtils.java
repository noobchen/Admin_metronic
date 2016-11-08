package com.admin.common.network.http.okhttp;

import com.admin.common.network.http.*;
import okhttp3.*;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by cyc on 2016/11/7.
 */
public class OkHttpUtils {

    public static RequestBody getRequestBody(HttpRequest httpRequest) {
        if (httpRequest instanceof HttpFormRequest) {
            FormBody.Builder builder = new FormBody.Builder();
            Map<String, String> formMap = ((HttpFormRequest) httpRequest).getFormMap();

            for (Map.Entry<String, String> entry : formMap.entrySet()) {
                builder.add(entry.getKey(), entry.getValue());
            }

            return builder.build();
        } else {

            if (httpRequest instanceof HttpJSONRequest) {
                MediaType mediaType = MediaType.parse("application/json; charset=utf-8");

                return RequestBody.create(mediaType, ((HttpJSONRequest) httpRequest).getJson());
            }

            if (httpRequest instanceof HttpXmlRequest) {
                MediaType mediaType = MediaType.parse("text/xml; charset=utf-8");

                return RequestBody.create(mediaType, ((HttpXmlRequest) httpRequest).getXml());
            }
        }

        return null;
    }

    public static HttpResponse getHttpResponse(Response response) throws IOException {
        HttpResponse mHttpResponse = new HttpResponse();

        boolean successful = response.isSuccessful();
        mHttpResponse.setSuccessful(successful);

        if (successful) {
            Map<String, String> mResHeaders = new HashMap<String, String>();

            Headers responseHeaders = response.headers();
            for (int i = 0; i < responseHeaders.size(); i++) {
                mResHeaders.put(responseHeaders.name(i), responseHeaders.value(i));
            }
            mHttpResponse.setHeaders(mResHeaders);
            ResponseBody body = response.body();
            mHttpResponse.setInputStream(body.byteStream());
            mHttpResponse.setRespStr(body.string());

        }
        return mHttpResponse;
    }
}
