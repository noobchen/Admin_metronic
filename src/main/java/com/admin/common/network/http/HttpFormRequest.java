package com.admin.common.network.http;

import java.util.Map;

/**
 * Created by cyc on 2016/11/7.
 */
public class HttpFormRequest extends HttpRequest {

    private Map<String,String> formMap;

    public Map<String, String> getFormMap() {
        return formMap;
    }

    public void setFormMap(Map<String, String> formMap) {
        this.formMap = formMap;
    }
}
