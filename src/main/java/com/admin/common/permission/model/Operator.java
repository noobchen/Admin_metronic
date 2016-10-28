package com.admin.common.permission.model;

import org.apache.commons.lang3.builder.ToStringBuilder;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: wy
 * Date: 12-9-7
 * Time: 下午2:16
 * To change this template use File | Settings | File Templates.
 */
public class Operator implements Serializable {
    private Integer id;
    private String operatorName;
    private String operatorCompany;
    private String operatorEmail;
    private String operatorPhone;
    private String remark;
    private String account;
    private String password;
    private String accountType;//账号角色， 0为管理员，1为cp,2为sp，3为渠道
    private Integer accountTypeId;
    private String status;
    private List<Role> roles;

    private Timestamp createTime;
    private Timestamp updateTime;


    public String getOperatorName() {
        return operatorName;
    }

    public void setOperatorName(String operatorName) {
        this.operatorName = operatorName;
    }

    public String getOperatorCompany() {
        return operatorCompany;
    }

    public void setOperatorCompany(String operatorCompany) {
        this.operatorCompany = operatorCompany;
    }

    public String getOperatorEmail() {
        return operatorEmail;
    }

    public void setOperatorEmail(String operatorEmail) {
        this.operatorEmail = operatorEmail;
    }

    public String getOperatorPhone() {
        return operatorPhone;
    }

    public void setOperatorPhone(String operatorPhone) {
        this.operatorPhone = operatorPhone;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    public Integer getAccountTypeId() {
        return accountTypeId;
    }

    public void setAccountTypeId(Integer accountTypeId) {
        this.accountTypeId = accountTypeId;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).
                append("id", id).
                append("operatorName", operatorName).
                append("operatorCompany", operatorCompany).
                append("operatorEmail", operatorEmail).
                append("operatorPhone", operatorPhone).
                append("remark", remark).
                append("account", account).
                append("password", password).
                append("accountType", accountType).
                append("accountTypeId", accountTypeId).
                append("status", status).
                append("roles", roles).
                append("createTime", createTime).
                append("updateTime", updateTime).
                toString();
    }
}
