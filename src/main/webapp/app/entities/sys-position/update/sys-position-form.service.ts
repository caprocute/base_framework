import { Injectable } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';

import { ISysPosition, NewSysPosition } from '../sys-position.model';

/**
 * A partial Type with required key is used as form input.
 */
type PartialWithRequiredKeyOf<T extends { id: unknown }> = Partial<Omit<T, 'id'>> & { id: T['id'] };

/**
 * Type for createFormGroup and resetForm argument.
 * It accepts ISysPosition for edit and NewSysPositionFormGroupInput for create.
 */
type SysPositionFormGroupInput = ISysPosition | PartialWithRequiredKeyOf<NewSysPosition>;

type SysPositionFormDefaults = Pick<NewSysPosition, 'id'>;

type SysPositionFormGroupContent = {
  id: FormControl<ISysPosition['id'] | NewSysPosition['id']>;
  code: FormControl<ISysPosition['code']>;
  name: FormControl<ISysPosition['name']>;
  postRank: FormControl<ISysPosition['postRank']>;
  companyId: FormControl<ISysPosition['companyId']>;
  sysOrgCode: FormControl<ISysPosition['sysOrgCode']>;
  createBy: FormControl<ISysPosition['createBy']>;
  createTime: FormControl<ISysPosition['createTime']>;
  updateBy: FormControl<ISysPosition['updateBy']>;
  updateTime: FormControl<ISysPosition['updateTime']>;
  tenantId: FormControl<ISysPosition['tenantId']>;
};

export type SysPositionFormGroup = FormGroup<SysPositionFormGroupContent>;

@Injectable({ providedIn: 'root' })
export class SysPositionFormService {
  createSysPositionFormGroup(sysPosition: SysPositionFormGroupInput = { id: null }): SysPositionFormGroup {
    const sysPositionRawValue = {
      ...this.getFormDefaults(),
      ...sysPosition,
    };
    return new FormGroup<SysPositionFormGroupContent>({
      id: new FormControl(
        { value: sysPositionRawValue.id, disabled: sysPositionRawValue.id !== null },
        {
          nonNullable: true,
          validators: [Validators.required],
        }
      ),
      code: new FormControl(sysPositionRawValue.code),
      name: new FormControl(sysPositionRawValue.name),
      postRank: new FormControl(sysPositionRawValue.postRank),
      companyId: new FormControl(sysPositionRawValue.companyId),
      sysOrgCode: new FormControl(sysPositionRawValue.sysOrgCode),
      createBy: new FormControl(sysPositionRawValue.createBy),
      createTime: new FormControl(sysPositionRawValue.createTime),
      updateBy: new FormControl(sysPositionRawValue.updateBy),
      updateTime: new FormControl(sysPositionRawValue.updateTime),
      tenantId: new FormControl(sysPositionRawValue.tenantId),
    });
  }

  getSysPosition(form: SysPositionFormGroup): ISysPosition | NewSysPosition {
    return form.getRawValue() as ISysPosition | NewSysPosition;
  }

  resetForm(form: SysPositionFormGroup, sysPosition: SysPositionFormGroupInput): void {
    const sysPositionRawValue = { ...this.getFormDefaults(), ...sysPosition };
    form.reset(
      {
        ...sysPositionRawValue,
        id: { value: sysPositionRawValue.id, disabled: sysPositionRawValue.id !== null },
      } as any /* cast to workaround https://github.com/angular/angular/issues/46458 */
    );
  }

  private getFormDefaults(): SysPositionFormDefaults {
    return {
      id: null,
    };
  }
}
