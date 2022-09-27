import { Injectable } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';

import { ISysDataSource, NewSysDataSource } from '../sys-data-source.model';

/**
 * A partial Type with required key is used as form input.
 */
type PartialWithRequiredKeyOf<T extends { id: unknown }> = Partial<Omit<T, 'id'>> & { id: T['id'] };

/**
 * Type for createFormGroup and resetForm argument.
 * It accepts ISysDataSource for edit and NewSysDataSourceFormGroupInput for create.
 */
type SysDataSourceFormGroupInput = ISysDataSource | PartialWithRequiredKeyOf<NewSysDataSource>;

type SysDataSourceFormDefaults = Pick<NewSysDataSource, 'id'>;

type SysDataSourceFormGroupContent = {
  id: FormControl<ISysDataSource['id'] | NewSysDataSource['id']>;
  name: FormControl<ISysDataSource['name']>;
  remark: FormControl<ISysDataSource['remark']>;
  dbType: FormControl<ISysDataSource['dbType']>;
  dbDriver: FormControl<ISysDataSource['dbDriver']>;
  dbUrl: FormControl<ISysDataSource['dbUrl']>;
  dbName: FormControl<ISysDataSource['dbName']>;
  dbUserName: FormControl<ISysDataSource['dbUserName']>;
  dbDrowssap: FormControl<ISysDataSource['dbDrowssap']>;
  createBy: FormControl<ISysDataSource['createBy']>;
  createTime: FormControl<ISysDataSource['createTime']>;
  updateBy: FormControl<ISysDataSource['updateBy']>;
  updateTime: FormControl<ISysDataSource['updateTime']>;
  tenantId: FormControl<ISysDataSource['tenantId']>;
};

export type SysDataSourceFormGroup = FormGroup<SysDataSourceFormGroupContent>;

@Injectable({ providedIn: 'root' })
export class SysDataSourceFormService {
  createSysDataSourceFormGroup(sysDataSource: SysDataSourceFormGroupInput = { id: null }): SysDataSourceFormGroup {
    const sysDataSourceRawValue = {
      ...this.getFormDefaults(),
      ...sysDataSource,
    };
    return new FormGroup<SysDataSourceFormGroupContent>({
      id: new FormControl(
        { value: sysDataSourceRawValue.id, disabled: sysDataSourceRawValue.id !== null },
        {
          nonNullable: true,
          validators: [Validators.required],
        }
      ),
      name: new FormControl(sysDataSourceRawValue.name),
      remark: new FormControl(sysDataSourceRawValue.remark),
      dbType: new FormControl(sysDataSourceRawValue.dbType),
      dbDriver: new FormControl(sysDataSourceRawValue.dbDriver),
      dbUrl: new FormControl(sysDataSourceRawValue.dbUrl),
      dbName: new FormControl(sysDataSourceRawValue.dbName),
      dbUserName: new FormControl(sysDataSourceRawValue.dbUserName),
      dbDrowssap: new FormControl(sysDataSourceRawValue.dbDrowssap),
      createBy: new FormControl(sysDataSourceRawValue.createBy),
      createTime: new FormControl(sysDataSourceRawValue.createTime),
      updateBy: new FormControl(sysDataSourceRawValue.updateBy),
      updateTime: new FormControl(sysDataSourceRawValue.updateTime),
      tenantId: new FormControl(sysDataSourceRawValue.tenantId),
    });
  }

  getSysDataSource(form: SysDataSourceFormGroup): ISysDataSource | NewSysDataSource {
    return form.getRawValue() as ISysDataSource | NewSysDataSource;
  }

  resetForm(form: SysDataSourceFormGroup, sysDataSource: SysDataSourceFormGroupInput): void {
    const sysDataSourceRawValue = { ...this.getFormDefaults(), ...sysDataSource };
    form.reset(
      {
        ...sysDataSourceRawValue,
        id: { value: sysDataSourceRawValue.id, disabled: sysDataSourceRawValue.id !== null },
      } as any /* cast to workaround https://github.com/angular/angular/issues/46458 */
    );
  }

  private getFormDefaults(): SysDataSourceFormDefaults {
    return {
      id: null,
    };
  }
}
