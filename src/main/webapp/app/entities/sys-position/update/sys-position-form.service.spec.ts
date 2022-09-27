import { TestBed } from '@angular/core/testing';

import { sampleWithRequiredData, sampleWithNewData } from '../sys-position.test-samples';

import { SysPositionFormService } from './sys-position-form.service';

describe('SysPosition Form Service', () => {
  let service: SysPositionFormService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(SysPositionFormService);
  });

  describe('Service methods', () => {
    describe('createSysPositionFormGroup', () => {
      it('should create a new form with FormControl', () => {
        const formGroup = service.createSysPositionFormGroup();

        expect(formGroup.controls).toEqual(
          expect.objectContaining({
            id: expect.any(Object),
            code: expect.any(Object),
            name: expect.any(Object),
            postRank: expect.any(Object),
            companyId: expect.any(Object),
            sysOrgCode: expect.any(Object),
            createBy: expect.any(Object),
            createTime: expect.any(Object),
            updateBy: expect.any(Object),
            updateTime: expect.any(Object),
            tenantId: expect.any(Object),
          })
        );
      });

      it('passing ISysPosition should create a new form with FormGroup', () => {
        const formGroup = service.createSysPositionFormGroup(sampleWithRequiredData);

        expect(formGroup.controls).toEqual(
          expect.objectContaining({
            id: expect.any(Object),
            code: expect.any(Object),
            name: expect.any(Object),
            postRank: expect.any(Object),
            companyId: expect.any(Object),
            sysOrgCode: expect.any(Object),
            createBy: expect.any(Object),
            createTime: expect.any(Object),
            updateBy: expect.any(Object),
            updateTime: expect.any(Object),
            tenantId: expect.any(Object),
          })
        );
      });
    });

    describe('getSysPosition', () => {
      it('should return NewSysPosition for default SysPosition initial value', () => {
        // eslint-disable-next-line @typescript-eslint/no-unused-vars
        const formGroup = service.createSysPositionFormGroup(sampleWithNewData);

        const sysPosition = service.getSysPosition(formGroup) as any;

        expect(sysPosition).toMatchObject(sampleWithNewData);
      });

      it('should return NewSysPosition for empty SysPosition initial value', () => {
        const formGroup = service.createSysPositionFormGroup();

        const sysPosition = service.getSysPosition(formGroup) as any;

        expect(sysPosition).toMatchObject({});
      });

      it('should return ISysPosition', () => {
        const formGroup = service.createSysPositionFormGroup(sampleWithRequiredData);

        const sysPosition = service.getSysPosition(formGroup) as any;

        expect(sysPosition).toMatchObject(sampleWithRequiredData);
      });
    });

    describe('resetForm', () => {
      it('passing ISysPosition should not enable id FormControl', () => {
        const formGroup = service.createSysPositionFormGroup();
        expect(formGroup.controls.id.disabled).toBe(true);

        service.resetForm(formGroup, sampleWithRequiredData);

        expect(formGroup.controls.id.disabled).toBe(true);
      });

      it('passing NewSysPosition should disable id FormControl', () => {
        const formGroup = service.createSysPositionFormGroup(sampleWithRequiredData);
        expect(formGroup.controls.id.disabled).toBe(true);

        service.resetForm(formGroup, { id: null });

        expect(formGroup.controls.id.disabled).toBe(true);
      });
    });
  });
});
