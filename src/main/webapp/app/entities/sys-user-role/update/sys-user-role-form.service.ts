import { Injectable } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';

import { ISysUserRole, NewSysUserRole } from '../sys-user-role.model';

/**
 * A partial Type with required key is used as form input.
 */
type PartialWithRequiredKeyOf<T extends { id: unknown }> = Partial<Omit<T, 'id'>> & { id: T['id'] };

/**
 * Type for createFormGroup and resetForm argument.
 * It accepts ISysUserRole for edit and NewSysUserRoleFormGroupInput for create.
 */
type SysUserRoleFormGroupInput = ISysUserRole | PartialWithRequiredKeyOf<NewSysUserRole>;

type SysUserRoleFormDefaults = Pick<NewSysUserRole, 'id'>;

type SysUserRoleFormGroupContent = {
  id: FormControl<ISysUserRole['id'] | NewSysUserRole['id']>;
  userId: FormControl<ISysUserRole['userId']>;
  roleId: FormControl<ISysUserRole['roleId']>;
};

export type SysUserRoleFormGroup = FormGroup<SysUserRoleFormGroupContent>;

@Injectable({ providedIn: 'root' })
export class SysUserRoleFormService {
  createSysUserRoleFormGroup(sysUserRole: SysUserRoleFormGroupInput = { id: null }): SysUserRoleFormGroup {
    const sysUserRoleRawValue = {
      ...this.getFormDefaults(),
      ...sysUserRole,
    };
    return new FormGroup<SysUserRoleFormGroupContent>({
      id: new FormControl(
        { value: sysUserRoleRawValue.id, disabled: sysUserRoleRawValue.id !== null },
        {
          nonNullable: true,
          validators: [Validators.required],
        }
      ),
      userId: new FormControl(sysUserRoleRawValue.userId),
      roleId: new FormControl(sysUserRoleRawValue.roleId),
    });
  }

  getSysUserRole(form: SysUserRoleFormGroup): ISysUserRole | NewSysUserRole {
    return form.getRawValue() as ISysUserRole | NewSysUserRole;
  }

  resetForm(form: SysUserRoleFormGroup, sysUserRole: SysUserRoleFormGroupInput): void {
    const sysUserRoleRawValue = { ...this.getFormDefaults(), ...sysUserRole };
    form.reset(
      {
        ...sysUserRoleRawValue,
        id: { value: sysUserRoleRawValue.id, disabled: sysUserRoleRawValue.id !== null },
      } as any /* cast to workaround https://github.com/angular/angular/issues/46458 */
    );
  }

  private getFormDefaults(): SysUserRoleFormDefaults {
    return {
      id: null,
    };
  }
}
