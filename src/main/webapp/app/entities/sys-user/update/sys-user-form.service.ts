import { Injectable } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';

import { ISysUser, NewSysUser } from '../sys-user.model';

/**
 * A partial Type with required key is used as form input.
 */
type PartialWithRequiredKeyOf<T extends { id: unknown }> = Partial<Omit<T, 'id'>> & { id: T['id'] };

/**
 * Type for createFormGroup and resetForm argument.
 * It accepts ISysUser for edit and NewSysUserFormGroupInput for create.
 */
type SysUserFormGroupInput = ISysUser | PartialWithRequiredKeyOf<NewSysUser>;

type SysUserFormDefaults = Pick<NewSysUser, 'id'>;

type SysUserFormGroupContent = {
  id: FormControl<ISysUser['id'] | NewSysUser['id']>;
  userName: FormControl<ISysUser['userName']>;
  realName: FormControl<ISysUser['realName']>;
  drowSsap: FormControl<ISysUser['drowSsap']>;
  salt: FormControl<ISysUser['salt']>;
  avatar: FormControl<ISysUser['avatar']>;
  birthday: FormControl<ISysUser['birthday']>;
  sex: FormControl<ISysUser['sex']>;
  email: FormControl<ISysUser['email']>;
  phone: FormControl<ISysUser['phone']>;
  orgCode: FormControl<ISysUser['orgCode']>;
  status: FormControl<ISysUser['status']>;
  delFlag: FormControl<ISysUser['delFlag']>;
  thirdId: FormControl<ISysUser['thirdId']>;
  thirdType: FormControl<ISysUser['thirdType']>;
  activitySync: FormControl<ISysUser['activitySync']>;
  workNo: FormControl<ISysUser['workNo']>;
  post: FormControl<ISysUser['post']>;
  telephone: FormControl<ISysUser['telephone']>;
  createBy: FormControl<ISysUser['createBy']>;
  createTime: FormControl<ISysUser['createTime']>;
  updateBy: FormControl<ISysUser['updateBy']>;
  updateTime: FormControl<ISysUser['updateTime']>;
  userIdentity: FormControl<ISysUser['userIdentity']>;
  departIds: FormControl<ISysUser['departIds']>;
  relTenantIds: FormControl<ISysUser['relTenantIds']>;
  clientId: FormControl<ISysUser['clientId']>;
};

export type SysUserFormGroup = FormGroup<SysUserFormGroupContent>;

@Injectable({ providedIn: 'root' })
export class SysUserFormService {
  createSysUserFormGroup(sysUser: SysUserFormGroupInput = { id: null }): SysUserFormGroup {
    const sysUserRawValue = {
      ...this.getFormDefaults(),
      ...sysUser,
    };
    return new FormGroup<SysUserFormGroupContent>({
      id: new FormControl(
        { value: sysUserRawValue.id, disabled: sysUserRawValue.id !== null },
        {
          nonNullable: true,
          validators: [Validators.required],
        }
      ),
      userName: new FormControl(sysUserRawValue.userName),
      realName: new FormControl(sysUserRawValue.realName),
      drowSsap: new FormControl(sysUserRawValue.drowSsap),
      salt: new FormControl(sysUserRawValue.salt),
      avatar: new FormControl(sysUserRawValue.avatar),
      birthday: new FormControl(sysUserRawValue.birthday),
      sex: new FormControl(sysUserRawValue.sex),
      email: new FormControl(sysUserRawValue.email),
      phone: new FormControl(sysUserRawValue.phone),
      orgCode: new FormControl(sysUserRawValue.orgCode),
      status: new FormControl(sysUserRawValue.status),
      delFlag: new FormControl(sysUserRawValue.delFlag),
      thirdId: new FormControl(sysUserRawValue.thirdId),
      thirdType: new FormControl(sysUserRawValue.thirdType),
      activitySync: new FormControl(sysUserRawValue.activitySync),
      workNo: new FormControl(sysUserRawValue.workNo),
      post: new FormControl(sysUserRawValue.post),
      telephone: new FormControl(sysUserRawValue.telephone),
      createBy: new FormControl(sysUserRawValue.createBy),
      createTime: new FormControl(sysUserRawValue.createTime),
      updateBy: new FormControl(sysUserRawValue.updateBy),
      updateTime: new FormControl(sysUserRawValue.updateTime),
      userIdentity: new FormControl(sysUserRawValue.userIdentity),
      departIds: new FormControl(sysUserRawValue.departIds),
      relTenantIds: new FormControl(sysUserRawValue.relTenantIds),
      clientId: new FormControl(sysUserRawValue.clientId),
    });
  }

  getSysUser(form: SysUserFormGroup): ISysUser | NewSysUser {
    return form.getRawValue() as ISysUser | NewSysUser;
  }

  resetForm(form: SysUserFormGroup, sysUser: SysUserFormGroupInput): void {
    const sysUserRawValue = { ...this.getFormDefaults(), ...sysUser };
    form.reset(
      {
        ...sysUserRawValue,
        id: { value: sysUserRawValue.id, disabled: sysUserRawValue.id !== null },
      } as any /* cast to workaround https://github.com/angular/angular/issues/46458 */
    );
  }

  private getFormDefaults(): SysUserFormDefaults {
    return {
      id: null,
    };
  }
}
