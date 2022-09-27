import { TestBed } from '@angular/core/testing';

import { sampleWithRequiredData, sampleWithNewData } from '../sys-role-index.test-samples';

import { SysRoleIndexFormService } from './sys-role-index-form.service';

describe('SysRoleIndex Form Service', () => {
  let service: SysRoleIndexFormService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(SysRoleIndexFormService);
  });

  describe('Service methods', () => {
    describe('createSysRoleIndexFormGroup', () => {
      it('should create a new form with FormControl', () => {
        const formGroup = service.createSysRoleIndexFormGroup();

        expect(formGroup.controls).toEqual(
          expect.objectContaining({
            id: expect.any(Object),
            roleCode: expect.any(Object),
            url: expect.any(Object),
            component: expect.any(Object),
            isRoute: expect.any(Object),
            priority: expect.any(Object),
            status: expect.any(Object),
            createBy: expect.any(Object),
            createTime: expect.any(Object),
            updateBy: expect.any(Object),
            updateTime: expect.any(Object),
            tenantId: expect.any(Object),
          })
        );
      });

      it('passing ISysRoleIndex should create a new form with FormGroup', () => {
        const formGroup = service.createSysRoleIndexFormGroup(sampleWithRequiredData);

        expect(formGroup.controls).toEqual(
          expect.objectContaining({
            id: expect.any(Object),
            roleCode: expect.any(Object),
            url: expect.any(Object),
            component: expect.any(Object),
            isRoute: expect.any(Object),
            priority: expect.any(Object),
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

    describe('getSysRoleIndex', () => {
      it('should return NewSysRoleIndex for default SysRoleIndex initial value', () => {
        // eslint-disable-next-line @typescript-eslint/no-unused-vars
        const formGroup = service.createSysRoleIndexFormGroup(sampleWithNewData);

        const sysRoleIndex = service.getSysRoleIndex(formGroup) as any;

        expect(sysRoleIndex).toMatchObject(sampleWithNewData);
      });

      it('should return NewSysRoleIndex for empty SysRoleIndex initial value', () => {
        const formGroup = service.createSysRoleIndexFormGroup();

        const sysRoleIndex = service.getSysRoleIndex(formGroup) as any;

        expect(sysRoleIndex).toMatchObject({});
      });

      it('should return ISysRoleIndex', () => {
        const formGroup = service.createSysRoleIndexFormGroup(sampleWithRequiredData);

        const sysRoleIndex = service.getSysRoleIndex(formGroup) as any;

        expect(sysRoleIndex).toMatchObject(sampleWithRequiredData);
      });
    });

    describe('resetForm', () => {
      it('passing ISysRoleIndex should not enable id FormControl', () => {
        const formGroup = service.createSysRoleIndexFormGroup();
        expect(formGroup.controls.id.disabled).toBe(true);

        service.resetForm(formGroup, sampleWithRequiredData);

        expect(formGroup.controls.id.disabled).toBe(true);
      });

      it('passing NewSysRoleIndex should disable id FormControl', () => {
        const formGroup = service.createSysRoleIndexFormGroup(sampleWithRequiredData);
        expect(formGroup.controls.id.disabled).toBe(true);

        service.resetForm(formGroup, { id: null });

        expect(formGroup.controls.id.disabled).toBe(true);
      });
    });
  });
});
