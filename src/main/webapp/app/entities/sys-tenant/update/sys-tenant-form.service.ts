import { Injectable } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';

import { ISysTenant, NewSysTenant } from '../sys-tenant.model';

/**
 * A partial Type with required key is used as form input.
 */
type PartialWithRequiredKeyOf<T extends { id: unknown }> = Partial<Omit<T, 'id'>> & { id: T['id'] };

/**
 * Type for createFormGroup and resetForm argument.
 * It accepts ISysTenant for edit and NewSysTenantFormGroupInput for create.
 */
type SysTenantFormGroupInput = ISysTenant | PartialWithRequiredKeyOf<NewSysTenant>;

type SysTenantFormDefaults = Pick<NewSysTenant, 'id'>;

type SysTenantFormGroupContent = {
  id: FormControl<ISysTenant['id'] | NewSysTenant['id']>;
  name: FormControl<ISysTenant['name']>;
  status: FormControl<ISysTenant['status']>;
  createBy: FormControl<ISysTenant['createBy']>;
  createTime: FormControl<ISysTenant['createTime']>;
  updateBy: FormControl<ISysTenant['updateBy']>;
  updateTime: FormControl<ISysTenant['updateTime']>;
  tenantId: FormControl<ISysTenant['tenantId']>;
};

export type SysTenantFormGroup = FormGroup<SysTenantFormGroupContent>;

@Injectable({ providedIn: 'root' })
export class SysTenantFormService {
  createSysTenantFormGroup(sysTenant: SysTenantFormGroupInput = { id: null }): SysTenantFormGroup {
    const sysTenantRawValue = {
      ...this.getFormDefaults(),
      ...sysTenant,
    };
    return new FormGroup<SysTenantFormGroupContent>({
      id: new FormControl(
        { value: sysTenantRawValue.id, disabled: sysTenantRawValue.id !== null },
        {
          nonNullable: true,
          validators: [Validators.required],
        }
      ),
      name: new FormControl(sysTenantRawValue.name),
      status: new FormControl(sysTenantRawValue.status),
      createBy: new FormControl(sysTenantRawValue.createBy),
      createTime: new FormControl(sysTenantRawValue.createTime),
      updateBy: new FormControl(sysTenantRawValue.updateBy),
      updateTime: new FormControl(sysTenantRawValue.updateTime),
      tenantId: new FormControl(sysTenantRawValue.tenantId),
    });
  }

  getSysTenant(form: SysTenantFormGroup): ISysTenant | NewSysTenant {
    return form.getRawValue() as ISysTenant | NewSysTenant;
  }

  resetForm(form: SysTenantFormGroup, sysTenant: SysTenantFormGroupInput): void {
    const sysTenantRawValue = { ...this.getFormDefaults(), ...sysTenant };
    form.reset(
      {
        ...sysTenantRawValue,
        id: { value: sysTenantRawValue.id, disabled: sysTenantRawValue.id !== null },
      } as any /* cast to workaround https://github.com/angular/angular/issues/46458 */
    );
  }

  private getFormDefaults(): SysTenantFormDefaults {
    return {
      id: null,
    };
  }
}
