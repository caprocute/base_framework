import { TestBed } from '@angular/core/testing';

import { sampleWithRequiredData, sampleWithNewData } from '../sys-user-role.test-samples';

import { SysUserRoleFormService } from './sys-user-role-form.service';

describe('SysUserRole Form Service', () => {
  let service: SysUserRoleFormService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(SysUserRoleFormService);
  });

  describe('Service methods', () => {
    describe('createSysUserRoleFormGroup', () => {
      it('should create a new form with FormControl', () => {
        const formGroup = service.createSysUserRoleFormGroup();

        expect(formGroup.controls).toEqual(
          expect.objectContaining({
            id: expect.any(Object),
            userId: expect.any(Object),
            roleId: expect.any(Object),
          })
        );
      });

      it('passing ISysUserRole should create a new form with FormGroup', () => {
        const formGroup = service.createSysUserRoleFormGroup(sampleWithRequiredData);

        expect(formGroup.controls).toEqual(
          expect.objectContaining({
            id: expect.any(Object),
            userId: expect.any(Object),
            roleId: expect.any(Object),
          })
        );
      });
    });

    describe('getSysUserRole', () => {
      it('should return NewSysUserRole for default SysUserRole initial value', () => {
        // eslint-disable-next-line @typescript-eslint/no-unused-vars
        const formGroup = service.createSysUserRoleFormGroup(sampleWithNewData);

        const sysUserRole = service.getSysUserRole(formGroup) as any;

        expect(sysUserRole).toMatchObject(sampleWithNewData);
      });

      it('should return NewSysUserRole for empty SysUserRole initial value', () => {
        const formGroup = service.createSysUserRoleFormGroup();

        const sysUserRole = service.getSysUserRole(formGroup) as any;

        expect(sysUserRole).toMatchObject({});
      });

      it('should return ISysUserRole', () => {
        const formGroup = service.createSysUserRoleFormGroup(sampleWithRequiredData);

        const sysUserRole = service.getSysUserRole(formGroup) as any;

        expect(sysUserRole).toMatchObject(sampleWithRequiredData);
      });
    });

    describe('resetForm', () => {
      it('passing ISysUserRole should not enable id FormControl', () => {
        const formGroup = service.createSysUserRoleFormGroup();
        expect(formGroup.controls.id.disabled).toBe(true);

        service.resetForm(formGroup, sampleWithRequiredData);

        expect(formGroup.controls.id.disabled).toBe(true);
      });

      it('passing NewSysUserRole should disable id FormControl', () => {
        const formGroup = service.createSysUserRoleFormGroup(sampleWithRequiredData);
        expect(formGroup.controls.id.disabled).toBe(true);

        service.resetForm(formGroup, { id: null });

        expect(formGroup.controls.id.disabled).toBe(true);
      });
    });
  });
});
