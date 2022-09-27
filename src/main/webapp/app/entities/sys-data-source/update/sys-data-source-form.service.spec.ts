import { TestBed } from '@angular/core/testing';

import { sampleWithRequiredData, sampleWithNewData } from '../sys-data-source.test-samples';

import { SysDataSourceFormService } from './sys-data-source-form.service';

describe('SysDataSource Form Service', () => {
  let service: SysDataSourceFormService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(SysDataSourceFormService);
  });

  describe('Service methods', () => {
    describe('createSysDataSourceFormGroup', () => {
      it('should create a new form with FormControl', () => {
        const formGroup = service.createSysDataSourceFormGroup();

        expect(formGroup.controls).toEqual(
          expect.objectContaining({
            id: expect.any(Object),
            name: expect.any(Object),
            remark: expect.any(Object),
            dbType: expect.any(Object),
            dbDriver: expect.any(Object),
            dbUrl: expect.any(Object),
            dbName: expect.any(Object),
            dbUserName: expect.any(Object),
            dbDrowssap: expect.any(Object),
            createBy: expect.any(Object),
            createTime: expect.any(Object),
            updateBy: expect.any(Object),
            updateTime: expect.any(Object),
            tenantId: expect.any(Object),
          })
        );
      });

      it('passing ISysDataSource should create a new form with FormGroup', () => {
        const formGroup = service.createSysDataSourceFormGroup(sampleWithRequiredData);

        expect(formGroup.controls).toEqual(
          expect.objectContaining({
            id: expect.any(Object),
            name: expect.any(Object),
            remark: expect.any(Object),
            dbType: expect.any(Object),
            dbDriver: expect.any(Object),
            dbUrl: expect.any(Object),
            dbName: expect.any(Object),
            dbUserName: expect.any(Object),
            dbDrowssap: expect.any(Object),
            createBy: expect.any(Object),
            createTime: expect.any(Object),
            updateBy: expect.any(Object),
            updateTime: expect.any(Object),
            tenantId: expect.any(Object),
          })
        );
      });
    });

    describe('getSysDataSource', () => {
      it('should return NewSysDataSource for default SysDataSource initial value', () => {
        // eslint-disable-next-line @typescript-eslint/no-unused-vars
        const formGroup = service.createSysDataSourceFormGroup(sampleWithNewData);

        const sysDataSource = service.getSysDataSource(formGroup) as any;

        expect(sysDataSource).toMatchObject(sampleWithNewData);
      });

      it('should return NewSysDataSource for empty SysDataSource initial value', () => {
        const formGroup = service.createSysDataSourceFormGroup();

        const sysDataSource = service.getSysDataSource(formGroup) as any;

        expect(sysDataSource).toMatchObject({});
      });

      it('should return ISysDataSource', () => {
        const formGroup = service.createSysDataSourceFormGroup(sampleWithRequiredData);

        const sysDataSource = service.getSysDataSource(formGroup) as any;

        expect(sysDataSource).toMatchObject(sampleWithRequiredData);
      });
    });

    describe('resetForm', () => {
      it('passing ISysDataSource should not enable id FormControl', () => {
        const formGroup = service.createSysDataSourceFormGroup();
        expect(formGroup.controls.id.disabled).toBe(true);

        service.resetForm(formGroup, sampleWithRequiredData);

        expect(formGroup.controls.id.disabled).toBe(true);
      });

      it('passing NewSysDataSource should disable id FormControl', () => {
        const formGroup = service.createSysDataSourceFormGroup(sampleWithRequiredData);
        expect(formGroup.controls.id.disabled).toBe(true);

        service.resetForm(formGroup, { id: null });

        expect(formGroup.controls.id.disabled).toBe(true);
      });
    });
  });
});
