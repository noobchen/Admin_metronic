package com.admin.common.page;

import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: wy
 * Date: 12-9-7
 * Time: 下午9:04
 * To change this template use File | Settings | File Templates.
 */
public class PageUtils {
    public static void set(PageInfo pageInfo, String url, Map<String, Object> queryParams) {
        StringBuilder queryUrl = new StringBuilder();
        for (String key : queryParams.keySet()) {
            Object value = queryParams.get(key);

            if (null != value) {
                if(value instanceof PageInfo){
                    continue;//跳过分页信息
                }
                queryUrl.append("&").append(key).append("=").append(value);
            }
        }

        if (pageInfo.getTotalRow() > 0) {
            pageInfo.setFirstPageUrl(url + "?pageIndex=0" + queryUrl);
            pageInfo.setLastPageUrl(url + "?pageIndex=" + (pageInfo.getTotalPageSize() - 1) + queryUrl.toString());
        } else {
            pageInfo.setFirstPageUrl("#");
            pageInfo.setLastPageUrl("#");
        }

        if (pageInfo.getStartPageIndex() != 0) {
            pageInfo.setPrevPageUrl(url + "?pageIndex=" + (pageInfo.getStartPageIndex() - 1) + queryUrl.toString());
        } else {
            pageInfo.setPrevPageUrl("#");
        }

        if (pageInfo.getTotalPageSize() > pageInfo.getStartPageIndex()) {
            if (pageInfo.getTotalPageSize() == (pageInfo.getStartPageIndex() + 1)) {
                pageInfo.setNextPageUrl("#");
            } else {
                pageInfo.setNextPageUrl(url + "?pageIndex=" + (pageInfo.getStartPageIndex() + 1) + queryUrl.toString());
            }
        } else {
            pageInfo.setNextPageUrl("#");
        }
    }
}
