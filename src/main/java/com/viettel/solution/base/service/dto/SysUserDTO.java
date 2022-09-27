package com.viettel.solution.base.service.dto;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;
import javax.validation.constraints.*;

/**
 * A DTO for the {@link com.viettel.solution.base.domain.SysUser} entity.
 */
@SuppressWarnings("common-java:DuplicatedBlocks")
public class SysUserDTO implements Serializable {

    @NotNull
    private String id;

    private String userName;

    private String realName;

    private String drowSsap;

    private String salt;

    private String avatar;

    private LocalDate birthday;

    private Integer sex;

    private String email;

    private String phone;

    private String orgCode;

    private Integer status;

    private Integer delFlag;

    private String thirdId;

    private String thirdType;

    private Integer activitySync;

    private String workNo;

    private String post;

    private String telephone;

    private String createBy;

    private LocalDate createTime;

    private String updateBy;

    private LocalDate updateTime;

    private Integer userIdentity;

    private String departIds;

    private String relTenantIds;

    private String clientId;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getDrowSsap() {
        return drowSsap;
    }

    public void setDrowSsap(String drowSsap) {
        this.drowSsap = drowSsap;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getOrgCode() {
        return orgCode;
    }

    public void setOrgCode(String orgCode) {
        this.orgCode = orgCode;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(Integer delFlag) {
        this.delFlag = delFlag;
    }

    public String getThirdId() {
        return thirdId;
    }

    public void setThirdId(String thirdId) {
        this.thirdId = thirdId;
    }

    public String getThirdType() {
        return thirdType;
    }

    public void setThirdType(String thirdType) {
        this.thirdType = thirdType;
    }

    public Integer getActivitySync() {
        return activitySync;
    }

    public void setActivitySync(Integer activitySync) {
        this.activitySync = activitySync;
    }

    public String getWorkNo() {
        return workNo;
    }

    public void setWorkNo(String workNo) {
        this.workNo = workNo;
    }

    public String getPost() {
        return post;
    }

    public void setPost(String post) {
        this.post = post;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
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

    public Integer getUserIdentity() {
        return userIdentity;
    }

    public void setUserIdentity(Integer userIdentity) {
        this.userIdentity = userIdentity;
    }

    public String getDepartIds() {
        return departIds;
    }

    public void setDepartIds(String departIds) {
        this.departIds = departIds;
    }

    public String getRelTenantIds() {
        return relTenantIds;
    }

    public void setRelTenantIds(String relTenantIds) {
        this.relTenantIds = relTenantIds;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof SysUserDTO)) {
            return false;
        }

        SysUserDTO sysUserDTO = (SysUserDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, sysUserDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "SysUserDTO{" +
            "id='" + getId() + "'" +
            ", userName='" + getUserName() + "'" +
            ", realName='" + getRealName() + "'" +
            ", drowSsap='" + getDrowSsap() + "'" +
            ", salt='" + getSalt() + "'" +
            ", avatar='" + getAvatar() + "'" +
            ", birthday='" + getBirthday() + "'" +
            ", sex=" + getSex() +
            ", email='" + getEmail() + "'" +
            ", phone='" + getPhone() + "'" +
            ", orgCode='" + getOrgCode() + "'" +
            ", status=" + getStatus() +
            ", delFlag=" + getDelFlag() +
            ", thirdId='" + getThirdId() + "'" +
            ", thirdType='" + getThirdType() + "'" +
            ", activitySync=" + getActivitySync() +
            ", workNo='" + getWorkNo() + "'" +
            ", post='" + getPost() + "'" +
            ", telephone='" + getTelephone() + "'" +
            ", createBy='" + getCreateBy() + "'" +
            ", createTime='" + getCreateTime() + "'" +
            ", updateBy='" + getUpdateBy() + "'" +
            ", updateTime='" + getUpdateTime() + "'" +
            ", userIdentity=" + getUserIdentity() +
            ", departIds='" + getDepartIds() + "'" +
            ", relTenantIds='" + getRelTenantIds() + "'" +
            ", clientId='" + getClientId() + "'" +
            "}";
    }
}
