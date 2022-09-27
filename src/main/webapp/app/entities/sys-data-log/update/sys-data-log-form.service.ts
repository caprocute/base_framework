import { Injectable } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';

import { ISysDataLog, NewSysDataLog } from '../sys-data-log.model';

/**
 * A partial Type with required key is used as form input.
 */
type PartialWithRequiredKeyOf<T extends { id: unknown }> = Partial<Omit<T, 'id'>> & { id: T['id'] };

/**
 * Type for createFormGroup and resetForm argument.
 * It accepts ISysDataLog for edit and NewSysDataLogFormGroupInput for create.
 */
type SysDataLogFormGroupInput = ISysDataLog | PartialWithRequiredKeyOf<NewSysDataLog>;

type SysDataLogFormDefaults = Pick<NewSysDataLog, 'id'>;

type SysDataLogFormGroupContent = {
  id: FormControl<ISysDataLog['id'] | NewSysDataLog['id']>;
  dataTable: FormControl<ISysDataLog['dataTable']>;
  dataId: FormControl<ISysDataLog['dataId']>;
  dataContent: FormControl<ISysDataLog['dataContent']>;
  dataVersion: FormControl<ISysDataLog['dataVersion']>;
  createBy: FormControl<ISysDataLog['createBy']>;
  createTime: FormControl<ISysDataLog['createTime']>;
  updateBy: FormControl<ISysDataLog['updateBy']>;
  updateTime: FormControl<ISysDataLog['updateTime']>;
  tenantId: FormControl<ISysDataLog['tenantId']>;
};

export type SysDataLogFormGroup = FormGroup<SysDataLogFormGroupContent>;

@Injectable({ providedIn: 'root' })
export class SysDataLogFormService {
  createSysDataLogFormGroup(sysDataLog: SysDataLogFormGroupInput = { id: null }): SysDataLogFormGroup {
    const sysDataLogRawValue = {
      ...this.getFormDefaults(),
      ...sysDataLog,
    };
    return new FormGroup<SysDataLogFormGroupContent>({
      id: new FormControl(
        { value: sysDataLogRawValue.id, disabled: sysDataLogRawValue.id !== null },
        {
          nonNullable: true,
          validators: [Validators.required],
        }
      ),
      dataTable: new FormControl(sysDataLogRawValue.dataTable),
      dataId: new FormControl(sysDataLogRawValue.dataId),
      dataContent: new FormControl(sysDataLogRawValue.dataContent),
      dataVersion: new FormControl(sysDataLogRawValue.dataVersion),
      createBy: new FormControl(sysDataLogRawValue.createBy),
      createTime: new FormControl(sysDataLogRawValue.createTime),
      updateBy: new FormControl(sysDataLogRawValue.updateBy),
      updateTime: new FormControl(sysDataLogRawValue.updateTime),
      tenantId: new FormControl(sysDataLogRawValue.tenantId),
    });
  }

  getSysDataLog(form: SysDataLogFormGroup): ISysDataLog | NewSysDataLog {
    return form.getRawValue() as ISysDataLog | NewSysDataLog;
  }

  resetForm(form: SysDataLogFormGroup, sysDataLog: SysDataLogFormGroupInput): void {
    const sysDataLogRawValue = { ...this.getFormDefaults(), ...sysDataLog };
    form.reset(
      {
        ...sysDataLogRawValue,
        id: { value: sysDataLogRawValue.id, disabled: sysDataLogRawValue.id !== null },
      } as any /* cast to workaround https://github.com/angular/angular/issues/46458 */
    );
  }

  private getFormDefaults(): SysDataLogFormDefaults {
    return {
      id: null,
    };
  }
}
