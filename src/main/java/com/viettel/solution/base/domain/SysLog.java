package com.viettel.solution.base.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import java.time.LocalDate;
import javax.persistence.*;
import javax.validation.constraints.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.springframework.data.domain.Persistable;

/**
 * A SysLog.
 */
@JsonIgnoreProperties(value = { "new" })
@Entity
@Table(name = "sys_log")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@SuppressWarnings("common-java:DuplicatedBlocks")
public class SysLog implements Serializable, Persistable<String> {

    private static final long serialVersionUID = 1L;

    @NotNull
    @Id
    @Column(name = "id", nullable = false)
    private String id;

    @Column(name = "log_type")
    private Integer logType;

    @Column(name = "log_content")
    private String logContent;

    @Column(name = "operate_type")
    private String operateType;

    @Column(name = "user_name")
    private String userName;

    @Column(name = "ip")
    private String ip;

    @Column(name = "method")
    private String method;

    @Column(name = "request_url")
    private String requestUrl;

    @Column(name = "request_param")
    private String requestParam;

    @Column(name = "request_type")
    private String requestType;

    @Column(name = "cost_time")
    private Long costTime;

    @Column(name = "create_by")
    private String createBy;

    @Column(name = "create_time")
    private LocalDate createTime;

    @Column(name = "update_by")
    private String updateBy;

    @Column(name = "update_time")
    private LocalDate updateTime;

    @Column(name = "tenant_id")
    private String tenantId;

    @Transient
    private boolean isPersisted;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public String getId() {
        return this.id;
    }

    public SysLog id(String id) {
        this.setId(id);
        return this;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getLogType() {
        return this.logType;
    }

    public SysLog logType(Integer logType) {
        this.setLogType(logType);
        return this;
    }

    public void setLogType(Integer logType) {
        this.logType = logType;
    }

    public String getLogContent() {
        return this.logContent;
    }

    public SysLog logContent(String logContent) {
        this.setLogContent(logContent);
        return this;
    }

    public void setLogContent(String logContent) {
        this.logContent = logContent;
    }

    public String getOperateType() {
        return this.operateType;
    }

    public SysLog operateType(String operateType) {
        this.setOperateType(operateType);
        return this;
    }

    public void setOperateType(String operateType) {
        this.operateType = operateType;
    }

    public String getUserName() {
        return this.userName;
    }

    public SysLog userName(String userName) {
        this.setUserName(userName);
        return this;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getIp() {
        return this.ip;
    }

    public SysLog ip(String ip) {
        this.setIp(ip);
        return this;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getMethod() {
        return this.method;
    }

    public SysLog method(String method) {
        this.setMethod(method);
        return this;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getRequestUrl() {
        return this.requestUrl;
    }

    public SysLog requestUrl(String requestUrl) {
        this.setRequestUrl(requestUrl);
        return this;
    }

    public void setRequestUrl(String requestUrl) {
        this.requestUrl = requestUrl;
    }

    public String getRequestParam() {
        return this.requestParam;
    }

    public SysLog requestParam(String requestParam) {
        this.setRequestParam(requestParam);
        return this;
    }

    public void setRequestParam(String requestParam) {
        this.requestParam = requestParam;
    }

    public String getRequestType() {
        return this.requestType;
    }

    public SysLog requestType(String requestType) {
        this.setRequestType(requestType);
        return this;
    }

    public void setRequestType(String requestType) {
        this.requestType = requestType;
    }

    public Long getCostTime() {
        return this.costTime;
    }

    public SysLog costTime(Long costTime) {
        this.setCostTime(costTime);
        return this;
    }

    public void setCostTime(Long costTime) {
        this.costTime = costTime;
    }

    public String getCreateBy() {
        return this.createBy;
    }

    public SysLog createBy(String createBy) {
        this.setCreateBy(createBy);
        return this;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    public LocalDate getCreateTime() {
        return this.createTime;
    }

    public SysLog createTime(LocalDate createTime) {
        this.setCreateTime(createTime);
        return this;
    }

    public void setCreateTime(LocalDate createTime) {
        this.createTime = createTime;
    }

    public String getUpdateBy() {
        return this.updateBy;
    }

    public SysLog updateBy(String updateBy) {
        this.setUpdateBy(updateBy);
        return this;
    }

    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy;
    }

    public LocalDate getUpdateTime() {
        return this.updateTime;
    }

    public SysLog updateTime(LocalDate updateTime) {
        this.setUpdateTime(updateTime);
        return this;
    }

    public void setUpdateTime(LocalDate updateTime) {
        this.updateTime = updateTime;
    }

    public String getTenantId() {
        return this.tenantId;
    }

    public SysLog tenantId(String tenantId) {
        this.setTenantId(tenantId);
        return this;
    }

    public void setTenantId(String tenantId) {
        this.tenantId = tenantId;
    }

    @Transient
    @Override
    public boolean isNew() {
        return !this.isPersisted;
    }

    public SysLog setIsPersisted() {
        this.isPersisted = true;
        return this;
    }

    @PostLoad
    @PostPersist
    public void updateEntityState() {
        this.setIsPersisted();
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof SysLog)) {
            return false;
        }
        return id != null && id.equals(((SysLog) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "SysLog{" +
            "id=" + getId() +
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
