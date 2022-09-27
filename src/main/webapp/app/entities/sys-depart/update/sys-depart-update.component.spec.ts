import { ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { FormBuilder } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { RouterTestingModule } from '@angular/router/testing';
import { of, Subject, from } from 'rxjs';

import { SysDepartFormService } from './sys-depart-form.service';
import { SysDepartService } from '../service/sys-depart.service';
import { ISysDepart } from '../sys-depart.model';

import { SysDepartUpdateComponent } from './sys-depart-update.component';

describe('SysDepart Management Update Component', () => {
  let comp: SysDepartUpdateComponent;
  let fixture: ComponentFixture<SysDepartUpdateComponent>;
  let activatedRoute: ActivatedRoute;
  let sysDepartFormService: SysDepartFormService;
  let sysDepartService: SysDepartService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule, RouterTestingModule.withRoutes([])],
      declarations: [SysDepartUpdateComponent],
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
      .overrideTemplate(SysDepartUpdateComponent, '')
      .compileComponents();

    fixture = TestBed.createComponent(SysDepartUpdateComponent);
    activatedRoute = TestBed.inject(ActivatedRoute);
    sysDepartFormService = TestBed.inject(SysDepartFormService);
    sysDepartService = TestBed.inject(SysDepartService);

    comp = fixture.componentInstance;
  });

  describe('ngOnInit', () => {
    it('Should update editForm', () => {
      const sysDepart: ISysDepart = { id: 'CBA' };

      activatedRoute.data = of({ sysDepart });
      comp.ngOnInit();

      expect(comp.sysDepart).toEqual(sysDepart);
    });
  });

  describe('save', () => {
    it('Should call update service on save for existing entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<ISysDepart>>();
      const sysDepart = { id: 'ABC' };
      jest.spyOn(sysDepartFormService, 'getSysDepart').mockReturnValue(sysDepart);
      jest.spyOn(sysDepartService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ sysDepart });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: sysDepart }));
      saveSubject.complete();

      // THEN
      expect(sysDepartFormService.getSysDepart).toHaveBeenCalled();
      expect(comp.previousState).toHaveBeenCalled();
      expect(sysDepartService.update).toHaveBeenCalledWith(expect.objectContaining(sysDepart));
      expect(comp.isSaving).toEqual(false);
    });

    it('Should call create service on save for new entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<ISysDepart>>();
      const sysDepart = { id: 'ABC' };
      jest.spyOn(sysDepartFormService, 'getSysDepart').mockReturnValue({ id: null });
      jest.spyOn(sysDepartService, 'create').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ sysDepart: null });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: sysDepart }));
      saveSubject.complete();

      // THEN
      expect(sysDepartFormService.getSysDepart).toHaveBeenCalled();
      expect(sysDepartService.create).toHaveBeenCalled();
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).toHaveBeenCalled();
    });

    it('Should set isSaving to false on error', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<ISysDepart>>();
      const sysDepart = { id: 'ABC' };
      jest.spyOn(sysDepartService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ sysDepart });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.error('This is an error!');

      // THEN
      expect(sysDepartService.update).toHaveBeenCalled();
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).not.toHaveBeenCalled();
    });
  });
});
