package com.viettel.solution.base.service.dto;

import java.io.Serializable;
import java.util.Objects;
import javax.validation.constraints.*;

/**
 * A DTO for the {@link com.viettel.solution.base.domain.SysUserRole} entity.
 */
@SuppressWarnings("common-java:DuplicatedBlocks")
public class SysUserRoleDTO implements Serializable {

    @NotNull
    private String id;

    private String userId;

    private String roleId;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof SysUserRoleDTO)) {
            return false;
        }

        SysUserRoleDTO sysUserRoleDTO = (SysUserRoleDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, sysUserRoleDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "SysUserRoleDTO{" +
            "id='" + getId() + "'" +
            ", userId='" + getUserId() + "'" +
            ", roleId='" + getRoleId() + "'" +
            "}";
    }
}
