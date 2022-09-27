import { Injectable } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';

import { ISysRolePermission, NewSysRolePermission } from '../sys-role-permission.model';

/**
 * A partial Type with required key is used as form input.
 */
type PartialWithRequiredKeyOf<T extends { id: unknown }> = Partial<Omit<T, 'id'>> & { id: T['id'] };

/**
 * Type for createFormGroup and resetForm argument.
 * It accepts ISysRolePermission for edit and NewSysRolePermissionFormGroupInput for create.
 */
type SysRolePermissionFormGroupInput = ISysRolePermission | PartialWithRequiredKeyOf<NewSysRolePermission>;

type SysRolePermissionFormDefaults = Pick<NewSysRolePermission, 'id'>;

type SysRolePermissionFormGroupContent = {
  id: FormControl<ISysRolePermission['id'] | NewSysRolePermission['id']>;
  roleId: FormControl<ISysRolePermission['roleId']>;
  permissionId: FormControl<ISysRolePermission['permissionId']>;
  dataRuleIds: FormControl<ISysRolePermission['dataRuleIds']>;
  operateDate: FormControl<ISysRolePermission['operateDate']>;
  operateIp: FormControl<ISysRolePermission['operateIp']>;
};

export type SysRolePermissionFormGroup = FormGroup<SysRolePermissionFormGroupContent>;

@Injectable({ providedIn: 'root' })
export class SysRolePermissionFormService {
  createSysRolePermissionFormGroup(sysRolePermission: SysRolePermissionFormGroupInput = { id: null }): SysRolePermissionFormGroup {
    const sysRolePermissionRawValue = {
      ...this.getFormDefaults(),
      ...sysRolePermission,
    };
    return new FormGroup<SysRolePermissionFormGroupContent>({
      id: new FormControl(
        { value: sysRolePermissionRawValue.id, disabled: sysRolePermissionRawValue.id !== null },
        {
          nonNullable: true,
          validators: [Validators.required],
        }
      ),
      roleId: new FormControl(sysRolePermissionRawValue.roleId),
      permissionId: new FormControl(sysRolePermissionRawValue.permissionId),
      dataRuleIds: new FormControl(sysRolePermissionRawValue.dataRuleIds),
      operateDate: new FormControl(sysRolePermissionRawValue.operateDate),
      operateIp: new FormControl(sysRolePermissionRawValue.operateIp),
    });
  }

  getSysRolePermission(form: SysRolePermissionFormGroup): ISysRolePermission | NewSysRolePermission {
    return form.getRawValue() as ISysRolePermission | NewSysRolePermission;
  }

  resetForm(form: SysRolePermissionFormGroup, sysRolePermission: SysRolePermissionFormGroupInput): void {
    const sysRolePermissionRawValue = { ...this.getFormDefaults(), ...sysRolePermission };
    form.reset(
      {
        ...sysRolePermissionRawValue,
        id: { value: sysRolePermissionRawValue.id, disabled: sysRolePermissionRawValue.id !== null },
      } as any /* cast to workaround https://github.com/angular/angular/issues/46458 */
    );
  }

  private getFormDefaults(): SysRolePermissionFormDefaults {
    return {
      id: null,
    };
  }
}
