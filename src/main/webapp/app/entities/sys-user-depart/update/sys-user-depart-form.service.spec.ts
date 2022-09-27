import { TestBed } from '@angular/core/testing';

import { sampleWithRequiredData, sampleWithNewData } from '../sys-user-depart.test-samples';

import { SysUserDepartFormService } from './sys-user-depart-form.service';

describe('SysUserDepart Form Service', () => {
  let service: SysUserDepartFormService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(SysUserDepartFormService);
  });

  describe('Service methods', () => {
    describe('createSysUserDepartFormGroup', () => {
      it('should create a new form with FormControl', () => {
        const formGroup = service.createSysUserDepartFormGroup();

        expect(formGroup.controls).toEqual(
          expect.objectContaining({
            id: expect.any(Object),
            userId: expect.any(Object),
            depID: expect.any(Object),
          })
        );
      });

      it('passing ISysUserDepart should create a new form with FormGroup', () => {
        const formGroup = service.createSysUserDepartFormGroup(sampleWithRequiredData);

        expect(formGroup.controls).toEqual(
          expect.objectContaining({
            id: expect.any(Object),
            userId: expect.any(Object),
            depID: expect.any(Object),
          })
        );
      });
    });

    describe('getSysUserDepart', () => {
      it('should return NewSysUserDepart for default SysUserDepart initial value', () => {
        // eslint-disable-next-line @typescript-eslint/no-unused-vars
        const formGroup = service.createSysUserDepartFormGroup(sampleWithNewData);

        const sysUserDepart = service.getSysUserDepart(formGroup) as any;

        expect(sysUserDepart).toMatchObject(sampleWithNewData);
      });

      it('should return NewSysUserDepart for empty SysUserDepart initial value', () => {
        const formGroup = service.createSysUserDepartFormGroup();

        const sysUserDepart = service.getSysUserDepart(formGroup) as any;

        expect(sysUserDepart).toMatchObject({});
      });

      it('should return ISysUserDepart', () => {
        const formGroup = service.createSysUserDepartFormGroup(sampleWithRequiredData);

        const sysUserDepart = service.getSysUserDepart(formGroup) as any;

        expect(sysUserDepart).toMatchObject(sampleWithRequiredData);
      });
    });

    describe('resetForm', () => {
      it('passing ISysUserDepart should not enable id FormControl', () => {
        const formGroup = service.createSysUserDepartFormGroup();
        expect(formGroup.controls.id.disabled).toBe(true);

        service.resetForm(formGroup, sampleWithRequiredData);

        expect(formGroup.controls.id.disabled).toBe(true);
      });

      it('passing NewSysUserDepart should disable id FormControl', () => {
        const formGroup = service.createSysUserDepartFormGroup(sampleWithRequiredData);
        expect(formGroup.controls.id.disabled).toBe(true);

        service.resetForm(formGroup, { id: null });

        expect(formGroup.controls.id.disabled).toBe(true);
      });
    });
  });
});
