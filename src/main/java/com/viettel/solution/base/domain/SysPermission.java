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
 * A SysPermission.
 */
@JsonIgnoreProperties(value = { "new" })
@Entity
@Table(name = "sys_permission")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@SuppressWarnings("common-java:DuplicatedBlocks")
public class SysPermission implements Serializable, Persistable<String> {

    private static final long serialVersionUID = 1L;

    @NotNull
    @Id
    @Column(name = "id", nullable = false)
    private String id;

    @Column(name = "parent_id")
    private String parentId;

    @Column(name = "name")
    private String name;

    @Column(name = "url")
    private String url;

    @Column(name = "component")
    private String component;

    @Column(name = "is_route")
    private Boolean isRoute;

    @Column(name = "component_name")
    private String componentName;

    @Column(name = "redirect")
    private String redirect;

    @Column(name = "menu_type")
    private Integer menuType;

    @Column(name = "perms")
    private String perms;

    @Column(name = "perms_type")
    private String permsType;

    @Column(name = "sort_no")
    private String sortNo;

    @Column(name = "always_show")
    private Boolean alwaysShow;

    @Column(name = "icon")
    private String icon;

    @Column(name = "is_leaf")
    private Boolean isLeaf;

    @Column(name = "keep_alive")
    private Boolean keepAlive;

    @Column(name = "hidden")
    private Boolean hidden;

    @Column(name = "hide_tab")
    private Boolean hideTab;

    @Column(name = "description")
    private String description;

    @Column(name = "del_flag")
    private Boolean delFlag;

    @Column(name = "rule_f_lag")
    private Boolean ruleFLag;

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

    @Column(name = "internal_or_external")
    private Boolean internalOrExternal;

    @Transient
    private boolean isPersisted;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public String getId() {
        return this.id;
    }

