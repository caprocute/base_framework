package com.viettel.solution.base.service.dto;

import java.io.Serializable;
import java.util.Objects;
import javax.validation.constraints.*;

/**
 * A DTO for the {@link com.viettel.solution.base.domain.SysUserDepart} entity.
 */
@SuppressWarnings("common-java:DuplicatedBlocks")
public class SysUserDepartDTO implements Serializable {

    @NotNull
    private String id;

    private String userId;

    private String depID;

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

    public String getDepID() {
        return depID;
    }

    public void setDepID(String depID) {
        this.depID = depID;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof SysUserDepartDTO)) {
            return false;
        }

        SysUserDepartDTO sysUserDepartDTO = (SysUserDepartDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, sysUserDepartDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "SysUserDepartDTO{" +
            "id='" + getId() + "'" +
            ", userId='" + getUserId() + "'" +
            ", depID='" + getDepID() + "'" +
            "}";
    }
}
