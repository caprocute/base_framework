import { Injectable } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';

import { ISysPermission, NewSysPermission } from '../sys-permission.model';

/**
 * A partial Type with required key is used as form input.
 */
type PartialWithRequiredKeyOf<T extends { id: unknown }> = Partial<Omit<T, 'id'>> & { id: T['id'] };

/**
 * Type for createFormGroup and resetForm argument.
 * It accepts ISysPermission for edit and NewSysPermissionFormGroupInput for create.
 */
type SysPermissionFormGroupInput = ISysPermission | PartialWithRequiredKeyOf<NewSysPermission>;

type SysPermissionFormDefaults = Pick<
  NewSysPermission,
  'id' | 'isRoute' | 'alwaysShow' | 'isLeaf' | 'keepAlive' | 'hidden' | 'hideTab' | 'delFlag' | 'ruleFLag' | 'internalOrExternal'
>;

type SysPermissionFormGroupContent = {
  id: FormControl<ISysPermission['id'] | NewSysPermission['id']>;
  parentId: FormControl<ISysPermission['parentId']>;
  name: FormControl<ISysPermission['name']>;
  url: FormControl<ISysPermission['url']>;
  component: FormControl<ISysPermission['component']>;
  isRoute: FormControl<ISysPermission['isRoute']>;
  componentName: FormControl<ISysPermission['componentName']>;
  redirect: FormControl<ISysPermission['redirect']>;
  menuType: FormControl<ISysPermission['menuType']>;
  perms: FormControl<ISysPermission['perms']>;
  permsType: FormControl<ISysPermission['permsType']>;
  sortNo: FormControl<ISysPermission['sortNo']>;
  alwaysShow: FormControl<ISysPermission['alwaysShow']>;
  icon: FormControl<ISysPermission['icon']>;
  isLeaf: FormControl<ISysPermission['isLeaf']>;
  keepAlive: FormControl<ISysPermission['keepAlive']>;
  hidden: FormControl<ISysPermission['hidden']>;
  hideTab: FormControl<ISysPermission['hideTab']>;
  description: FormControl<ISysPermission['description']>;
  delFlag: FormControl<ISysPermission['delFlag']>;
  ruleFLag: FormControl<ISysPermission['ruleFLag']>;
  createBy: FormControl<ISysPermission['createBy']>;
  createTime: FormControl<ISysPermission['createTime']>;
  updateBy: FormControl<ISysPermission['updateBy']>;
  updateTime: FormControl<ISysPermission['updateTime']>;
  tenantId: FormControl<ISysPermission['tenantId']>;
  internalOrExternal: FormControl<ISysPermission['internalOrExternal']>;
};

export type SysPermissionFormGroup = FormGroup<SysPermissionFormGroupContent>;

@Injectable({ providedIn: 'root' })
export class SysPermissionFormService {
  createSysPermissionFormGroup(sysPermission: SysPermissionFormGroupInput = { id: null }): SysPermissionFormGroup {
    const sysPermissionRawValue = {
      ...this.getFormDefaults(),
      ...sysPermission,
    };
    return new FormGroup<SysPermissionFormGroupContent>({
      id: new FormControl(
        { value: sysPermissionRawValue.id, disabled: sysPermissionRawValue.id !== null },
        {
          nonNullable: true,
          validators: [Validators.required],
        }
      ),
      parentId: new FormControl(sysPermissionRawValue.parentId),
      name: new FormControl(sysPermissionRawValue.name),
      url: new FormControl(sysPermissionRawValue.url),
      component: new FormControl(sysPermissionRawValue.component),
      isRoute: new FormControl(sysPermissionRawValue.isRoute),
      componentName: new FormControl(sysPermissionRawValue.componentName),
      redirect: new FormControl(sysPermissionRawValue.redirect),
      menuType: new FormControl(sysPermissionRawValue.menuType),
      perms: new FormControl(sysPermissionRawValue.perms),
      permsType: new FormControl(sysPermissionRawValue.permsType),
      sortNo: new FormControl(sysPermissionRawValue.sortNo),
      alwaysShow: new FormControl(sysPermissionRawValue.alwaysShow),
      icon: new FormControl(sysPermissionRawValue.icon),
      isLeaf: new FormControl(sysPermissionRawValue.isLeaf),
      keepAlive: new FormControl(sysPermissionRawValue.keepAlive),
      hidden: new FormControl(sysPermissionRawValue.hidden),
      hideTab: new FormControl(sysPermissionRawValue.hideTab),
      description: new FormControl(sysPermissionRawValue.description),
      delFlag: new FormControl(sysPermissionRawValue.delFlag),
      ruleFLag: new FormControl(sysPermissionRawValue.ruleFLag),
      createBy: new FormControl(sysPermissionRawValue.createBy),
      createTime: new FormControl(sysPermissionRawValue.createTime),
      updateBy: new FormControl(sysPermissionRawValue.updateBy),
      updateTime: new FormControl(sysPermissionRawValue.updateTime),
      tenantId: new FormControl(sysPermissionRawValue.tenantId),
      internalOrExternal: new FormControl(sysPermissionRawValue.internalOrExternal),
    });
  }

  getSysPermission(form: SysPermissionFormGroup): ISysPermission | NewSysPermission {
    return form.getRawValue() as ISysPermission | NewSysPermission;
  }

  resetForm(form: SysPermissionFormGroup, sysPermission: SysPermissionFormGroupInput): void {
    const sysPermissionRawValue = { ...this.getFormDefaults(), ...sysPermission };
    form.reset(
      {
        ...sysPermissionRawValue,
        id: { value: sysPermissionRawValue.id, disabled: sysPermissionRawValue.id !== null },
      } as any /* cast to workaround https://github.com/angular/angular/issues/46458 */
    );
  }

  private getFormDefaults(): SysPermissionFormDefaults {
    return {
      id: null,
      isRoute: false,
      alwaysShow: false,
      isLeaf: false,
      keepAlive: false,
      hidden: false,
      hideTab: false,
      delFlag: false,
      ruleFLag: false,
      internalOrExternal: false,
    };
  }
}
