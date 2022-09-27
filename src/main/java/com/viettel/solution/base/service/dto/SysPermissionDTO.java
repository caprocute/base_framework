package com.viettel.solution.base.service.dto;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;
import javax.validation.constraints.*;

/**
 * A DTO for the {@link com.viettel.solution.base.domain.SysPermission} entity.
 */
@SuppressWarnings("common-java:DuplicatedBlocks")
public class SysPermissionDTO implements Serializable {

    @NotNull
    private String id;

    private String parentId;

    private String name;

    private String url;

    private String component;

    private Boolean isRoute;

    private String componentName;

    private String redirect;

    private Integer menuType;

    private String perms;

    private String permsType;

    private String sortNo;

    private Boolean alwaysShow;

    private String icon;

    private Boolean isLeaf;

    private Boolean keepAlive;

    private Boolean hidden;

    private Boolean hideTab;

    private String description;

    private Boolean delFlag;

    private Boolean ruleFLag;

    private String createBy;

    private LocalDate createTime;

    private String updateBy;

    private LocalDate updateTime;

    private String tenantId;

    private Boolean internalOrExternal;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public String getComponentName() {
        return componentName;
    }

    public void setComponentName(String componentName) {
        this.componentName = componentName;
    }

    public String getRedirect() {
        return redirect;
    }

    public void setRedirect(String redirect) {
        this.redirect = redirect;
    }

    public Integer getMenuType() {
        return menuType;
    }

    public void setMenuType(Integer menuType) {
        this.menuType = menuType;
    }

    public String getPerms() {
        return perms;
    }

    public void setPerms(String perms) {
        this.perms = perms;
    }

    public String getPermsType() {
        return permsType;
    }

    public void setPermsType(String permsType) {
        this.permsType = permsType;
    }

    public String getSortNo() {
        return sortNo;
    }

    public void setSortNo(String sortNo) {
        this.sortNo = sortNo;
    }

    public Boolean getAlwaysShow() {
        return alwaysShow;
    }

    public void setAlwaysShow(Boolean alwaysShow) {
        this.alwaysShow = alwaysShow;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public Boolean getIsLeaf() {
        return isLeaf;
    }

    public void setIsLeaf(Boolean isLeaf) {
        this.isLeaf = isLeaf;
    }

    public Boolean getKeepAlive() {
        return keepAlive;
    }

    public void setKeepAlive(Boolean keepAlive) {
        this.keepAlive = keepAlive;
    }

    public Boolean getHidden() {
        return hidden;
    }

    public void setHidden(Boolean hidden) {
        this.hidden = hidden;
    }

    public Boolean getHideTab() {
        return hideTab;
    }

    public void setHideTab(Boolean hideTab) {
        this.hideTab = hideTab;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Boolean getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(Boolean delFlag) {
        this.delFlag = delFlag;
    }

    public Boolean getRuleFLag() {
        return ruleFLag;
    }

    public void setRuleFLag(Boolean ruleFLag) {
        this.ruleFLag = ruleFLag;
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

    public Boolean getInternalOrExternal() {
        return internalOrExternal;
    }

    public void setInternalOrExternal(Boolean internalOrExternal) {
        this.internalOrExternal = internalOrExternal;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof SysPermissionDTO)) {
            return false;
        }

        SysPermissionDTO sysPermissionDTO = (SysPermissionDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, sysPermissionDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "SysPermissionDTO{" +
            "id='" + getId() + "'" +
            ", parentId='" + getParentId() + "'" +
            ", name='" + getName() + "'" +
            ", url='" + getUrl() + "'" +
            ", component='" + getComponent() + "'" +
            ", isRoute='" + getIsRoute() + "'" +
            ", componentName='" + getComponentName() + "'" +
            ", redirect='" + getRedirect() + "'" +
            ", menuType=" + getMenuType() +
            ", perms='" + getPerms() + "'" +
            ", permsType='" + getPermsType() + "'" +
            ", sortNo='" + getSortNo() + "'" +
            ", alwaysShow='" + getAlwaysShow() + "'" +
            ", icon='" + getIcon() + "'" +
            ", isLeaf='" + getIsLeaf() + "'" +
            ", keepAlive='" + getKeepAlive() + "'" +
            ", hidden='" + getHidden() + "'" +
            ", hideTab='" + getHideTab() + "'" +
            ", description='" + getDescription() + "'" +
            ", delFlag='" + getDelFlag() + "'" +
            ", ruleFLag='" + getRuleFLag() + "'" +
            ", createBy='" + getCreateBy() + "'" +
            ", createTime='" + getCreateTime() + "'" +
            ", updateBy='" + getUpdateBy() + "'" +
            ", updateTime='" + getUpdateTime() + "'" +
            ", tenantId='" + getTenantId() + "'" +
            ", internalOrExternal='" + getInternalOrExternal() + "'" +
            "}";
    }
}
