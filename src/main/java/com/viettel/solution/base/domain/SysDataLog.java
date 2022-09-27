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
 * A SysDataLog.
 */
@JsonIgnoreProperties(value = { "new" })
@Entity
@Table(name = "sys_data_log")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@SuppressWarnings("common-java:DuplicatedBlocks")
public class SysDataLog implements Serializable, Persistable<String> {

    private static final long serialVersionUID = 1L;

    @NotNull
    @Id
    @Column(name = "id", nullable = false)
    private String id;

    @Column(name = "data_table")
    private String dataTable;

    @Column(name = "data_id")
    private String dataId;

    @Column(name = "data_content")
    private String dataContent;

    @Column(name = "data_version")
    private Integer dataVersion;

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

    public SysDataLog id(String id) {
        this.setId(id);
        return this;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDataTable() {
        return this.dataTable;
    }

    public SysDataLog dataTable(String dataTable) {
        this.setDataTable(dataTable);
        return this;
    }

    public void setDataTable(String dataTable) {
        this.dataTable = dataTable;
    }

    public String getDataId() {
        return this.dataId;
    }

    public SysDataLog dataId(String dataId) {
        this.setDataId(dataId);
        return this;
    }

    public void setDataId(String dataId) {
        this.dataId = dataId;
    }

    public String getDataContent() {
        return this.dataContent;
    }

    public SysDataLog dataContent(String dataContent) {
        this.setDataContent(dataContent);
        return this;
    }

    public void setDataContent(String dataContent) {
        this.dataContent = dataContent;
    }

    public Integer getDataVersion() {
        return this.dataVersion;
    }

    public SysDataLog dataVersion(Integer dataVersion) {
        this.setDataVersion(dataVersion);
        return this;
    }

    public void setDataVersion(Integer dataVersion) {
        this.dataVersion = dataVersion;
    }

    public String getCreateBy() {
        return this.createBy;
    }

    public SysDataLog createBy(String createBy) {
        this.setCreateBy(createBy);
        return this;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    public LocalDate getCreateTime() {
        return this.createTime;
    }

    public SysDataLog createTime(LocalDate createTime) {
        this.setCreateTime(createTime);
        return this;
    }

    public void setCreateTime(LocalDate createTime) {
        this.createTime = createTime;
    }

    public String getUpdateBy() {
        return this.updateBy;
    }

    public SysDataLog updateBy(String updateBy) {
        this.setUpdateBy(updateBy);
        return this;
    }

    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy;
    }

    public LocalDate getUpdateTime() {
        return this.updateTime;
    }

    public SysDataLog updateTime(LocalDate updateTime) {
        this.setUpdateTime(updateTime);
        return this;
    }

    public void setUpdateTime(LocalDate updateTime) {
        this.updateTime = updateTime;
    }

    public String getTenantId() {
        return this.tenantId;
    }

    public SysDataLog tenantId(String tenantId) {
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

    public SysDataLog setIsPersisted() {
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
        if (!(o instanceof SysDataLog)) {
            return false;
        }
        return id != null && id.equals(((SysDataLog) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "SysDataLog{" +
            "id=" + getId() +
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
