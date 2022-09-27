package com.viettel.solution.base.service.dto;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;
import javax.validation.constraints.*;

/**
 * A DTO for the {@link com.viettel.solution.base.domain.SysDataLog} entity.
 */
@SuppressWarnings("common-java:DuplicatedBlocks")
public class SysDataLogDTO implements Serializable {

    @NotNull
    private String id;

    private String dataTable;

    private String dataId;

    private String dataContent;

    private Integer dataVersion;

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

    public String getDataTable() {
        return dataTable;
    }

    public void setDataTable(String dataTable) {
        this.dataTable = dataTable;
    }

    public String getDataId() {
        return dataId;
    }

    public void setDataId(String dataId) {
        this.dataId = dataId;
    }

    public String getDataContent() {
        return dataContent;
    }

    public void setDataContent(String dataContent) {
        this.dataContent = dataContent;
    }

    public Integer getDataVersion() {
        return dataVersion;
    }

    public void setDataVersion(Integer dataVersion) {
        this.dataVersion = dataVersion;
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
        if (!(o instanceof SysDataLogDTO)) {
            return false;
        }

        SysDataLogDTO sysDataLogDTO = (SysDataLogDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, sysDataLogDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "SysDataLogDTO{" +
            "id='" + getId() + "'" +
            ", dataTable='" + getDataTable() + "'" +
            ", dataId='" + getDataId() + "'" +
            ", dataContent='" + getDataContent() + "'" +
            ", dataVersion=" + getDataVersion() +
            ", createBy='" + getCreateBy() + "'" +
            ", createTime='" + getCreateTime() + "'" +
            ", updateBy='" + getUpdateBy() + "'" +
            ", updateTime='" + getUpdateTime() + "'" +
            ", tenantId='" + getTenantId() + "'" +
            "}";
    }
}
