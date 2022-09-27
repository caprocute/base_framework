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
 * A SysDepart.
 */
@JsonIgnoreProperties(value = { "new" })
@Entity
@Table(name = "sys_depart")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@SuppressWarnings("common-java:DuplicatedBlocks")
public class SysDepart implements Serializable, Persistable<String> {

    private static final long serialVersionUID = 1L;

    @NotNull
    @Id
    @Column(name = "id", nullable = false)
    private String id;

    @Column(name = "parent_id")
    private String parentId;

    @NotNull
    @Column(name = "depart_name", nullable = false)
    private String departName;

    @Column(name = "depart_order")
    private Integer departOrder;

    @NotNull
    @Column(name = "org_category", nullable = false)
    private String orgCategory;

    @Column(name = "org_type")
    private String orgType;

    @NotNull
    @Column(name = "org_code", nullable = false)
    private String orgCode;

    @Column(name = "mobile")
    private String mobile;

    @Column(name = "fax")
    private String fax;

    @Column(name = "address")
    private String address;

    @Column(name = "memo")
    private String memo;

    @Column(name = "status")
    private Boolean status;

    @Column(name = "del_flag")
    private Boolean delFlag;

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

    public SysDepart id(String id) {
        this.setId(id);
        return this;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getParentId() {
        return this.parentId;
    }

    public SysDepart parentId(String parentId) {
        this.setParentId(parentId);
        return this;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public String getDepartName() {
        return this.departName;
    }

    public SysDepart departName(String departName) {
        this.setDepartName(departName);
        return this;
    }

    public void setDepartName(String departName) {
        this.departName = departName;
    }

    public Integer getDepartOrder() {
        return this.departOrder;
    }

    public SysDepart departOrder(Integer departOrder) {
        this.setDepartOrder(departOrder);
        return this;
    }

    public void setDepartOrder(Integer departOrder) {
        this.departOrder = departOrder;
    }

    public String getOrgCategory() {
        return this.orgCategory;
    }

    public SysDepart orgCategory(String orgCategory) {
        this.setOrgCategory(orgCategory);
        return this;
    }

    public void setOrgCategory(String orgCategory) {
        this.orgCategory = orgCategory;
    }

    public String getOrgType() {
        return this.orgType;
    }

    public SysDepart orgType(String orgType) {
        this.setOrgType(orgType);
        return this;
    }

    public void setOrgType(String orgType) {
        this.orgType = orgType;
    }

    public String getOrgCode() {
        return this.orgCode;
    }

    public SysDepart orgCode(String orgCode) {
        this.setOrgCode(orgCode);
        return this;
    }

    public void setOrgCode(String orgCode) {
        this.orgCode = orgCode;
    }

    public String getMobile() {
        return this.mobile;
    }

    public SysDepart mobile(String mobile) {
        this.setMobile(mobile);
        return this;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getFax() {
        return this.fax;
    }

    public SysDepart fax(String fax) {
        this.setFax(fax);
        return this;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    public String getAddress() {
        return this.address;
    }

    public SysDepart address(String address) {
        this.setAddress(address);
        return this;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getMemo() {
        return this.memo;
    }

    public SysDepart memo(String memo) {
        this.setMemo(memo);
        return this;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    public Boolean getStatus() {
        return this.status;
    }

    public SysDepart status(Boolean status) {
        this.setStatus(status);
        return this;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public Boolean getDelFlag() {
        return this.delFlag;
    }

    public SysDepart delFlag(Boolean delFlag) {
        this.setDelFlag(delFlag);
        return this;
    }

    public void setDelFlag(Boolean delFlag) {
        this.delFlag = delFlag;
    }

    public String getCreateBy() {
        return this.createBy;
    }

    public SysDepart createBy(String createBy) {
        this.setCreateBy(createBy);
        return this;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    public LocalDate getCreateTime() {
        return this.createTime;
    }

    public SysDepart createTime(LocalDate createTime) {
        this.setCreateTime(createTime);
        return this;
    }

    public void setCreateTime(LocalDate createTime) {
        this.createTime = createTime;
    }

    public String getUpdateBy() {
        return this.updateBy;
    }

    public SysDepart updateBy(String updateBy) {
        this.setUpdateBy(updateBy);
        return this;
    }

    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy;
    }

    public LocalDate getUpdateTime() {
        return this.updateTime;
    }

    public SysDepart updateTime(LocalDate updateTime) {
        this.setUpdateTime(updateTime);
        return this;
    }

    public void setUpdateTime(LocalDate updateTime) {
        this.updateTime = updateTime;
    }

    public String getTenantId() {
        return this.tenantId;
    }

    public SysDepart tenantId(String tenantId) {
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

    public SysDepart setIsPersisted() {
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
        if (!(o instanceof SysDepart)) {
            return false;
        }
        return id != null && id.equals(((SysDepart) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "SysDepart{" +
            "id=" + getId() +
            ", parentId='" + getParentId() + "'" +
            ", departName='" + getDepartName() + "'" +
            ", departOrder=" + getDepartOrder() +
            ", orgCategory='" + getOrgCategory() + "'" +
            ", orgType='" + getOrgType() + "'" +
            ", orgCode='" + getOrgCode() + "'" +
            ", mobile='" + getMobile() + "'" +
            ", fax='" + getFax() + "'" +
            ", address='" + getAddress() + "'" +
            ", memo='" + getMemo() + "'" +
            ", status='" + getStatus() + "'" +
            ", delFlag='" + getDelFlag() + "'" +
            ", createBy='" + getCreateBy() + "'" +
            ", createTime='" + getCreateTime() + "'" +
            ", updateBy='" + getUpdateBy() + "'" +
            ", updateTime='" + getUpdateTime() + "'" +
            ", tenantId='" + getTenantId() + "'" +
            "}";
    }
}
