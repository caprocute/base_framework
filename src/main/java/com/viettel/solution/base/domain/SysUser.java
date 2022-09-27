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
 * A SysUser.
 */
@JsonIgnoreProperties(value = { "new" })
@Entity
@Table(name = "sys_user")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@SuppressWarnings("common-java:DuplicatedBlocks")
public class SysUser implements Serializable, Persistable<String> {

    private static final long serialVersionUID = 1L;

    @NotNull
    @Id
    @Column(name = "id", nullable = false)
    private String id;

    @Column(name = "user_name")
    private String userName;

    @Column(name = "real_name")
    private String realName;

    @Column(name = "drow_ssap")
    private String drowSsap;

    @Column(name = "salt")
    private String salt;

    @Column(name = "avatar")
    private String avatar;

    @Column(name = "birthday")
    private LocalDate birthday;

    @Column(name = "sex")
    private Integer sex;

    @Column(name = "email")
    private String email;

    @Column(name = "phone")
    private String phone;

    @Column(name = "org_code")
    private String orgCode;

    @Column(name = "status")
    private Integer status;

    @Column(name = "del_flag")
    private Integer delFlag;

    @Column(name = "third_id")
    private String thirdId;

    @Column(name = "third_type")
    private String thirdType;

    @Column(name = "activity_sync")
    private Integer activitySync;

    @Column(name = "work_no")
    private String workNo;

    @Column(name = "post")
    private String post;

    @Column(name = "telephone")
    private String telephone;

    @Column(name = "create_by")
    private String createBy;

    @Column(name = "create_time")
    private LocalDate createTime;

    @Column(name = "update_by")
    private String updateBy;

    @Column(name = "update_time")
    private LocalDate updateTime;

    @Column(name = "user_identity")
    private Integer userIdentity;

    @Column(name = "depart_ids")
    private String departIds;

    @Column(name = "rel_tenant_ids")
    private String relTenantIds;

    @Column(name = "client_id")
    private String clientId;

    @Transient
    private boolean isPersisted;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public String getId() {
        return this.id;
    }

    public SysUser id(String id) {
        this.setId(id);
        return this;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserName() {
        return this.userName;
    }

    public SysUser userName(String userName) {
        this.setUserName(userName);
        return this;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getRealName() {
        return this.realName;
    }

    public SysUser realName(String realName) {
        this.setRealName(realName);
        return this;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getDrowSsap() {
        return this.drowSsap;
    }

    public SysUser drowSsap(String drowSsap) {
        this.setDrowSsap(drowSsap);
        return this;
    }

    public void setDrowSsap(String drowSsap) {
        this.drowSsap = drowSsap;
    }

    public String getSalt() {
        return this.salt;
    }

    public SysUser salt(String salt) {
        this.setSalt(salt);
        return this;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public String getAvatar() {
        return this.avatar;
    }

    public SysUser avatar(String avatar) {
        this.setAvatar(avatar);
        return this;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public LocalDate getBirthday() {
        return this.birthday;
    }

    public SysUser birthday(LocalDate birthday) {
        this.setBirthday(birthday);
        return this;
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }

    public Integer getSex() {
        return this.sex;
    }

    public SysUser sex(Integer sex) {
        this.setSex(sex);
        return this;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public String getEmail() {
        return this.email;
    }

    public SysUser email(String email) {
        this.setEmail(email);
        return this;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return this.phone;
    }

    public SysUser phone(String phone) {
        this.setPhone(phone);
        return this;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getOrgCode() {
        return this.orgCode;
    }

    public SysUser orgCode(String orgCode) {
        this.setOrgCode(orgCode);
        return this;
    }

    public void setOrgCode(String orgCode) {
        this.orgCode = orgCode;
    }

    public Integer getStatus() {
        return this.status;
    }

    public SysUser status(Integer status) {
        this.setStatus(status);
        return this;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getDelFlag() {
        return this.delFlag;
    }

    public SysUser delFlag(Integer delFlag) {
        this.setDelFlag(delFlag);
        return this;
    }

    public void setDelFlag(Integer delFlag) {
        this.delFlag = delFlag;
    }

    public String getThirdId() {
        return this.thirdId;
    }

    public SysUser thirdId(String thirdId) {
        this.setThirdId(thirdId);
        return this;
    }

    public void setThirdId(String thirdId) {
        this.thirdId = thirdId;
    }

    public String getThirdType() {
        return this.thirdType;
    }

    public SysUser thirdType(String thirdType) {
        this.setThirdType(thirdType);
        return this;
    }

    public void setThirdType(String thirdType) {
        this.thirdType = thirdType;
    }

    public Integer getActivitySync() {
        return this.activitySync;
    }

    public SysUser activitySync(Integer activitySync) {
        this.setActivitySync(activitySync);
        return this;
    }

    public void setActivitySync(Integer activitySync) {
        this.activitySync = activitySync;
    }

    public String getWorkNo() {
        return this.workNo;
    }

    public SysUser workNo(String workNo) {
        this.setWorkNo(workNo);
        return this;
    }

    public void setWorkNo(String workNo) {
        this.workNo = workNo;
    }

    public String getPost() {
        return this.post;
    }

    public SysUser post(String post) {
        this.setPost(post);
        return this;
    }

    public void setPost(String post) {
        this.post = post;
    }

    public String getTelephone() {
        return this.telephone;
    }

    public SysUser telephone(String telephone) {
        this.setTelephone(telephone);
        return this;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getCreateBy() {
        return this.createBy;
    }

    public SysUser createBy(String createBy) {
        this.setCreateBy(createBy);
        return this;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    public LocalDate getCreateTime() {
        return this.createTime;
    }

    public SysUser createTime(LocalDate createTime) {
        this.setCreateTime(createTime);
        return this;
    }

    public void setCreateTime(LocalDate createTime) {
        this.createTime = createTime;
    }

    public String getUpdateBy() {
        return this.updateBy;
    }

    public SysUser updateBy(String updateBy) {
        this.setUpdateBy(updateBy);
        return this;
    }

    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy;
    }

    public LocalDate getUpdateTime() {
        return this.updateTime;
    }

    public SysUser updateTime(LocalDate updateTime) {
        this.setUpdateTime(updateTime);
        return this;
    }

    public void setUpdateTime(LocalDate updateTime) {
        this.updateTime = updateTime;
    }

    public Integer getUserIdentity() {
        return this.userIdentity;
    }

    public SysUser userIdentity(Integer userIdentity) {
        this.setUserIdentity(userIdentity);
        return this;
    }

    public void setUserIdentity(Integer userIdentity) {
        this.userIdentity = userIdentity;
    }

    public String getDepartIds() {
        return this.departIds;
    }

    public SysUser departIds(String departIds) {
        this.setDepartIds(departIds);
        return this;
    }

    public void setDepartIds(String departIds) {
        this.departIds = departIds;
    }

    public String getRelTenantIds() {
        return this.relTenantIds;
    }

    public SysUser relTenantIds(String relTenantIds) {
        this.setRelTenantIds(relTenantIds);
        return this;
    }

    public void setRelTenantIds(String relTenantIds) {
        this.relTenantIds = relTenantIds;
    }

    public String getClientId() {
        return this.clientId;
    }

    public SysUser clientId(String clientId) {
        this.setClientId(clientId);
        return this;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    @Transient
    @Override
    public boolean isNew() {
        return !this.isPersisted;
    }

    public SysUser setIsPersisted() {
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
        if (!(o instanceof SysUser)) {
            return false;
        }
        return id != null && id.equals(((SysUser) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "SysUser{" +
            "id=" + getId() +
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
