package com.viettel.solution.base.service.dto;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;
import javax.validation.constraints.*;

/**
 * A DTO for the {@link com.viettel.solution.base.domain.SysRolePermission} entity.
 */
@SuppressWarnings("common-java:DuplicatedBlocks")
public class SysRolePermissionDTO implements Serializable {

    @NotNull
    private String id;

    private String roleId;

    private String permissionId;

    private String dataRuleIds;

    private LocalDate operateDate;

    private String operateIp;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    public String getPermissionId() {
        return permissionId;
    }

    public void setPermissionId(String permissionId) {
        this.permissionId = permissionId;
    }

    public String getDataRuleIds() {
        return dataRuleIds;
    }

    public void setDataRuleIds(String dataRuleIds) {
        this.dataRuleIds = dataRuleIds;
    }

    public LocalDate getOperateDate() {
        return operateDate;
    }

    public void setOperateDate(LocalDate operateDate) {
        this.operateDate = operateDate;
    }

    public String getOperateIp() {
        return operateIp;
    }

    public void setOperateIp(String operateIp) {
        this.operateIp = operateIp;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof SysRolePermissionDTO)) {
            return false;
        }

        SysRolePermissionDTO sysRolePermissionDTO = (SysRolePermissionDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, sysRolePermissionDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "SysRolePermissionDTO{" +
            "id='" + getId() + "'" +
            ", roleId='" + getRoleId() + "'" +
            ", permissionId='" + getPermissionId() + "'" +
            ", dataRuleIds='" + getDataRuleIds() + "'" +
            ", operateDate='" + getOperateDate() + "'" +
            ", operateIp='" + getOperateIp() + "'" +
            "}";
    }
}
