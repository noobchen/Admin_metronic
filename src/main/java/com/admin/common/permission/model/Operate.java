package com.admin.common.permission.model;

import org.apache.commons.lang3.builder.ToStringBuilder;

import java.security.Timestamp;

/**
 * Created with IntelliJ IDEA.
 * User: wy
 * Date: 12-9-7
 * Time: 下午3:41
 * To change this template use File | Settings | File Templates.
 */
public class Operate {
    private Integer id;
    private Integer resourceId;
    private OperateType operateType;
    private Timestamp createTime;
    private Timestamp updateTime;

    public Integer getResourceId() {
        return resourceId;
    }

    public void setResourceId(Integer resourceId) {
        this.resourceId = resourceId;
    }

    public OperateType getOperateType() {
        return operateType;
    }

    public void setOperateType(OperateType operateType) {
        this.operateType = operateType;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    @Override
    public String toString() {
        return new ToStringBuilder(this).
                append("id", id).
                append("resourceId", resourceId).
                append("operateType", operateType).
                append("createTime", createTime).
                append("updateTime", updateTime).
                toString();
    }
}
