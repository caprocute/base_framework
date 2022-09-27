import { Injectable } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';

import { ISysPermissionDataRule, NewSysPermissionDataRule } from '../sys-permission-data-rule.model';

/**
 * A partial Type with required key is used as form input.
 */
type PartialWithRequiredKeyOf<T extends { id: unknown }> = Partial<Omit<T, 'id'>> & { id: T['id'] };

/**
 * Type for createFormGroup and resetForm argument.
 * It accepts ISysPermissionDataRule for edit and NewSysPermissionDataRuleFormGroupInput for create.
 */
type SysPermissionDataRuleFormGroupInput = ISysPermissionDataRule | PartialWithRequiredKeyOf<NewSysPermissionDataRule>;

type SysPermissionDataRuleFormDefaults = Pick<NewSysPermissionDataRule, 'id'>;

type SysPermissionDataRuleFormGroupContent = {
  id: FormControl<ISysPermissionDataRule['id'] | NewSysPermissionDataRule['id']>;
  permissionId: FormControl<ISysPermissionDataRule['permissionId']>;
  ruleName: FormControl<ISysPermissionDataRule['ruleName']>;
  ruleColumn: FormControl<ISysPermissionDataRule['ruleColumn']>;
  ruleCOnditions: FormControl<ISysPermissionDataRule['ruleCOnditions']>;
  ruleValue: FormControl<ISysPermissionDataRule['ruleValue']>;
  status: FormControl<ISysPermissionDataRule['status']>;
  createBy: FormControl<ISysPermissionDataRule['createBy']>;
  createTime: FormControl<ISysPermissionDataRule['createTime']>;
  updateBy: FormControl<ISysPermissionDataRule['updateBy']>;
  updateTime: FormControl<ISysPermissionDataRule['updateTime']>;
  tenantId: FormControl<ISysPermissionDataRule['tenantId']>;
};

export type SysPermissionDataRuleFormGroup = FormGroup<SysPermissionDataRuleFormGroupContent>;

@Injectable({ providedIn: 'root' })
export class SysPermissionDataRuleFormService {
  createSysPermissionDataRuleFormGroup(
    sysPermissionDataRule: SysPermissionDataRuleFormGroupInput = { id: null }
  ): SysPermissionDataRuleFormGroup {
    const sysPermissionDataRuleRawValue = {
      ...this.getFormDefaults(),
      ...sysPermissionDataRule,
    };
    return new FormGroup<SysPermissionDataRuleFormGroupContent>({
      id: new FormControl(
        { value: sysPermissionDataRuleRawValue.id, disabled: sysPermissionDataRuleRawValue.id !== null },
        {
          nonNullable: true,
          validators: [Validators.required],
        }
      ),
      permissionId: new FormControl(sysPermissionDataRuleRawValue.permissionId),
      ruleName: new FormControl(sysPermissionDataRuleRawValue.ruleName),
      ruleColumn: new FormControl(sysPermissionDataRuleRawValue.ruleColumn),
      ruleCOnditions: new FormControl(sysPermissionDataRuleRawValue.ruleCOnditions),
      ruleValue: new FormControl(sysPermissionDataRuleRawValue.ruleValue),
      status: new FormControl(sysPermissionDataRuleRawValue.status),
      createBy: new FormControl(sysPermissionDataRuleRawValue.createBy),
      createTime: new FormControl(sysPermissionDataRuleRawValue.createTime),
      updateBy: new FormControl(sysPermissionDataRuleRawValue.updateBy),
      updateTime: new FormControl(sysPermissionDataRuleRawValue.updateTime),
      tenantId: new FormControl(sysPermissionDataRuleRawValue.tenantId),
    });
  }

  getSysPermissionDataRule(form: SysPermissionDataRuleFormGroup): ISysPermissionDataRule | NewSysPermissionDataRule {
    return form.getRawValue() as ISysPermissionDataRule | NewSysPermissionDataRule;
  }

  resetForm(form: SysPermissionDataRuleFormGroup, sysPermissionDataRule: SysPermissionDataRuleFormGroupInput): void {
    const sysPermissionDataRuleRawValue = { ...this.getFormDefaults(), ...sysPermissionDataRule };
    form.reset(
      {
        ...sysPermissionDataRuleRawValue,
        id: { value: sysPermissionDataRuleRawValue.id, disabled: sysPermissionDataRuleRawValue.id !== null },
      } as any /* cast to workaround https://github.com/angular/angular/issues/46458 */
    );
  }

  private getFormDefaults(): SysPermissionDataRuleFormDefaults {
    return {
      id: null,
    };
  }
}
