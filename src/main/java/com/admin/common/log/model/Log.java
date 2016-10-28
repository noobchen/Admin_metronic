package com.admin.common.log.model;

import org.apache.commons.lang3.builder.ToStringBuilder;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * Created with IntelliJ IDEA.
 * User: wy
 * Date: 12-9-18
 * Time: 上午9:46
 * To change this template use File | Settings | File Templates.
 */
public class Log implements Serializable {
    private Integer id;
    private String operator;
    private String Type;
    private String content;
    private Timestamp createTime;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public String getType() {
        return Type;
    }

    public void setType(String type) {
        Type = type;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).
                append("id", id).
                append("operator", operator).
                append("Type", Type).
                append("content", content).
                append("createTime", createTime).
                toString();
    }
}
