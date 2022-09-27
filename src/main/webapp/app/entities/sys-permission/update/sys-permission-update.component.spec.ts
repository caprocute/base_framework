import { ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { FormBuilder } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { RouterTestingModule } from '@angular/router/testing';
import { of, Subject, from } from 'rxjs';

import { SysPermissionFormService } from './sys-permission-form.service';
import { SysPermissionService } from '../service/sys-permission.service';
import { ISysPermission } from '../sys-permission.model';

import { SysPermissionUpdateComponent } from './sys-permission-update.component';

describe('SysPermission Management Update Component', () => {
  let comp: SysPermissionUpdateComponent;
  let fixture: ComponentFixture<SysPermissionUpdateComponent>;
  let activatedRoute: ActivatedRoute;
  let sysPermissionFormService: SysPermissionFormService;
  let sysPermissionService: SysPermissionService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule, RouterTestingModule.withRoutes([])],
      declarations: [SysPermissionUpdateComponent],
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
      .overrideTemplate(SysPermissionUpdateComponent, '')
      .compileComponents();

    fixture = TestBed.createComponent(SysPermissionUpdateComponent);
    activatedRoute = TestBed.inject(ActivatedRoute);
    sysPermissionFormService = TestBed.inject(SysPermissionFormService);
    sysPermissionService = TestBed.inject(SysPermissionService);

    comp = fixture.componentInstance;
  });

  describe('ngOnInit', () => {
    it('Should update editForm', () => {
      const sysPermission: ISysPermission = { id: 'CBA' };

      activatedRoute.data = of({ sysPermission });
      comp.ngOnInit();

      expect(comp.sysPermission).toEqual(sysPermission);
    });
  });

  describe('save', () => {
    it('Should call update service on save for existing entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<ISysPermission>>();
      const sysPermission = { id: 'ABC' };
      jest.spyOn(sysPermissionFormService, 'getSysPermission').mockReturnValue(sysPermission);
      jest.spyOn(sysPermissionService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ sysPermission });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: sysPermission }));
      saveSubject.complete();

      // THEN
      expect(sysPermissionFormService.getSysPermission).toHaveBeenCalled();
      expect(comp.previousState).toHaveBeenCalled();
      expect(sysPermissionService.update).toHaveBeenCalledWith(expect.objectContaining(sysPermission));
      expect(comp.isSaving).toEqual(false);
    });

    it('Should call create service on save for new entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<ISysPermission>>();
      const sysPermission = { id: 'ABC' };
      jest.spyOn(sysPermissionFormService, 'getSysPermission').mockReturnValue({ id: null });
      jest.spyOn(sysPermissionService, 'create').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ sysPermission: null });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: sysPermission }));
      saveSubject.complete();

      // THEN
      expect(sysPermissionFormService.getSysPermission).toHaveBeenCalled();
      expect(sysPermissionService.create).toHaveBeenCalled();
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).toHaveBeenCalled();
    });

    it('Should set isSaving to false on error', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<ISysPermission>>();
      const sysPermission = { id: 'ABC' };
      jest.spyOn(sysPermissionService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ sysPermission });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.error('This is an error!');

      // THEN
      expect(sysPermissionService.update).toHaveBeenCalled();
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).not.toHaveBeenCalled();
    });
  });
});
