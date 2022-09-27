import { TestBed } from '@angular/core/testing';

import { sampleWithRequiredData, sampleWithNewData } from '../sys-permission-data-rule.test-samples';

import { SysPermissionDataRuleFormService } from './sys-permission-data-rule-form.service';

describe('SysPermissionDataRule Form Service', () => {
  let service: SysPermissionDataRuleFormService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(SysPermissionDataRuleFormService);
  });

  describe('Service methods', () => {
    describe('createSysPermissionDataRuleFormGroup', () => {
      it('should create a new form with FormControl', () => {
        const formGroup = service.createSysPermissionDataRuleFormGroup();

        expect(formGroup.controls).toEqual(
          expect.objectContaining({
            id: expect.any(Object),
            permissionId: expect.any(Object),
            ruleName: expect.any(Object),
            ruleColumn: expect.any(Object),
            ruleCOnditions: expect.any(Object),
            ruleValue: expect.any(Object),
            status: expect.any(Object),
            createBy: expect.any(Object),
            createTime: expect.any(Object),
            updateBy: expect.any(Object),
            updateTime: expect.any(Object),
            tenantId: expect.any(Object),
          })
        );
      });

      it('passing ISysPermissionDataRule should create a new form with FormGroup', () => {
        const formGroup = service.createSysPermissionDataRuleFormGroup(sampleWithRequiredData);

        expect(formGroup.controls).toEqual(
          expect.objectContaining({
            id: expect.any(Object),
            permissionId: expect.any(Object),
            ruleName: expect.any(Object),
            ruleColumn: expect.any(Object),
            ruleCOnditions: expect.any(Object),
            ruleValue: expect.any(Object),
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

    describe('getSysPermissionDataRule', () => {
      it('should return NewSysPermissionDataRule for default SysPermissionDataRule initial value', () => {
        // eslint-disable-next-line @typescript-eslint/no-unused-vars
        const formGroup = service.createSysPermissionDataRuleFormGroup(sampleWithNewData);

        const sysPermissionDataRule = service.getSysPermissionDataRule(formGroup) as any;

        expect(sysPermissionDataRule).toMatchObject(sampleWithNewData);
      });

      it('should return NewSysPermissionDataRule for empty SysPermissionDataRule initial value', () => {
        const formGroup = service.createSysPermissionDataRuleFormGroup();

        const sysPermissionDataRule = service.getSysPermissionDataRule(formGroup) as any;

        expect(sysPermissionDataRule).toMatchObject({});
      });

      it('should return ISysPermissionDataRule', () => {
        const formGroup = service.createSysPermissionDataRuleFormGroup(sampleWithRequiredData);

        const sysPermissionDataRule = service.getSysPermissionDataRule(formGroup) as any;

        expect(sysPermissionDataRule).toMatchObject(sampleWithRequiredData);
      });
    });

    describe('resetForm', () => {
      it('passing ISysPermissionDataRule should not enable id FormControl', () => {
        const formGroup = service.createSysPermissionDataRuleFormGroup();
        expect(formGroup.controls.id.disabled).toBe(true);

        service.resetForm(formGroup, sampleWithRequiredData);

        expect(formGroup.controls.id.disabled).toBe(true);
      });

      it('passing NewSysPermissionDataRule should disable id FormControl', () => {
        const formGroup = service.createSysPermissionDataRuleFormGroup(sampleWithRequiredData);
        expect(formGroup.controls.id.disabled).toBe(true);

        service.resetForm(formGroup, { id: null });

        expect(formGroup.controls.id.disabled).toBe(true);
      });
    });
  });
});
