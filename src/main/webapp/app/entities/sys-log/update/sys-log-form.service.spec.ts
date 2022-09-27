import { TestBed } from '@angular/core/testing';

import { sampleWithRequiredData, sampleWithNewData } from '../sys-log.test-samples';

import { SysLogFormService } from './sys-log-form.service';

describe('SysLog Form Service', () => {
  let service: SysLogFormService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(SysLogFormService);
  });

  describe('Service methods', () => {
    describe('createSysLogFormGroup', () => {
      it('should create a new form with FormControl', () => {
        const formGroup = service.createSysLogFormGroup();

        expect(formGroup.controls).toEqual(
          expect.objectContaining({
            id: expect.any(Object),
            logType: expect.any(Object),
            logContent: expect.any(Object),
            operateType: expect.any(Object),
            userName: expect.any(Object),
            ip: expect.any(Object),
            method: expect.any(Object),
            requestUrl: expect.any(Object),
            requestParam: expect.any(Object),
            requestType: expect.any(Object),
            costTime: expect.any(Object),
            createBy: expect.any(Object),
            createTime: expect.any(Object),
            updateBy: expect.any(Object),
            updateTime: expect.any(Object),
            tenantId: expect.any(Object),
          })
        );
      });

      it('passing ISysLog should create a new form with FormGroup', () => {
        const formGroup = service.createSysLogFormGroup(sampleWithRequiredData);

        expect(formGroup.controls).toEqual(
          expect.objectContaining({
            id: expect.any(Object),
            logType: expect.any(Object),
            logContent: expect.any(Object),
            operateType: expect.any(Object),
            userName: expect.any(Object),
            ip: expect.any(Object),
            method: expect.any(Object),
            requestUrl: expect.any(Object),
            requestParam: expect.any(Object),
            requestType: expect.any(Object),
            costTime: expect.any(Object),
            createBy: expect.any(Object),
            createTime: expect.any(Object),
            updateBy: expect.any(Object),
            updateTime: expect.any(Object),
            tenantId: expect.any(Object),
          })
        );
      });
    });

    describe('getSysLog', () => {
      it('should return NewSysLog for default SysLog initial value', () => {
        // eslint-disable-next-line @typescript-eslint/no-unused-vars
        const formGroup = service.createSysLogFormGroup(sampleWithNewData);

        const sysLog = service.getSysLog(formGroup) as any;

        expect(sysLog).toMatchObject(sampleWithNewData);
      });

      it('should return NewSysLog for empty SysLog initial value', () => {
        const formGroup = service.createSysLogFormGroup();

        const sysLog = service.getSysLog(formGroup) as any;

        expect(sysLog).toMatchObject({});
      });

      it('should return ISysLog', () => {
        const formGroup = service.createSysLogFormGroup(sampleWithRequiredData);

        const sysLog = service.getSysLog(formGroup) as any;

        expect(sysLog).toMatchObject(sampleWithRequiredData);
      });
    });

    describe('resetForm', () => {
      it('passing ISysLog should not enable id FormControl', () => {
        const formGroup = service.createSysLogFormGroup();
        expect(formGroup.controls.id.disabled).toBe(true);

        service.resetForm(formGroup, sampleWithRequiredData);

        expect(formGroup.controls.id.disabled).toBe(true);
      });

      it('passing NewSysLog should disable id FormControl', () => {
        const formGroup = service.createSysLogFormGroup(sampleWithRequiredData);
        expect(formGroup.controls.id.disabled).toBe(true);

        service.resetForm(formGroup, { id: null });

        expect(formGroup.controls.id.disabled).toBe(true);
      });
    });
  });
});
