import { Injectable } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';

import { ISysDepartRole, NewSysDepartRole } from '../sys-depart-role.model';

/**
 * A partial Type with required key is used as form input.
 */
type PartialWithRequiredKeyOf<T extends { id: unknown }> = Partial<Omit<T, 'id'>> & { id: T['id'] };

/**
 * Type for createFormGroup and resetForm argument.
 * It accepts ISysDepartRole for edit and NewSysDepartRoleFormGroupInput for create.
 */
type SysDepartRoleFormGroupInput = ISysDepartRole | PartialWithRequiredKeyOf<NewSysDepartRole>;

type SysDepartRoleFormDefaults = Pick<NewSysDepartRole, 'id'>;

type SysDepartRoleFormGroupContent = {
  id: FormControl<ISysDepartRole['id'] | NewSysDepartRole['id']>;
  departID: FormControl<ISysDepartRole['departID']>;
  roleName: FormControl<ISysDepartRole['roleName']>;
  roleCode: FormControl<ISysDepartRole['roleCode']>;
  description: FormControl<ISysDepartRole['description']>;
  createBy: FormControl<ISysDepartRole['createBy']>;
  createTime: FormControl<ISysDepartRole['createTime']>;
  updateBy: FormControl<ISysDepartRole['updateBy']>;
  updateTime: FormControl<ISysDepartRole['updateTime']>;
  tenantId: FormControl<ISysDepartRole['tenantId']>;
};

export type SysDepartRoleFormGroup = FormGroup<SysDepartRoleFormGroupContent>;

@Injectable({ providedIn: 'root' })
export class SysDepartRoleFormService {
  createSysDepartRoleFormGroup(sysDepartRole: SysDepartRoleFormGroupInput = { id: null }): SysDepartRoleFormGroup {
    const sysDepartRoleRawValue = {
      ...this.getFormDefaults(),
      ...sysDepartRole,
    };
    return new FormGroup<SysDepartRoleFormGroupContent>({
      id: new FormControl(
        { value: sysDepartRoleRawValue.id, disabled: sysDepartRoleRawValue.id !== null },
        {
          nonNullable: true,
          validators: [Validators.required],
        }
      ),
      departID: new FormControl(sysDepartRoleRawValue.departID),
      roleName: new FormControl(sysDepartRoleRawValue.roleName),
      roleCode: new FormControl(sysDepartRoleRawValue.roleCode),
      description: new FormControl(sysDepartRoleRawValue.description),
      createBy: new FormControl(sysDepartRoleRawValue.createBy),
      createTime: new FormControl(sysDepartRoleRawValue.createTime),
      updateBy: new FormControl(sysDepartRoleRawValue.updateBy),
      updateTime: new FormControl(sysDepartRoleRawValue.updateTime),
      tenantId: new FormControl(sysDepartRoleRawValue.tenantId),
    });
  }

  getSysDepartRole(form: SysDepartRoleFormGroup): ISysDepartRole | NewSysDepartRole {
    return form.getRawValue() as ISysDepartRole | NewSysDepartRole;
  }

  resetForm(form: SysDepartRoleFormGroup, sysDepartRole: SysDepartRoleFormGroupInput): void {
    const sysDepartRoleRawValue = { ...this.getFormDefaults(), ...sysDepartRole };
    form.reset(
      {
        ...sysDepartRoleRawValue,
        id: { value: sysDepartRoleRawValue.id, disabled: sysDepartRoleRawValue.id !== null },
      } as any /* cast to workaround https://github.com/angular/angular/issues/46458 */
    );
  }

  private getFormDefaults(): SysDepartRoleFormDefaults {
    return {
      id: null,
    };
  }
}
