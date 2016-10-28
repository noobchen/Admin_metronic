package com.admin.common.page;

import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.ArrayList;
import java.util.List;

/**
 * Author: Tony.Wang
 * Date: 12-5-24
 * Time: 下午2:42
 * Description: to write something
 */
public class PageInfo {
    private int pageSize = 8; //每页显示条数
    private int startPageIndex = 0;  //起始行号
    private long totalPageSize = 0; //总页数
    private long totalRow = 0;//总行数
    private List result = new ArrayList(); //结果集

    private String firstPageUrl;
    private String lastPageUrl;
    private String prevPageUrl;
    private String nextPageUrl;

    public String getFirstPageUrl() {
        return firstPageUrl;
    }

    public void setFirstPageUrl(String firstPageUrl) {
        this.firstPageUrl = firstPageUrl;
    }

    public String getLastPageUrl() {
        return lastPageUrl;
    }

    public void setLastPageUrl(String lastPageUrl) {
        this.lastPageUrl = lastPageUrl;
    }

    public String getNextPageUrl() {
        return nextPageUrl;
    }

    public void setNextPageUrl(String nextPageUrl) {
        this.nextPageUrl = nextPageUrl;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public String getPrevPageUrl() {
        return prevPageUrl;
    }

    public void setPrevPageUrl(String prevPageUrl) {
        this.prevPageUrl = prevPageUrl;
    }

    public List getResult() {
        return result;
    }

    public void setResult(List result) {
        this.result = result;
    }

    public int getStartPageIndex() {
        return startPageIndex;
    }

    public void setStartPageIndex(int startPageIndex) {
        this.startPageIndex = startPageIndex;
    }

    public long getTotalPageSize() {
        return totalPageSize;
    }

    public void setTotalPageSize(long totalPageSize) {
        this.totalPageSize = totalPageSize;
    }

    public long getTotalRow() {
        return totalRow;
    }

    public void setTotalRow(long totalRow) {
        this.totalRow = totalRow;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).
                append("firstPageUrl", firstPageUrl).
                append("pageSize", pageSize).
                append("startPageIndex", startPageIndex).
                append("totalPageSize", totalPageSize).
                append("totalRow", totalRow).
                append("result", result).
                append("lastPageUrl", lastPageUrl).
                append("prevPageUrl", prevPageUrl).
                append("nextPageUrl", nextPageUrl).
                toString();
    }
}
