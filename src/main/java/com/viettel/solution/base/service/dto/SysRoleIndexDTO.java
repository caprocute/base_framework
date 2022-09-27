package com.viettel.solution.base.service.dto;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;
import javax.validation.constraints.*;

/**
 * A DTO for the {@link com.viettel.solution.base.domain.SysRoleIndex} entity.
 */
@SuppressWarnings("common-java:DuplicatedBlocks")
public class SysRoleIndexDTO implements Serializable {

    @NotNull
    private String id;

    private String roleCode;

    private String url;

    private String component;

    private Boolean isRoute;

    private Integer priority;

    private Integer status;

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

    public String getRoleCode() {
        return roleCode;
    }

    public void setRoleCode(String roleCode) {
        this.roleCode = roleCode;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getComponent() {
        return component;
    }

    public void setComponent(String component) {
        this.component = component;
    }

    public Boolean getIsRoute() {
        return isRoute;
    }

    public void setIsRoute(Boolean isRoute) {
        this.isRoute = isRoute;
    }

    public Integer getPriority() {
        return priority;
    }

    public void setPriority(Integer priority) {
        this.priority = priority;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
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
        if (!(o instanceof SysRoleIndexDTO)) {
            return false;
        }

        SysRoleIndexDTO sysRoleIndexDTO = (SysRoleIndexDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, sysRoleIndexDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "SysRoleIndexDTO{" +
            "id='" + getId() + "'" +
            ", roleCode='" + getRoleCode() + "'" +
            ", url='" + getUrl() + "'" +
            ", component='" + getComponent() + "'" +
            ", isRoute='" + getIsRoute() + "'" +
            ", priority=" + getPriority() +
            ", status=" + getStatus() +
            ", createBy='" + getCreateBy() + "'" +
            ", createTime='" + getCreateTime() + "'" +
            ", updateBy='" + getUpdateBy() + "'" +
            ", updateTime='" + getUpdateTime() + "'" +
            ", tenantId='" + getTenantId() + "'" +
            "}";
    }
}
