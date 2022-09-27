import { ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { FormBuilder } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { RouterTestingModule } from '@angular/router/testing';
import { of, Subject, from } from 'rxjs';

import { SysPermissionDataRuleFormService } from './sys-permission-data-rule-form.service';
import { SysPermissionDataRuleService } from '../service/sys-permission-data-rule.service';
import { ISysPermissionDataRule } from '../sys-permission-data-rule.model';

import { SysPermissionDataRuleUpdateComponent } from './sys-permission-data-rule-update.component';

describe('SysPermissionDataRule Management Update Component', () => {
  let comp: SysPermissionDataRuleUpdateComponent;
  let fixture: ComponentFixture<SysPermissionDataRuleUpdateComponent>;
  let activatedRoute: ActivatedRoute;
  let sysPermissionDataRuleFormService: SysPermissionDataRuleFormService;
  let sysPermissionDataRuleService: SysPermissionDataRuleService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule, RouterTestingModule.withRoutes([])],
      declarations: [SysPermissionDataRuleUpdateComponent],
      providers: [
        FormBuilder,
        {
          provide: ActivatedRoute,
          useValue: {
            params: from([{}]),
          },
        },
      ],
    })
      .overrideTemplate(SysPermissionDataRuleUpdateComponent, '')
      .compileComponents();

    fixture = TestBed.createComponent(SysPermissionDataRuleUpdateComponent);
    activatedRoute = TestBed.inject(ActivatedRoute);
    sysPermissionDataRuleFormService = TestBed.inject(SysPermissionDataRuleFormService);
    sysPermissionDataRuleService = TestBed.inject(SysPermissionDataRuleService);

    comp = fixture.componentInstance;
  });

  describe('ngOnInit', () => {
    it('Should update editForm', () => {
      const sysPermissionDataRule: ISysPermissionDataRule = { id: 'CBA' };

      activatedRoute.data = of({ sysPermissionDataRule });
      comp.ngOnInit();

      expect(comp.sysPermissionDataRule).toEqual(sysPermissionDataRule);
    });
  });

  describe('save', () => {
    it('Should call update service on save for existing entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<ISysPermissionDataRule>>();
      const sysPermissionDataRule = { id: 'ABC' };
      jest.spyOn(sysPermissionDataRuleFormService, 'getSysPermissionDataRule').mockReturnValue(sysPermissionDataRule);
      jest.spyOn(sysPermissionDataRuleService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ sysPermissionDataRule });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: sysPermissionDataRule }));
      saveSubject.complete();

      // THEN
      expect(sysPermissionDataRuleFormService.getSysPermissionDataRule).toHaveBeenCalled();
      expect(comp.previousState).toHaveBeenCalled();
      expect(sysPermissionDataRuleService.update).toHaveBeenCalledWith(expect.objectContaining(sysPermissionDataRule));
      expect(comp.isSaving).toEqual(false);
    });

    it('Should call create service on save for new entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<ISysPermissionDataRule>>();
      const sysPermissionDataRule = { id: 'ABC' };
      jest.spyOn(sysPermissionDataRuleFormService, 'getSysPermissionDataRule').mockReturnValue({ id: null });
      jest.spyOn(sysPermissionDataRuleService, 'create').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ sysPermissionDataRule: null });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: sysPermissionDataRule }));
      saveSubject.complete();

      // THEN
      expect(sysPermissionDataRuleFormService.getSysPermissionDataRule).toHaveBeenCalled();
      expect(sysPermissionDataRuleService.create).toHaveBeenCalled();
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).toHaveBeenCalled();
    });

    it('Should set isSaving to false on error', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<ISysPermissionDataRule>>();
      const sysPermissionDataRule = { id: 'ABC' };
      jest.spyOn(sysPermissionDataRuleService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ sysPermissionDataRule });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.error('This is an error!');

      // THEN
      expect(sysPermissionDataRuleService.update).toHaveBeenCalled();
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).not.toHaveBeenCalled();
    });
  });
});
