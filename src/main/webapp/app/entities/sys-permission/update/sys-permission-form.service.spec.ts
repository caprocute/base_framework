import { TestBed } from '@angular/core/testing';

import { sampleWithRequiredData, sampleWithNewData } from '../sys-permission.test-samples';

import { SysPermissionFormService } from './sys-permission-form.service';

describe('SysPermission Form Service', () => {
  let service: SysPermissionFormService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(SysPermissionFormService);
  });

  describe('Service methods', () => {
    describe('createSysPermissionFormGroup', () => {
      it('should create a new form with FormControl', () => {
        const formGroup = service.createSysPermissionFormGroup();

        expect(formGroup.controls).toEqual(
          expect.objectContaining({
            id: expect.any(Object),
            parentId: expect.any(Object),
            name: expect.any(Object),
            url: expect.any(Object),
            component: expect.any(Object),
            isRoute: expect.any(Object),
            componentName: expect.any(Object),
            redirect: expect.any(Object),
            menuType: expect.any(Object),
            perms: expect.any(Object),
            permsType: expect.any(Object),
            sortNo: expect.any(Object),
            alwaysShow: expect.any(Object),
            icon: expect.any(Object),
            isLeaf: expect.any(Object),
            keepAlive: expect.any(Object),
            hidden: expect.any(Object),
            hideTab: expect.any(Object),
            description: expect.any(Object),
            delFlag: expect.any(Object),
            ruleFLag: expect.any(Object),
            createBy: expect.any(Object),
            createTime: expect.any(Object),
            updateBy: expect.any(Object),
            updateTime: expect.any(Object),
            tenantId: expect.any(Object),
            internalOrExternal: expect.any(Object),
          })
        );
      });

      it('passing ISysPermission should create a new form with FormGroup', () => {
        const formGroup = service.createSysPermissionFormGroup(sampleWithRequiredData);

        expect(formGroup.controls).toEqual(
          expect.objectContaining({
            id: expect.any(Object),
            parentId: expect.any(Object),
            name: expect.any(Object),
            url: expect.any(Object),
            component: expect.any(Object),
            isRoute: expect.any(Object),
            componentName: expect.any(Object),
            redirect: expect.any(Object),
            menuType: expect.any(Object),
            perms: expect.any(Object),
            permsType: expect.any(Object),
            sortNo: expect.any(Object),
            alwaysShow: expect.any(Object),
            icon: expect.any(Object),
            isLeaf: expect.any(Object),
            keepAlive: expect.any(Object),
            hidden: expect.any(Object),
            hideTab: expect.any(Object),
            description: expect.any(Object),
            delFlag: expect.any(Object),
            ruleFLag: expect.any(Object),
            createBy: expect.any(Object),
            createTime: expect.any(Object),
            updateBy: expect.any(Object),
            updateTime: expect.any(Object),
            tenantId: expect.any(Object),
            internalOrExternal: expect.any(Object),
          })
        );
      });
    });

    describe('getSysPermission', () => {
      it('should return NewSysPermission for default SysPermission initial value', () => {
        // eslint-disable-next-line @typescript-eslint/no-unused-vars
        const formGroup = service.createSysPermissionFormGroup(sampleWithNewData);

        const sysPermission = service.getSysPermission(formGroup) as any;

        expect(sysPermission).toMatchObject(sampleWithNewData);
      });

      it('should return NewSysPermission for empty SysPermission initial value', () => {
        const formGroup = service.createSysPermissionFormGroup();

        const sysPermission = service.getSysPermission(formGroup) as any;

        expect(sysPermission).toMatchObject({});
      });

      it('should return ISysPermission', () => {
        const formGroup = service.createSysPermissionFormGroup(sampleWithRequiredData);

        const sysPermission = service.getSysPermission(formGroup) as any;

        expect(sysPermission).toMatchObject(sampleWithRequiredData);
      });
    });

    describe('resetForm', () => {
      it('passing ISysPermission should not enable id FormControl', () => {
        const formGroup = service.createSysPermissionFormGroup();
        expect(formGroup.controls.id.disabled).toBe(true);

        service.resetForm(formGroup, sampleWithRequiredData);

        expect(formGroup.controls.id.disabled).toBe(true);
      });

      it('passing NewSysPermission should disable id FormControl', () => {
        const formGroup = service.createSysPermissionFormGroup(sampleWithRequiredData);
        expect(formGroup.controls.id.disabled).toBe(true);

        service.resetForm(formGroup, { id: null });

        expect(formGroup.controls.id.disabled).toBe(true);
      });
    });
  });
});
