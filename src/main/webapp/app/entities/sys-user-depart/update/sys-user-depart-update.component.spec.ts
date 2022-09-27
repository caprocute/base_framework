import { ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { FormBuilder } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { RouterTestingModule } from '@angular/router/testing';
import { of, Subject, from } from 'rxjs';

import { SysUserDepartFormService } from './sys-user-depart-form.service';
import { SysUserDepartService } from '../service/sys-user-depart.service';
import { ISysUserDepart } from '../sys-user-depart.model';

import { SysUserDepartUpdateComponent } from './sys-user-depart-update.component';

describe('SysUserDepart Management Update Component', () => {
  let comp: SysUserDepartUpdateComponent;
  let fixture: ComponentFixture<SysUserDepartUpdateComponent>;
  let activatedRoute: ActivatedRoute;
  let sysUserDepartFormService: SysUserDepartFormService;
  let sysUserDepartService: SysUserDepartService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule, RouterTestingModule.withRoutes([])],
      declarations: [SysUserDepartUpdateComponent],
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
      .overrideTemplate(SysUserDepartUpdateComponent, '')
      .compileComponents();

    fixture = TestBed.createComponent(SysUserDepartUpdateComponent);
    activatedRoute = TestBed.inject(ActivatedRoute);
    sysUserDepartFormService = TestBed.inject(SysUserDepartFormService);
    sysUserDepartService = TestBed.inject(SysUserDepartService);

    comp = fixture.componentInstance;
  });

  describe('ngOnInit', () => {
    it('Should update editForm', () => {
      const sysUserDepart: ISysUserDepart = { id: 'CBA' };

      activatedRoute.data = of({ sysUserDepart });
      comp.ngOnInit();

      expect(comp.sysUserDepart).toEqual(sysUserDepart);
    });
  });

  describe('save', () => {
    it('Should call update service on save for existing entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<ISysUserDepart>>();
      const sysUserDepart = { id: 'ABC' };
      jest.spyOn(sysUserDepartFormService, 'getSysUserDepart').mockReturnValue(sysUserDepart);
      jest.spyOn(sysUserDepartService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ sysUserDepart });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: sysUserDepart }));
      saveSubject.complete();

      // THEN
      expect(sysUserDepartFormService.getSysUserDepart).toHaveBeenCalled();
      expect(comp.previousState).toHaveBeenCalled();
      expect(sysUserDepartService.update).toHaveBeenCalledWith(expect.objectContaining(sysUserDepart));
      expect(comp.isSaving).toEqual(false);
    });

    it('Should call create service on save for new entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<ISysUserDepart>>();
      const sysUserDepart = { id: 'ABC' };
      jest.spyOn(sysUserDepartFormService, 'getSysUserDepart').mockReturnValue({ id: null });
      jest.spyOn(sysUserDepartService, 'create').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ sysUserDepart: null });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: sysUserDepart }));
      saveSubject.complete();

      // THEN
      expect(sysUserDepartFormService.getSysUserDepart).toHaveBeenCalled();
      expect(sysUserDepartService.create).toHaveBeenCalled();
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).toHaveBeenCalled();
    });

    it('Should set isSaving to false on error', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<ISysUserDepart>>();
      const sysUserDepart = { id: 'ABC' };
      jest.spyOn(sysUserDepartService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ sysUserDepart });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.error('This is an error!');

      // THEN
      expect(sysUserDepartService.update).toHaveBeenCalled();
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).not.toHaveBeenCalled();
    });
  });
});