    public SysPermission id(String id) {
        this.setId(id);
        return this;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getParentId() {
        return this.parentId;
    }

    public SysPermission parentId(String parentId) {
        this.setParentId(parentId);
        return this;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public String getName() {
        return this.name;
    }

    public SysPermission name(String name) {
        this.setName(name);
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return this.url;
    }

    public SysPermission url(String url) {
        this.setUrl(url);
        return this;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getComponent() {
        return this.component;
    }

    public SysPermission component(String component) {
        this.setComponent(component);
        return this;
    }

    public void setComponent(String component) {
        this.component = component;
    }

    public Boolean getIsRoute() {
        return this.isRoute;
    }

    public SysPermission isRoute(Boolean isRoute) {
        this.setIsRoute(isRoute);
        return this;
    }

    public void setIsRoute(Boolean isRoute) {
        this.isRoute = isRoute;
    }

    public String getComponentName() {
        return this.componentName;
    }

    public SysPermission componentName(String componentName) {
        this.setComponentName(componentName);
        return this;
    }

    public void setComponentName(String componentName) {
        this.componentName = componentName;
    }

    public String getRedirect() {
        return this.redirect;
    }

    public SysPermission redirect(String redirect) {
        this.setRedirect(redirect);
        return this;
    }

    public void setRedirect(String redirect) {
        this.redirect = redirect;
    }

    public Integer getMenuType() {
        return this.menuType;
    }

    public SysPermission menuType(Integer menuType) {
        this.setMenuType(menuType);
        return this;
    }

    public void setMenuType(Integer menuType) {
        this.menuType = menuType;
    }

    public String getPerms() {
        return this.perms;
    }

    public SysPermission perms(String perms) {
        this.setPerms(perms);
        return this;
    }

    public void setPerms(String perms) {
        this.perms = perms;
    }

    public String getPermsType() {
        return this.permsType;
    }

    public SysPermission permsType(String permsType) {
        this.setPermsType(permsType);
        return this;
    }

    public void setPermsType(String permsType) {
        this.permsType = permsType;
    }

    public String getSortNo() {
        return this.sortNo;
    }

    public SysPermission sortNo(String sortNo) {
        this.setSortNo(sortNo);
        return this;
    }

    public void setSortNo(String sortNo) {
        this.sortNo = sortNo;
    }

    public Boolean getAlwaysShow() {
        return this.alwaysShow;
    }

    public SysPermission alwaysShow(Boolean alwaysShow) {
        this.setAlwaysShow(alwaysShow);
        return this;
    }

    public void setAlwaysShow(Boolean alwaysShow) {
        this.alwaysShow = alwaysShow;
    }

    public String getIcon() {
        return this.icon;
    }

    public SysPermission icon(String icon) {
        this.setIcon(icon);
        return this;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public Boolean getIsLeaf() {
        return this.isLeaf;
    }

    public SysPermission isLeaf(Boolean isLeaf) {
        this.setIsLeaf(isLeaf);
        return this;
    }

    public void setIsLeaf(Boolean isLeaf) {
        this.isLeaf = isLeaf;
    }

    public Boolean getKeepAlive() {
        return this.keepAlive;
    }

    public SysPermission keepAlive(Boolean keepAlive) {
        this.setKeepAlive(keepAlive);
        return this;
    }

    public void setKeepAlive(Boolean keepAlive) {
        this.keepAlive = keepAlive;
    }

    public Boolean getHidden() {
        return this.hidden;
    }

    public SysPermission hidden(Boolean hidden) {
        this.setHidden(hidden);
        return this;
    }

    public void setHidden(Boolean hidden) {
        this.hidden = hidden;
    }

    public Boolean getHideTab() {
        return this.hideTab;
    }

    public SysPermission hideTab(Boolean hideTab) {
        this.setHideTab(hideTab);
        return this;
    }

    public void setHideTab(Boolean hideTab) {
        this.hideTab = hideTab;
    }

    public String getDescription() {
        return this.description;
    }

    public SysPermission description(String description) {
        this.setDescription(description);
        return this;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Boolean getDelFlag() {
        return this.delFlag;
    }

    public SysPermission delFlag(Boolean delFlag) {
        this.setDelFlag(delFlag);
        return this;
    }

    public void setDelFlag(Boolean delFlag) {
        this.delFlag = delFlag;
    }

    public Boolean getRuleFLag() {
        return this.ruleFLag;
    }

    public SysPermission ruleFLag(Boolean ruleFLag) {
        this.setRuleFLag(ruleFLag);
        return this;
    }

    public void setRuleFLag(Boolean ruleFLag) {
        this.ruleFLag = ruleFLag;
    }

    public String getCreateBy() {
        return this.createBy;
    }

    public SysPermission createBy(String createBy) {
        this.setCreateBy(createBy);
        return this;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    public LocalDate getCreateTime() {
        return this.createTime;
    }

    public SysPermission createTime(LocalDate createTime) {
        this.setCreateTime(createTime);
        return this;
    }

    public void setCreateTime(LocalDate createTime) {
        this.createTime = createTime;
    }

    public String getUpdateBy() {
        return this.updateBy;
    }

    public SysPermission updateBy(String updateBy) {
        this.setUpdateBy(updateBy);
        return this;
    }

    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy;
    }

    public LocalDate getUpdateTime() {
        return this.updateTime;
    }

    public SysPermission updateTime(LocalDate updateTime) {
        this.setUpdateTime(updateTime);
        return this;
    }

    public void setUpdateTime(LocalDate updateTime) {
        this.updateTime = updateTime;
    }

    public String getTenantId() {
        return this.tenantId;
    }

    public SysPermission tenantId(String tenantId) {
        this.setTenantId(tenantId);
        return this;
    }

    public void setTenantId(String tenantId) {
        this.tenantId = tenantId;
    }

    public Boolean getInternalOrExternal() {
        return this.internalOrExternal;
    }

    public SysPermission internalOrExternal(Boolean internalOrExternal) {
        this.setInternalOrExternal(internalOrExternal);
        return this;
    }

    public void setInternalOrExternal(Boolean internalOrExternal) {
        this.internalOrExternal = internalOrExternal;
    }

    @Transient
    @Override
    public boolean isNew() {
        return !this.isPersisted;
    }

    public SysPermission setIsPersisted() {
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
        if (!(o instanceof SysPermission)) {
            return false;
        }
        return id != null && id.equals(((SysPermission) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "SysPermission{" +
            "id=" + getId() +
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
