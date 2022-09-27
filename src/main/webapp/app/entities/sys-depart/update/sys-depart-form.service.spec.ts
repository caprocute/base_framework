import { TestBed } from '@angular/core/testing';

import { sampleWithRequiredData, sampleWithNewData } from '../sys-depart.test-samples';

import { SysDepartFormService } from './sys-depart-form.service';

describe('SysDepart Form Service', () => {
  let service: SysDepartFormService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(SysDepartFormService);
  });

  describe('Service methods', () => {
    describe('createSysDepartFormGroup', () => {
      it('should create a new form with FormControl', () => {
        const formGroup = service.createSysDepartFormGroup();

        expect(formGroup.controls).toEqual(
          expect.objectContaining({
            id: expect.any(Object),
            parentId: expect.any(Object),
            departName: expect.any(Object),
            departOrder: expect.any(Object),
            orgCategory: expect.any(Object),
            orgType: expect.any(Object),
            orgCode: expect.any(Object),
            mobile: expect.any(Object),
            fax: expect.any(Object),
            address: expect.any(Object),
            memo: expect.any(Object),
            status: expect.any(Object),
            delFlag: expect.any(Object),
            createBy: expect.any(Object),
            createTime: expect.any(Object),
            updateBy: expect.any(Object),
            updateTime: expect.any(Object),
            tenantId: expect.any(Object),
          })
        );
      });

      it('passing ISysDepart should create a new form with FormGroup', () => {
        const formGroup = service.createSysDepartFormGroup(sampleWithRequiredData);

        expect(formGroup.controls).toEqual(
          expect.objectContaining({
            id: expect.any(Object),
            parentId: expect.any(Object),
            departName: expect.any(Object),
            departOrder: expect.any(Object),
            orgCategory: expect.any(Object),
            orgType: expect.any(Object),
            orgCode: expect.any(Object),
            mobile: expect.any(Object),
            fax: expect.any(Object),
            address: expect.any(Object),
            memo: expect.any(Object),
            status: expect.any(Object),
            delFlag: expect.any(Object),
            createBy: expect.any(Object),
            createTime: expect.any(Object),
            updateBy: expect.any(Object),
            updateTime: expect.any(Object),
            tenantId: expect.any(Object),
          })
        );
      });
    });

    describe('getSysDepart', () => {
      it('should return NewSysDepart for default SysDepart initial value', () => {
        // eslint-disable-next-line @typescript-eslint/no-unused-vars
        const formGroup = service.createSysDepartFormGroup(sampleWithNewData);

        const sysDepart = service.getSysDepart(formGroup) as any;

        expect(sysDepart).toMatchObject(sampleWithNewData);
      });

      it('should return NewSysDepart for empty SysDepart initial value', () => {
        const formGroup = service.createSysDepartFormGroup();

        const sysDepart = service.getSysDepart(formGroup) as any;

        expect(sysDepart).toMatchObject({});
      });

      it('should return ISysDepart', () => {
        const formGroup = service.createSysDepartFormGroup(sampleWithRequiredData);

        const sysDepart = service.getSysDepart(formGroup) as any;

        expect(sysDepart).toMatchObject(sampleWithRequiredData);
      });
    });

    describe('resetForm', () => {
      it('passing ISysDepart should not enable id FormControl', () => {
        const formGroup = service.createSysDepartFormGroup();
        expect(formGroup.controls.id.disabled).toBe(true);

        service.resetForm(formGroup, sampleWithRequiredData);

        expect(formGroup.controls.id.disabled).toBe(true);
      });

      it('passing NewSysDepart should disable id FormControl', () => {
        const formGroup = service.createSysDepartFormGroup(sampleWithRequiredData);
        expect(formGroup.controls.id.disabled).toBe(true);

        service.resetForm(formGroup, { id: null });

        expect(formGroup.controls.id.disabled).toBe(true);
      });
    });
  });
});
