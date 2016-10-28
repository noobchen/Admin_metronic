package com.admin.common.permission.model;

import com.admin.common.permission.util.UrlEncryption;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: wy
 * Date: 12-9-7
 * Time: 下午2:13
 * To change this template use File | Settings | File Templates.
 */
public class Resource implements Serializable {
    private Integer id;
    private String resourceName;
    private String type;
    private Resource parentResource;
    private String url;
    private String baseUrl;
    private Integer index;
    private List<Operate> operates;

    private List<Resource> subMenus;

    private Timestamp createTime;
    private Timestamp updateTime;

    public List<Resource> getSubMenus() {
        return subMenus;
    }

    public void setSubMenus(List<Resource> subMenus) {
        this.subMenus = subMenus;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getResourceName() {
        return resourceName;
    }

    public void setResourceName(String resourceName) {
        this.resourceName = resourceName;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Resource getParentResource() {
        return parentResource;
    }

    public void setParentResource(Resource parentResource) {
        this.parentResource = parentResource;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public List<Operate> getOperates() {
        return operates;
    }

    public void setOperates(List<Operate> operates) {
        this.operates = operates;
    }

    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    public Timestamp getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Timestamp updateTime) {
        this.updateTime = updateTime;
    }

    public Integer getIndex() {
        return index;
    }

    public void setIndex(Integer index) {
        this.index = index;
    }

    public String getBaseUrl() {
        return baseUrl;
    }

    public void setBaseUrl(String baseUrl) {
        this.baseUrl = baseUrl;
    }


    public String getEncryptionUrlString() {
        if(null == parentResource){
            return null;
        }
        return UrlEncryption.encrypt(parentResource.getBaseUrl() + url);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).
                append("id", id).
                append("resourceName", resourceName).
                append("type", type).
                append("parentResource", parentResource).
                append("url", url).
                append("baseUrl", baseUrl).
                append("index", index).
                append("operates", operates).
                append("createTime", createTime).
                append("updateTime", updateTime).
                toString();
    }
}
