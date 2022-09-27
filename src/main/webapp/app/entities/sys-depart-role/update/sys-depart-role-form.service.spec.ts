import { TestBed } from '@angular/core/testing';

import { sampleWithRequiredData, sampleWithNewData } from '../sys-depart-role.test-samples';

import { SysDepartRoleFormService } from './sys-depart-role-form.service';

describe('SysDepartRole Form Service', () => {
  let service: SysDepartRoleFormService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(SysDepartRoleFormService);
  });

  describe('Service methods', () => {
    describe('createSysDepartRoleFormGroup', () => {
      it('should create a new form with FormControl', () => {
        const formGroup = service.createSysDepartRoleFormGroup();

        expect(formGroup.controls).toEqual(
          expect.objectContaining({
            id: expect.any(Object),
            departID: expect.any(Object),
            roleName: expect.any(Object),
            roleCode: expect.any(Object),
            description: expect.any(Object),
            createBy: expect.any(Object),
            createTime: expect.any(Object),
            updateBy: expect.any(Object),
            updateTime: expect.any(Object),
            tenantId: expect.any(Object),
          })
        );
      });

      it('passing ISysDepartRole should create a new form with FormGroup', () => {
        const formGroup = service.createSysDepartRoleFormGroup(sampleWithRequiredData);

        expect(formGroup.controls).toEqual(
          expect.objectContaining({
            id: expect.any(Object),
            departID: expect.any(Object),
            roleName: expect.any(Object),
            roleCode: expect.any(Object),
            description: expect.any(Object),
            createBy: expect.any(Object),
            createTime: expect.any(Object),
            updateBy: expect.any(Object),
            updateTime: expect.any(Object),
            tenantId: expect.any(Object),
          })
        );
      });
    });

    describe('getSysDepartRole', () => {
      it('should return NewSysDepartRole for default SysDepartRole initial value', () => {
        // eslint-disable-next-line @typescript-eslint/no-unused-vars
        const formGroup = service.createSysDepartRoleFormGroup(sampleWithNewData);

        const sysDepartRole = service.getSysDepartRole(formGroup) as any;

        expect(sysDepartRole).toMatchObject(sampleWithNewData);
      });

      it('should return NewSysDepartRole for empty SysDepartRole initial value', () => {
        const formGroup = service.createSysDepartRoleFormGroup();

        const sysDepartRole = service.getSysDepartRole(formGroup) as any;

        expect(sysDepartRole).toMatchObject({});
      });

      it('should return ISysDepartRole', () => {
        const formGroup = service.createSysDepartRoleFormGroup(sampleWithRequiredData);

        const sysDepartRole = service.getSysDepartRole(formGroup) as any;

        expect(sysDepartRole).toMatchObject(sampleWithRequiredData);
      });
    });

    describe('resetForm', () => {
      it('passing ISysDepartRole should not enable id FormControl', () => {
        const formGroup = service.createSysDepartRoleFormGroup();
        expect(formGroup.controls.id.disabled).toBe(true);

        service.resetForm(formGroup, sampleWithRequiredData);

        expect(formGroup.controls.id.disabled).toBe(true);
      });

      it('passing NewSysDepartRole should disable id FormControl', () => {
        const formGroup = service.createSysDepartRoleFormGroup(sampleWithRequiredData);
        expect(formGroup.controls.id.disabled).toBe(true);

        service.resetForm(formGroup, { id: null });

        expect(formGroup.controls.id.disabled).toBe(true);
      });
    });
  });
});
