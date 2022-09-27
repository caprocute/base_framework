import { Injectable } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';

import { ISysDepart, NewSysDepart } from '../sys-depart.model';

/**
 * A partial Type with required key is used as form input.
 */
type PartialWithRequiredKeyOf<T extends { id: unknown }> = Partial<Omit<T, 'id'>> & { id: T['id'] };

/**
 * Type for createFormGroup and resetForm argument.
 * It accepts ISysDepart for edit and NewSysDepartFormGroupInput for create.
 */
type SysDepartFormGroupInput = ISysDepart | PartialWithRequiredKeyOf<NewSysDepart>;

type SysDepartFormDefaults = Pick<NewSysDepart, 'id' | 'status' | 'delFlag'>;

type SysDepartFormGroupContent = {
  id: FormControl<ISysDepart['id'] | NewSysDepart['id']>;
  parentId: FormControl<ISysDepart['parentId']>;
  departName: FormControl<ISysDepart['departName']>;
  departOrder: FormControl<ISysDepart['departOrder']>;
  orgCategory: FormControl<ISysDepart['orgCategory']>;
  orgType: FormControl<ISysDepart['orgType']>;
  orgCode: FormControl<ISysDepart['orgCode']>;
  mobile: FormControl<ISysDepart['mobile']>;
  fax: FormControl<ISysDepart['fax']>;
  address: FormControl<ISysDepart['address']>;
  memo: FormControl<ISysDepart['memo']>;
  status: FormControl<ISysDepart['status']>;
  delFlag: FormControl<ISysDepart['delFlag']>;
  createBy: FormControl<ISysDepart['createBy']>;
  createTime: FormControl<ISysDepart['createTime']>;
  updateBy: FormControl<ISysDepart['updateBy']>;
  updateTime: FormControl<ISysDepart['updateTime']>;
  tenantId: FormControl<ISysDepart['tenantId']>;
};

export type SysDepartFormGroup = FormGroup<SysDepartFormGroupContent>;

@Injectable({ providedIn: 'root' })
export class SysDepartFormService {
  createSysDepartFormGroup(sysDepart: SysDepartFormGroupInput = { id: null }): SysDepartFormGroup {
    const sysDepartRawValue = {
      ...this.getFormDefaults(),
      ...sysDepart,
    };
    return new FormGroup<SysDepartFormGroupContent>({
      id: new FormControl(
        { value: sysDepartRawValue.id, disabled: sysDepartRawValue.id !== null },
        {
          nonNullable: true,
          validators: [Validators.required],
        }
      ),
      parentId: new FormControl(sysDepartRawValue.parentId),
      departName: new FormControl(sysDepartRawValue.departName, {
        validators: [Validators.required],
      }),
      departOrder: new FormControl(sysDepartRawValue.departOrder),
      orgCategory: new FormControl(sysDepartRawValue.orgCategory, {
        validators: [Validators.required],
      }),
      orgType: new FormControl(sysDepartRawValue.orgType),
      orgCode: new FormControl(sysDepartRawValue.orgCode, {
        validators: [Validators.required],
      }),
      mobile: new FormControl(sysDepartRawValue.mobile),
      fax: new FormControl(sysDepartRawValue.fax),
      address: new FormControl(sysDepartRawValue.address),
      memo: new FormControl(sysDepartRawValue.memo),
      status: new FormControl(sysDepartRawValue.status),
      delFlag: new FormControl(sysDepartRawValue.delFlag),
      createBy: new FormControl(sysDepartRawValue.createBy),
      createTime: new FormControl(sysDepartRawValue.createTime),
      updateBy: new FormControl(sysDepartRawValue.updateBy),
      updateTime: new FormControl(sysDepartRawValue.updateTime),
      tenantId: new FormControl(sysDepartRawValue.tenantId),
    });
  }

  getSysDepart(form: SysDepartFormGroup): ISysDepart | NewSysDepart {
    return form.getRawValue() as ISysDepart | NewSysDepart;
  }

  resetForm(form: SysDepartFormGroup, sysDepart: SysDepartFormGroupInput): void {
    const sysDepartRawValue = { ...this.getFormDefaults(), ...sysDepart };
    form.reset(
      {
        ...sysDepartRawValue,
        id: { value: sysDepartRawValue.id, disabled: sysDepartRawValue.id !== null },
      } as any /* cast to workaround https://github.com/angular/angular/issues/46458 */
    );
  }

  private getFormDefaults(): SysDepartFormDefaults {
    return {
      id: null,
      status: false,
      delFlag: false,
    };
  }
}
