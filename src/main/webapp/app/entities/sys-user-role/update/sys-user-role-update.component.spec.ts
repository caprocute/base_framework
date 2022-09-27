import { ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { FormBuilder } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { RouterTestingModule } from '@angular/router/testing';
import { of, Subject, from } from 'rxjs';

import { SysUserRoleFormService } from './sys-user-role-form.service';
import { SysUserRoleService } from '../service/sys-user-role.service';
import { ISysUserRole } from '../sys-user-role.model';

import { SysUserRoleUpdateComponent } from './sys-user-role-update.component';

describe('SysUserRole Management Update Component', () => {
  let comp: SysUserRoleUpdateComponent;
  let fixture: ComponentFixture<SysUserRoleUpdateComponent>;
  let activatedRoute: ActivatedRoute;
  let sysUserRoleFormService: SysUserRoleFormService;
  let sysUserRoleService: SysUserRoleService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule, RouterTestingModule.withRoutes([])],
      declarations: [SysUserRoleUpdateComponent],
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
      .overrideTemplate(SysUserRoleUpdateComponent, '')
      .compileComponents();

    fixture = TestBed.createComponent(SysUserRoleUpdateComponent);
    activatedRoute = TestBed.inject(ActivatedRoute);
    sysUserRoleFormService = TestBed.inject(SysUserRoleFormService);
    sysUserRoleService = TestBed.inject(SysUserRoleService);

    comp = fixture.componentInstance;
  });

  describe('ngOnInit', () => {
    it('Should update editForm', () => {
      const sysUserRole: ISysUserRole = { id: 'CBA' };

      activatedRoute.data = of({ sysUserRole });
      comp.ngOnInit();

      expect(comp.sysUserRole).toEqual(sysUserRole);
    });
  });

  describe('save', () => {
    it('Should call update service on save for existing entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<ISysUserRole>>();
      const sysUserRole = { id: 'ABC' };
      jest.spyOn(sysUserRoleFormService, 'getSysUserRole').mockReturnValue(sysUserRole);
      jest.spyOn(sysUserRoleService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ sysUserRole });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: sysUserRole }));
      saveSubject.complete();

      // THEN
      expect(sysUserRoleFormService.getSysUserRole).toHaveBeenCalled();
      expect(comp.previousState).toHaveBeenCalled();
      expect(sysUserRoleService.update).toHaveBeenCalledWith(expect.objectContaining(sysUserRole));
      expect(comp.isSaving).toEqual(false);
    });

    it('Should call create service on save for new entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<ISysUserRole>>();
      const sysUserRole = { id: 'ABC' };
      jest.spyOn(sysUserRoleFormService, 'getSysUserRole').mockReturnValue({ id: null });
      jest.spyOn(sysUserRoleService, 'create').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ sysUserRole: null });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: sysUserRole }));
      saveSubject.complete();

      // THEN
      expect(sysUserRoleFormService.getSysUserRole).toHaveBeenCalled();
      expect(sysUserRoleService.create).toHaveBeenCalled();
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).toHaveBeenCalled();
    });

    it('Should set isSaving to false on error', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<ISysUserRole>>();
      const sysUserRole = { id: 'ABC' };
      jest.spyOn(sysUserRoleService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ sysUserRole });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.error('This is an error!');

      // THEN
      expect(sysUserRoleService.update).toHaveBeenCalled();
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).not.toHaveBeenCalled();
    });
  });
});
