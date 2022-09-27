import { Injectable } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';

import { ISysRoleIndex, NewSysRoleIndex } from '../sys-role-index.model';

/**
 * A partial Type with required key is used as form input.
 */
type PartialWithRequiredKeyOf<T extends { id: unknown }> = Partial<Omit<T, 'id'>> & { id: T['id'] };

/**
 * Type for createFormGroup and resetForm argument.
 * It accepts ISysRoleIndex for edit and NewSysRoleIndexFormGroupInput for create.
 */
type SysRoleIndexFormGroupInput = ISysRoleIndex | PartialWithRequiredKeyOf<NewSysRoleIndex>;

type SysRoleIndexFormDefaults = Pick<NewSysRoleIndex, 'id' | 'isRoute'>;

type SysRoleIndexFormGroupContent = {
  id: FormControl<ISysRoleIndex['id'] | NewSysRoleIndex['id']>;
  roleCode: FormControl<ISysRoleIndex['roleCode']>;
  url: FormControl<ISysRoleIndex['url']>;
  component: FormControl<ISysRoleIndex['component']>;
  isRoute: FormControl<ISysRoleIndex['isRoute']>;
  priority: FormControl<ISysRoleIndex['priority']>;
  status: FormControl<ISysRoleIndex['status']>;
  createBy: FormControl<ISysRoleIndex['createBy']>;
  createTime: FormControl<ISysRoleIndex['createTime']>;
  updateBy: FormControl<ISysRoleIndex['updateBy']>;
  updateTime: FormControl<ISysRoleIndex['updateTime']>;
  tenantId: FormControl<ISysRoleIndex['tenantId']>;
};

export type SysRoleIndexFormGroup = FormGroup<SysRoleIndexFormGroupContent>;

@Injectable({ providedIn: 'root' })
export class SysRoleIndexFormService {
  createSysRoleIndexFormGroup(sysRoleIndex: SysRoleIndexFormGroupInput = { id: null }): SysRoleIndexFormGroup {
    const sysRoleIndexRawValue = {
      ...this.getFormDefaults(),
      ...sysRoleIndex,
    };
    return new FormGroup<SysRoleIndexFormGroupContent>({
      id: new FormControl(
        { value: sysRoleIndexRawValue.id, disabled: sysRoleIndexRawValue.id !== null },
        {
          nonNullable: true,
          validators: [Validators.required],
        }
      ),
      roleCode: new FormControl(sysRoleIndexRawValue.roleCode),
      url: new FormControl(sysRoleIndexRawValue.url),
      component: new FormControl(sysRoleIndexRawValue.component),
      isRoute: new FormControl(sysRoleIndexRawValue.isRoute),
      priority: new FormControl(sysRoleIndexRawValue.priority),
      status: new FormControl(sysRoleIndexRawValue.status),
      createBy: new FormControl(sysRoleIndexRawValue.createBy),
      createTime: new FormControl(sysRoleIndexRawValue.createTime),
      updateBy: new FormControl(sysRoleIndexRawValue.updateBy),
      updateTime: new FormControl(sysRoleIndexRawValue.updateTime),
      tenantId: new FormControl(sysRoleIndexRawValue.tenantId),
    });
  }

  getSysRoleIndex(form: SysRoleIndexFormGroup): ISysRoleIndex | NewSysRoleIndex {
    return form.getRawValue() as ISysRoleIndex | NewSysRoleIndex;
  }

  resetForm(form: SysRoleIndexFormGroup, sysRoleIndex: SysRoleIndexFormGroupInput): void {
    const sysRoleIndexRawValue = { ...this.getFormDefaults(), ...sysRoleIndex };
    form.reset(
      {
        ...sysRoleIndexRawValue,
        id: { value: sysRoleIndexRawValue.id, disabled: sysRoleIndexRawValue.id !== null },
      } as any /* cast to workaround https://github.com/angular/angular/issues/46458 */
    );
  }

  private getFormDefaults(): SysRoleIndexFormDefaults {
    return {
      id: null,
      isRoute: false,
    };
  }
}
