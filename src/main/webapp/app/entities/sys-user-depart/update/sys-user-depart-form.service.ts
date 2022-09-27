import { Injectable } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';

import { ISysUserDepart, NewSysUserDepart } from '../sys-user-depart.model';

/**
 * A partial Type with required key is used as form input.
 */
type PartialWithRequiredKeyOf<T extends { id: unknown }> = Partial<Omit<T, 'id'>> & { id: T['id'] };

/**
 * Type for createFormGroup and resetForm argument.
 * It accepts ISysUserDepart for edit and NewSysUserDepartFormGroupInput for create.
 */
type SysUserDepartFormGroupInput = ISysUserDepart | PartialWithRequiredKeyOf<NewSysUserDepart>;

type SysUserDepartFormDefaults = Pick<NewSysUserDepart, 'id'>;

type SysUserDepartFormGroupContent = {
  id: FormControl<ISysUserDepart['id'] | NewSysUserDepart['id']>;
  userId: FormControl<ISysUserDepart['userId']>;
  depID: FormControl<ISysUserDepart['depID']>;
};

export type SysUserDepartFormGroup = FormGroup<SysUserDepartFormGroupContent>;

@Injectable({ providedIn: 'root' })
export class SysUserDepartFormService {
  createSysUserDepartFormGroup(sysUserDepart: SysUserDepartFormGroupInput = { id: null }): SysUserDepartFormGroup {
    const sysUserDepartRawValue = {
      ...this.getFormDefaults(),
      ...sysUserDepart,
    };
    return new FormGroup<SysUserDepartFormGroupContent>({
      id: new FormControl(
        { value: sysUserDepartRawValue.id, disabled: sysUserDepartRawValue.id !== null },
        {
          nonNullable: true,
          validators: [Validators.required],
        }
      ),
      userId: new FormControl(sysUserDepartRawValue.userId),
      depID: new FormControl(sysUserDepartRawValue.depID),
    });
  }

  getSysUserDepart(form: SysUserDepartFormGroup): ISysUserDepart | NewSysUserDepart {
    return form.getRawValue() as ISysUserDepart | NewSysUserDepart;
  }

  resetForm(form: SysUserDepartFormGroup, sysUserDepart: SysUserDepartFormGroupInput): void {
    const sysUserDepartRawValue = { ...this.getFormDefaults(), ...sysUserDepart };
    form.reset(
      {
        ...sysUserDepartRawValue,
        id: { value: sysUserDepartRawValue.id, disabled: sysUserDepartRawValue.id !== null },
      } as any /* cast to workaround https://github.com/angular/angular/issues/46458 */
    );
  }

  private getFormDefaults(): SysUserDepartFormDefaults {
    return {
      id: null,
    };
  }
}
