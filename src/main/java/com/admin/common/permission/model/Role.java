package com.admin.common.permission.model;

import org.apache.commons.lang3.builder.ToStringBuilder;

import java.io.Serializable;
import java.sql.Date;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: wy
 * Date: 12-9-7
 * Time: 下午2:14
 * To change this template use File | Settings | File Templates.
 */
public class Role implements Serializable {
    private Integer id;
    private String name;
    private List<Resource> resources;
    private Date createTime;
    private Date updateTime;

    public List<Resource> getResources() {
        return resources;
    }

    public void setResources(List<Resource> resources) {
        this.resources = resources;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).
                append("id", id).
                append("name", name).
                append("resources", resources).
                append("createTime", createTime).
                append("updateTime", updateTime).
                toString();
    }
}
