import { ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { FormBuilder } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { RouterTestingModule } from '@angular/router/testing';
import { of, Subject, from } from 'rxjs';

import { SysRolePermissionFormService } from './sys-role-permission-form.service';
import { SysRolePermissionService } from '../service/sys-role-permission.service';
import { ISysRolePermission } from '../sys-role-permission.model';

import { SysRolePermissionUpdateComponent } from './sys-role-permission-update.component';

describe('SysRolePermission Management Update Component', () => {
  let comp: SysRolePermissionUpdateComponent;
  let fixture: ComponentFixture<SysRolePermissionUpdateComponent>;
  let activatedRoute: ActivatedRoute;
  let sysRolePermissionFormService: SysRolePermissionFormService;
  let sysRolePermissionService: SysRolePermissionService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule, RouterTestingModule.withRoutes([])],
      declarations: [SysRolePermissionUpdateComponent],
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
      .overrideTemplate(SysRolePermissionUpdateComponent, '')
      .compileComponents();

    fixture = TestBed.createComponent(SysRolePermissionUpdateComponent);
    activatedRoute = TestBed.inject(ActivatedRoute);
    sysRolePermissionFormService = TestBed.inject(SysRolePermissionFormService);
    sysRolePermissionService = TestBed.inject(SysRolePermissionService);

    comp = fixture.componentInstance;
  });

  describe('ngOnInit', () => {
    it('Should update editForm', () => {
      const sysRolePermission: ISysRolePermission = { id: 'CBA' };

      activatedRoute.data = of({ sysRolePermission });
      comp.ngOnInit();

      expect(comp.sysRolePermission).toEqual(sysRolePermission);
    });
  });

  describe('save', () => {
    it('Should call update service on save for existing entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<ISysRolePermission>>();
      const sysRolePermission = { id: 'ABC' };
      jest.spyOn(sysRolePermissionFormService, 'getSysRolePermission').mockReturnValue(sysRolePermission);
      jest.spyOn(sysRolePermissionService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ sysRolePermission });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: sysRolePermission }));
      saveSubject.complete();

      // THEN
      expect(sysRolePermissionFormService.getSysRolePermission).toHaveBeenCalled();
      expect(comp.previousState).toHaveBeenCalled();
      expect(sysRolePermissionService.update).toHaveBeenCalledWith(expect.objectContaining(sysRolePermission));
      expect(comp.isSaving).toEqual(false);
    });

    it('Should call create service on save for new entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<ISysRolePermission>>();
      const sysRolePermission = { id: 'ABC' };
      jest.spyOn(sysRolePermissionFormService, 'getSysRolePermission').mockReturnValue({ id: null });
      jest.spyOn(sysRolePermissionService, 'create').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ sysRolePermission: null });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: sysRolePermission }));
      saveSubject.complete();

      // THEN
      expect(sysRolePermissionFormService.getSysRolePermission).toHaveBeenCalled();
      expect(sysRolePermissionService.create).toHaveBeenCalled();
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).toHaveBeenCalled();
    });

    it('Should set isSaving to false on error', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<ISysRolePermission>>();
      const sysRolePermission = { id: 'ABC' };
      jest.spyOn(sysRolePermissionService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ sysRolePermission });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.error('This is an error!');

      // THEN
      expect(sysRolePermissionService.update).toHaveBeenCalled();
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).not.toHaveBeenCalled();
    });
  });
});
