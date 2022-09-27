import { ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { FormBuilder } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { RouterTestingModule } from '@angular/router/testing';
import { of, Subject, from } from 'rxjs';

import { SysLogFormService } from './sys-log-form.service';
import { SysLogService } from '../service/sys-log.service';
import { ISysLog } from '../sys-log.model';

import { SysLogUpdateComponent } from './sys-log-update.component';

describe('SysLog Management Update Component', () => {
  let comp: SysLogUpdateComponent;
  let fixture: ComponentFixture<SysLogUpdateComponent>;
  let activatedRoute: ActivatedRoute;
  let sysLogFormService: SysLogFormService;
  let sysLogService: SysLogService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule, RouterTestingModule.withRoutes([])],
      declarations: [SysLogUpdateComponent],
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
      .overrideTemplate(SysLogUpdateComponent, '')
      .compileComponents();

    fixture = TestBed.createComponent(SysLogUpdateComponent);
    activatedRoute = TestBed.inject(ActivatedRoute);
    sysLogFormService = TestBed.inject(SysLogFormService);
    sysLogService = TestBed.inject(SysLogService);

    comp = fixture.componentInstance;
  });

  describe('ngOnInit', () => {
    it('Should update editForm', () => {
      const sysLog: ISysLog = { id: 'CBA' };

      activatedRoute.data = of({ sysLog });
      comp.ngOnInit();

      expect(comp.sysLog).toEqual(sysLog);
    });
  });

  describe('save', () => {
    it('Should call update service on save for existing entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<ISysLog>>();
      const sysLog = { id: 'ABC' };
      jest.spyOn(sysLogFormService, 'getSysLog').mockReturnValue(sysLog);
      jest.spyOn(sysLogService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ sysLog });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: sysLog }));
      saveSubject.complete();

      // THEN
      expect(sysLogFormService.getSysLog).toHaveBeenCalled();
      expect(comp.previousState).toHaveBeenCalled();
      expect(sysLogService.update).toHaveBeenCalledWith(expect.objectContaining(sysLog));
      expect(comp.isSaving).toEqual(false);
    });

    it('Should call create service on save for new entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<ISysLog>>();
      const sysLog = { id: 'ABC' };
      jest.spyOn(sysLogFormService, 'getSysLog').mockReturnValue({ id: null });
      jest.spyOn(sysLogService, 'create').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ sysLog: null });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: sysLog }));
      saveSubject.complete();

      // THEN
      expect(sysLogFormService.getSysLog).toHaveBeenCalled();
      expect(sysLogService.create).toHaveBeenCalled();
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).toHaveBeenCalled();
    });

    it('Should set isSaving to false on error', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<ISysLog>>();
      const sysLog = { id: 'ABC' };
      jest.spyOn(sysLogService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ sysLog });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.error('This is an error!');

      // THEN
      expect(sysLogService.update).toHaveBeenCalled();
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).not.toHaveBeenCalled();
    });
  });
});
