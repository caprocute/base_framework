import { TestBed } from '@angular/core/testing';

import { sampleWithRequiredData, sampleWithNewData } from '../sys-data-log.test-samples';

import { SysDataLogFormService } from './sys-data-log-form.service';

describe('SysDataLog Form Service', () => {
  let service: SysDataLogFormService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(SysDataLogFormService);
  });

  describe('Service methods', () => {
    describe('createSysDataLogFormGroup', () => {
      it('should create a new form with FormControl', () => {
        const formGroup = service.createSysDataLogFormGroup();

        expect(formGroup.controls).toEqual(
          expect.objectContaining({
            id: expect.any(Object),
            dataTable: expect.any(Object),
            dataId: expect.any(Object),
            dataContent: expect.any(Object),
            dataVersion: expect.any(Object),
            createBy: expect.any(Object),
            createTime: expect.any(Object),
            updateBy: expect.any(Object),
            updateTime: expect.any(Object),
            tenantId: expect.any(Object),
          })
        );
      });

      it('passing ISysDataLog should create a new form with FormGroup', () => {
        const formGroup = service.createSysDataLogFormGroup(sampleWithRequiredData);

        expect(formGroup.controls).toEqual(
          expect.objectContaining({
            id: expect.any(Object),
            dataTable: expect.any(Object),
            dataId: expect.any(Object),
            dataContent: expect.any(Object),
            dataVersion: expect.any(Object),
            createBy: expect.any(Object),
            createTime: expect.any(Object),
            updateBy: expect.any(Object),
            updateTime: expect.any(Object),
            tenantId: expect.any(Object),
          })
        );
      });
    });

    describe('getSysDataLog', () => {
      it('should return NewSysDataLog for default SysDataLog initial value', () => {
        // eslint-disable-next-line @typescript-eslint/no-unused-vars
        const formGroup = service.createSysDataLogFormGroup(sampleWithNewData);

        const sysDataLog = service.getSysDataLog(formGroup) as any;

        expect(sysDataLog).toMatchObject(sampleWithNewData);
      });

      it('should return NewSysDataLog for empty SysDataLog initial value', () => {
        const formGroup = service.createSysDataLogFormGroup();

        const sysDataLog = service.getSysDataLog(formGroup) as any;

        expect(sysDataLog).toMatchObject({});
      });

      it('should return ISysDataLog', () => {
        const formGroup = service.createSysDataLogFormGroup(sampleWithRequiredData);

        const sysDataLog = service.getSysDataLog(formGroup) as any;

        expect(sysDataLog).toMatchObject(sampleWithRequiredData);
      });
    });

    describe('resetForm', () => {
      it('passing ISysDataLog should not enable id FormControl', () => {
        const formGroup = service.createSysDataLogFormGroup();
        expect(formGroup.controls.id.disabled).toBe(true);

        service.resetForm(formGroup, sampleWithRequiredData);

        expect(formGroup.controls.id.disabled).toBe(true);
      });

      it('passing NewSysDataLog should disable id FormControl', () => {
        const formGroup = service.createSysDataLogFormGroup(sampleWithRequiredData);
        expect(formGroup.controls.id.disabled).toBe(true);

        service.resetForm(formGroup, { id: null });

        expect(formGroup.controls.id.disabled).toBe(true);
      });
    });
  });
});
