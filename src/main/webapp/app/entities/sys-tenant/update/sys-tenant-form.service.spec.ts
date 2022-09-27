import { TestBed } from '@angular/core/testing';

import { sampleWithRequiredData, sampleWithNewData } from '../sys-tenant.test-samples';

import { SysTenantFormService } from './sys-tenant-form.service';

describe('SysTenant Form Service', () => {
  let service: SysTenantFormService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(SysTenantFormService);
  });

  describe('Service methods', () => {
    describe('createSysTenantFormGroup', () => {
      it('should create a new form with FormControl', () => {
        const formGroup = service.createSysTenantFormGroup();

        expect(formGroup.controls).toEqual(
          expect.objectContaining({
            id: expect.any(Object),
            name: expect.any(Object),
            status: expect.any(Object),
            createBy: expect.any(Object),
            createTime: expect.any(Object),
            updateBy: expect.any(Object),
            updateTime: expect.any(Object),
            tenantId: expect.any(Object),
          })
        );
      });

      it('passing ISysTenant should create a new form with FormGroup', () => {
        const formGroup = service.createSysTenantFormGroup(sampleWithRequiredData);

        expect(formGroup.controls).toEqual(
          expect.objectContaining({
            id: expect.any(Object),
            name: expect.any(Object),
            status: expect.any(Object),
            createBy: expect.any(Object),
            createTime: expect.any(Object),
            updateBy: expect.any(Object),
            updateTime: expect.any(Object),
            tenantId: expect.any(Object),
          })
        );
      });
    });

    describe('getSysTenant', () => {
      it('should return NewSysTenant for default SysTenant initial value', () => {
        // eslint-disable-next-line @typescript-eslint/no-unused-vars
        const formGroup = service.createSysTenantFormGroup(sampleWithNewData);

        const sysTenant = service.getSysTenant(formGroup) as any;

        expect(sysTenant).toMatchObject(sampleWithNewData);
      });

      it('should return NewSysTenant for empty SysTenant initial value', () => {
        const formGroup = service.createSysTenantFormGroup();

        const sysTenant = service.getSysTenant(formGroup) as any;

        expect(sysTenant).toMatchObject({});
      });

      it('should return ISysTenant', () => {
        const formGroup = service.createSysTenantFormGroup(sampleWithRequiredData);

        const sysTenant = service.getSysTenant(formGroup) as any;

        expect(sysTenant).toMatchObject(sampleWithRequiredData);
      });
    });

    describe('resetForm', () => {
      it('passing ISysTenant should not enable id FormControl', () => {
        const formGroup = service.createSysTenantFormGroup();
        expect(formGroup.controls.id.disabled).toBe(true);

        service.resetForm(formGroup, sampleWithRequiredData);

        expect(formGroup.controls.id.disabled).toBe(true);
      });

      it('passing NewSysTenant should disable id FormControl', () => {
        const formGroup = service.createSysTenantFormGroup(sampleWithRequiredData);
        expect(formGroup.controls.id.disabled).toBe(true);

        service.resetForm(formGroup, { id: null });

        expect(formGroup.controls.id.disabled).toBe(true);
      });
    });
  });
});
