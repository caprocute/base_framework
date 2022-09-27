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
 * A SysRolePermission.
 */
@JsonIgnoreProperties(value = { "new" })
@Entity
@Table(name = "sys_role_permission")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@SuppressWarnings("common-java:DuplicatedBlocks")
public class SysRolePermission implements Serializable, Persistable<String> {

    private static final long serialVersionUID = 1L;

    @NotNull
    @Id
    @Column(name = "id", nullable = false)
    private String id;

    @Column(name = "role_id")
    private String roleId;

    @Column(name = "permission_id")
    private String permissionId;

    @Column(name = "data_rule_ids")
    private String dataRuleIds;

    @Column(name = "operate_date")
    private LocalDate operateDate;

    @Column(name = "operate_ip")
    private String operateIp;

    @Transient
    private boolean isPersisted;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public String getId() {
        return this.id;
    }

    public SysRolePermission id(String id) {
        this.setId(id);
        return this;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRoleId() {
        return this.roleId;
    }

    public SysRolePermission roleId(String roleId) {
        this.setRoleId(roleId);
        return this;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    public String getPermissionId() {
        return this.permissionId;
    }

    public SysRolePermission permissionId(String permissionId) {
        this.setPermissionId(permissionId);
        return this;
    }

    public void setPermissionId(String permissionId) {
        this.permissionId = permissionId;
    }

    public String getDataRuleIds() {
        return this.dataRuleIds;
    }

    public SysRolePermission dataRuleIds(String dataRuleIds) {
        this.setDataRuleIds(dataRuleIds);
        return this;
    }

    public void setDataRuleIds(String dataRuleIds) {
        this.dataRuleIds = dataRuleIds;
    }

    public LocalDate getOperateDate() {
        return this.operateDate;
    }

    public SysRolePermission operateDate(LocalDate operateDate) {
        this.setOperateDate(operateDate);
        return this;
    }

    public void setOperateDate(LocalDate operateDate) {
        this.operateDate = operateDate;
    }

    public String getOperateIp() {
        return this.operateIp;
    }

    public SysRolePermission operateIp(String operateIp) {
        this.setOperateIp(operateIp);
        return this;
    }

    public void setOperateIp(String operateIp) {
        this.operateIp = operateIp;
    }

    @Transient
    @Override
    public boolean isNew() {
        return !this.isPersisted;
    }

    public SysRolePermission setIsPersisted() {
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
        if (!(o instanceof SysRolePermission)) {
            return false;
        }
        return id != null && id.equals(((SysRolePermission) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "SysRolePermission{" +
            "id=" + getId() +
            ", roleId='" + getRoleId() + "'" +
            ", permissionId='" + getPermissionId() + "'" +
            ", dataRuleIds='" + getDataRuleIds() + "'" +
            ", operateDate='" + getOperateDate() + "'" +
            ", operateIp='" + getOperateIp() + "'" +
            "}";
    }
}
