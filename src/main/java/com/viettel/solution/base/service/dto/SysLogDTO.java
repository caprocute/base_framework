package com.viettel.solution.base.service.dto;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;
import javax.validation.constraints.*;

/**
 * A DTO for the {@link com.viettel.solution.base.domain.SysLog} entity.
 */
@SuppressWarnings("common-java:DuplicatedBlocks")
public class SysLogDTO implements Serializable {

    @NotNull
    private String id;

    private Integer logType;

    private String logContent;

    private String operateType;

    private String userName;

    private String ip;

    private String method;

    private String requestUrl;

    private String requestParam;

    private String requestType;

    private Long costTime;

    private String createBy;

    private LocalDate createTime;

    private String updateBy;

    private LocalDate updateTime;

    private String tenantId;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getLogType() {
        return logType;
    }

    public void setLogType(Integer logType) {
        this.logType = logType;
    }

    public String getLogContent() {
        return logContent;
    }

    public void setLogContent(String logContent) {
        this.logContent = logContent;
    }

    public String getOperateType() {
        return operateType;
    }

    public void setOperateType(String operateType) {
        this.operateType = operateType;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getRequestUrl() {
        return requestUrl;
    }

    public void setRequestUrl(String requestUrl) {
        this.requestUrl = requestUrl;
    }

    public String getRequestParam() {
        return requestParam;
    }

    public void setRequestParam(String requestParam) {
        this.requestParam = requestParam;
    }

    public String getRequestType() {
        return requestType;
    }

    public void setRequestType(String requestType) {
        this.requestType = requestType;
    }

    public Long getCostTime() {
        return costTime;
    }

    public void setCostTime(Long costTime) {
        this.costTime = costTime;
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    public LocalDate getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDate createTime) {
        this.createTime = createTime;
    }

    public String getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy;
    }

    public LocalDate getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(LocalDate updateTime) {
        this.updateTime = updateTime;
    }

    public String getTenantId() {
        return tenantId;
    }

    public void setTenantId(String tenantId) {
        this.tenantId = tenantId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof SysLogDTO)) {
            return false;
        }

        SysLogDTO sysLogDTO = (SysLogDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, sysLogDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "SysLogDTO{" +
            "id='" + getId() + "'" +
            ", logType=" + getLogType() +
            ", logContent='" + getLogContent() + "'" +
            ", operateType='" + getOperateType() + "'" +
            ", userName='" + getUserName() + "'" +
            ", ip='" + getIp() + "'" +
            ", method='" + getMethod() + "'" +
            ", requestUrl='" + getRequestUrl() + "'" +
            ", requestParam='" + getRequestParam() + "'" +
            ", requestType='" + getRequestType() + "'" +
            ", costTime=" + getCostTime() +
            ", createBy='" + getCreateBy() + "'" +
            ", createTime='" + getCreateTime() + "'" +
            ", updateBy='" + getUpdateBy() + "'" +
            ", updateTime='" + getUpdateTime() + "'" +
            ", tenantId='" + getTenantId() + "'" +
            "}";
    }
}
