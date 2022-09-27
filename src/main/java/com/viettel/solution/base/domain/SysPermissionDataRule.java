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
 * A SysPermissionDataRule.
 */
@JsonIgnoreProperties(value = { "new" })
@Entity
@Table(name = "sys_permission_data_rule")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@SuppressWarnings("common-java:DuplicatedBlocks")
public class SysPermissionDataRule implements Serializable, Persistable<String> {

    private static final long serialVersionUID = 1L;

    @NotNull
    @Id
    @Column(name = "id", nullable = false)
    private String id;

    @Column(name = "permission_id")
    private String permissionId;

    @Column(name = "rule_name")
    private String ruleName;

    @Column(name = "rule_column")
    private String ruleColumn;

    @Column(name = "rule_c_onditions")
    private String ruleCOnditions;

    @Column(name = "rule_value")
    private String ruleValue;

    @Column(name = "status")
    private Integer status;

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

    public SysPermissionDataRule id(String id) {
        this.setId(id);
        return this;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPermissionId() {
        return this.permissionId;
    }

    public SysPermissionDataRule permissionId(String permissionId) {
        this.setPermissionId(permissionId);
        return this;
    }

    public void setPermissionId(String permissionId) {
        this.permissionId = permissionId;
    }

    public String getRuleName() {
        return this.ruleName;
    }

    public SysPermissionDataRule ruleName(String ruleName) {
        this.setRuleName(ruleName);
        return this;
    }

    public void setRuleName(String ruleName) {
        this.ruleName = ruleName;
    }

    public String getRuleColumn() {
        return this.ruleColumn;
    }

    public SysPermissionDataRule ruleColumn(String ruleColumn) {
        this.setRuleColumn(ruleColumn);
        return this;
    }

    public void setRuleColumn(String ruleColumn) {
        this.ruleColumn = ruleColumn;
    }

    public String getRuleCOnditions() {
        return this.ruleCOnditions;
    }

    public SysPermissionDataRule ruleCOnditions(String ruleCOnditions) {
        this.setRuleCOnditions(ruleCOnditions);
        return this;
    }

    public void setRuleCOnditions(String ruleCOnditions) {
        this.ruleCOnditions = ruleCOnditions;
    }

    public String getRuleValue() {
        return this.ruleValue;
    }

    public SysPermissionDataRule ruleValue(String ruleValue) {
        this.setRuleValue(ruleValue);
        return this;
    }

    public void setRuleValue(String ruleValue) {
        this.ruleValue = ruleValue;
    }

    public Integer getStatus() {
        return this.status;
    }

    public SysPermissionDataRule status(Integer status) {
        this.setStatus(status);
        return this;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getCreateBy() {
        return this.createBy;
    }

    public SysPermissionDataRule createBy(String createBy) {
        this.setCreateBy(createBy);
        return this;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    public LocalDate getCreateTime() {
        return this.createTime;
    }

    public SysPermissionDataRule createTime(LocalDate createTime) {
        this.setCreateTime(createTime);
        return this;
    }

    public void setCreateTime(LocalDate createTime) {
        this.createTime = createTime;
    }

    public String getUpdateBy() {
        return this.updateBy;
    }

    public SysPermissionDataRule updateBy(String updateBy) {
        this.setUpdateBy(updateBy);
        return this;
    }

    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy;
    }

    public LocalDate getUpdateTime() {
        return this.updateTime;
    }

    public SysPermissionDataRule updateTime(LocalDate updateTime) {
        this.setUpdateTime(updateTime);
        return this;
    }

    public void setUpdateTime(LocalDate updateTime) {
        this.updateTime = updateTime;
    }

    public String getTenantId() {
        return this.tenantId;
    }

    public SysPermissionDataRule tenantId(String tenantId) {
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

    public SysPermissionDataRule setIsPersisted() {
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
        if (!(o instanceof SysPermissionDataRule)) {
            return false;
        }
        return id != null && id.equals(((SysPermissionDataRule) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "SysPermissionDataRule{" +
            "id=" + getId() +
            ", permissionId='" + getPermissionId() + "'" +
            ", ruleName='" + getRuleName() + "'" +
            ", ruleColumn='" + getRuleColumn() + "'" +
            ", ruleCOnditions='" + getRuleCOnditions() + "'" +
            ", ruleValue='" + getRuleValue() + "'" +
            ", status=" + getStatus() +
            ", createBy='" + getCreateBy() + "'" +
            ", createTime='" + getCreateTime() + "'" +
            ", updateBy='" + getUpdateBy() + "'" +
            ", updateTime='" + getUpdateTime() + "'" +
            ", tenantId='" + getTenantId() + "'" +
            "}";
    }
}
