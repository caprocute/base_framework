import { TestBed } from '@angular/core/testing';

import { sampleWithRequiredData, sampleWithNewData } from '../sys-role-permission.test-samples';

import { SysRolePermissionFormService } from './sys-role-permission-form.service';

describe('SysRolePermission Form Service', () => {
  let service: SysRolePermissionFormService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(SysRolePermissionFormService);
  });

  describe('Service methods', () => {
    describe('createSysRolePermissionFormGroup', () => {
      it('should create a new form with FormControl', () => {
        const formGroup = service.createSysRolePermissionFormGroup();

        expect(formGroup.controls).toEqual(
          expect.objectContaining({
            id: expect.any(Object),
            roleId: expect.any(Object),
            permissionId: expect.any(Object),
            dataRuleIds: expect.any(Object),
            operateDate: expect.any(Object),
            operateIp: expect.any(Object),
          })
        );
      });

      it('passing ISysRolePermission should create a new form with FormGroup', () => {
        const formGroup = service.createSysRolePermissionFormGroup(sampleWithRequiredData);

        expect(formGroup.controls).toEqual(
          expect.objectContaining({
            id: expect.any(Object),
            roleId: expect.any(Object),
            permissionId: expect.any(Object),
            dataRuleIds: expect.any(Object),
            operateDate: expect.any(Object),
            operateIp: expect.any(Object),
          })
        );
      });
    });

    describe('getSysRolePermission', () => {
      it('should return NewSysRolePermission for default SysRolePermission initial value', () => {
        // eslint-disable-next-line @typescript-eslint/no-unused-vars
        const formGroup = service.createSysRolePermissionFormGroup(sampleWithNewData);

        const sysRolePermission = service.getSysRolePermission(formGroup) as any;

        expect(sysRolePermission).toMatchObject(sampleWithNewData);
      });

      it('should return NewSysRolePermission for empty SysRolePermission initial value', () => {
        const formGroup = service.createSysRolePermissionFormGroup();

        const sysRolePermission = service.getSysRolePermission(formGroup) as any;

        expect(sysRolePermission).toMatchObject({});
      });

      it('should return ISysRolePermission', () => {
        const formGroup = service.createSysRolePermissionFormGroup(sampleWithRequiredData);

        const sysRolePermission = service.getSysRolePermission(formGroup) as any;

        expect(sysRolePermission).toMatchObject(sampleWithRequiredData);
      });
    });

    describe('resetForm', () => {
      it('passing ISysRolePermission should not enable id FormControl', () => {
        const formGroup = service.createSysRolePermissionFormGroup();
        expect(formGroup.controls.id.disabled).toBe(true);

        service.resetForm(formGroup, sampleWithRequiredData);

        expect(formGroup.controls.id.disabled).toBe(true);
      });

      it('passing NewSysRolePermission should disable id FormControl', () => {
        const formGroup = service.createSysRolePermissionFormGroup(sampleWithRequiredData);
        expect(formGroup.controls.id.disabled).toBe(true);

        service.resetForm(formGroup, { id: null });

        expect(formGroup.controls.id.disabled).toBe(true);
      });
    });
  });
});
