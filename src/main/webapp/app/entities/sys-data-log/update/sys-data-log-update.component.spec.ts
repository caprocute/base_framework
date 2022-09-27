import { ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { FormBuilder } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { RouterTestingModule } from '@angular/router/testing';
import { of, Subject, from } from 'rxjs';

import { SysDataLogFormService } from './sys-data-log-form.service';
import { SysDataLogService } from '../service/sys-data-log.service';
import { ISysDataLog } from '../sys-data-log.model';

import { SysDataLogUpdateComponent } from './sys-data-log-update.component';

describe('SysDataLog Management Update Component', () => {
  let comp: SysDataLogUpdateComponent;
  let fixture: ComponentFixture<SysDataLogUpdateComponent>;
  let activatedRoute: ActivatedRoute;
  let sysDataLogFormService: SysDataLogFormService;
  let sysDataLogService: SysDataLogService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule, RouterTestingModule.withRoutes([])],
      declarations: [SysDataLogUpdateComponent],
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
      .overrideTemplate(SysDataLogUpdateComponent, '')
      .compileComponents();

    fixture = TestBed.createComponent(SysDataLogUpdateComponent);
    activatedRoute = TestBed.inject(ActivatedRoute);
    sysDataLogFormService = TestBed.inject(SysDataLogFormService);
    sysDataLogService = TestBed.inject(SysDataLogService);

    comp = fixture.componentInstance;
  });

  describe('ngOnInit', () => {
    it('Should update editForm', () => {
      const sysDataLog: ISysDataLog = { id: 'CBA' };

      activatedRoute.data = of({ sysDataLog });
      comp.ngOnInit();

      expect(comp.sysDataLog).toEqual(sysDataLog);
    });
  });

  describe('save', () => {
    it('Should call update service on save for existing entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<ISysDataLog>>();
      const sysDataLog = { id: 'ABC' };
      jest.spyOn(sysDataLogFormService, 'getSysDataLog').mockReturnValue(sysDataLog);
      jest.spyOn(sysDataLogService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ sysDataLog });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: sysDataLog }));
      saveSubject.complete();

      // THEN
      expect(sysDataLogFormService.getSysDataLog).toHaveBeenCalled();
      expect(comp.previousState).toHaveBeenCalled();
      expect(sysDataLogService.update).toHaveBeenCalledWith(expect.objectContaining(sysDataLog));
      expect(comp.isSaving).toEqual(false);
    });

    it('Should call create service on save for new entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<ISysDataLog>>();
      const sysDataLog = { id: 'ABC' };
      jest.spyOn(sysDataLogFormService, 'getSysDataLog').mockReturnValue({ id: null });
      jest.spyOn(sysDataLogService, 'create').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ sysDataLog: null });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: sysDataLog }));
      saveSubject.complete();

      // THEN
      expect(sysDataLogFormService.getSysDataLog).toHaveBeenCalled();
      expect(sysDataLogService.create).toHaveBeenCalled();
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).toHaveBeenCalled();
    });

    it('Should set isSaving to false on error', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<ISysDataLog>>();
      const sysDataLog = { id: 'ABC' };
      jest.spyOn(sysDataLogService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ sysDataLog });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.error('This is an error!');

      // THEN
      expect(sysDataLogService.update).toHaveBeenCalled();
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).not.toHaveBeenCalled();
    });
  });
});
