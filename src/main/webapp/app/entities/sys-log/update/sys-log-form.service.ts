import { Injectable } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';

import { ISysLog, NewSysLog } from '../sys-log.model';

/**
 * A partial Type with required key is used as form input.
 */
type PartialWithRequiredKeyOf<T extends { id: unknown }> = Partial<Omit<T, 'id'>> & { id: T['id'] };

/**
 * Type for createFormGroup and resetForm argument.
 * It accepts ISysLog for edit and NewSysLogFormGroupInput for create.
 */
type SysLogFormGroupInput = ISysLog | PartialWithRequiredKeyOf<NewSysLog>;

type SysLogFormDefaults = Pick<NewSysLog, 'id'>;

type SysLogFormGroupContent = {
  id: FormControl<ISysLog['id'] | NewSysLog['id']>;
  logType: FormControl<ISysLog['logType']>;
  logContent: FormControl<ISysLog['logContent']>;
  operateType: FormControl<ISysLog['operateType']>;
  userName: FormControl<ISysLog['userName']>;
  ip: FormControl<ISysLog['ip']>;
  method: FormControl<ISysLog['method']>;
  requestUrl: FormControl<ISysLog['requestUrl']>;
  requestParam: FormControl<ISysLog['requestParam']>;
  requestType: FormControl<ISysLog['requestType']>;
  costTime: FormControl<ISysLog['costTime']>;
  createBy: FormControl<ISysLog['createBy']>;
  createTime: FormControl<ISysLog['createTime']>;
  updateBy: FormControl<ISysLog['updateBy']>;
  updateTime: FormControl<ISysLog['updateTime']>;
  tenantId: FormControl<ISysLog['tenantId']>;
};

export type SysLogFormGroup = FormGroup<SysLogFormGroupContent>;

@Injectable({ providedIn: 'root' })
export class SysLogFormService {
  createSysLogFormGroup(sysLog: SysLogFormGroupInput = { id: null }): SysLogFormGroup {
    const sysLogRawValue = {
      ...this.getFormDefaults(),
      ...sysLog,
    };
    return new FormGroup<SysLogFormGroupContent>({
      id: new FormControl(
        { value: sysLogRawValue.id, disabled: sysLogRawValue.id !== null },
        {
          nonNullable: true,
          validators: [Validators.required],
        }
      ),
      logType: new FormControl(sysLogRawValue.logType),
      logContent: new FormControl(sysLogRawValue.logContent),
      operateType: new FormControl(sysLogRawValue.operateType),
      userName: new FormControl(sysLogRawValue.userName),
      ip: new FormControl(sysLogRawValue.ip),
      method: new FormControl(sysLogRawValue.method),
      requestUrl: new FormControl(sysLogRawValue.requestUrl),
      requestParam: new FormControl(sysLogRawValue.requestParam),
      requestType: new FormControl(sysLogRawValue.requestType),
      costTime: new FormControl(sysLogRawValue.costTime),
      createBy: new FormControl(sysLogRawValue.createBy),
      createTime: new FormControl(sysLogRawValue.createTime),
      updateBy: new FormControl(sysLogRawValue.updateBy),
      updateTime: new FormControl(sysLogRawValue.updateTime),
      tenantId: new FormControl(sysLogRawValue.tenantId),
    });
  }

  getSysLog(form: SysLogFormGroup): ISysLog | NewSysLog {
    return form.getRawValue() as ISysLog | NewSysLog;
  }

  resetForm(form: SysLogFormGroup, sysLog: SysLogFormGroupInput): void {
    const sysLogRawValue = { ...this.getFormDefaults(), ...sysLog };
    form.reset(
      {
        ...sysLogRawValue,
        id: { value: sysLogRawValue.id, disabled: sysLogRawValue.id !== null },
      } as any /* cast to workaround https://github.com/angular/angular/issues/46458 */
    );
  }

  private getFormDefaults(): SysLogFormDefaults {
    return {
      id: null,
    };
  }
}
