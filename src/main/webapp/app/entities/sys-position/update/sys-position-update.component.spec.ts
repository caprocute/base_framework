import { ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { FormBuilder } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { RouterTestingModule } from '@angular/router/testing';
import { of, Subject, from } from 'rxjs';

import { SysPositionFormService } from './sys-position-form.service';
import { SysPositionService } from '../service/sys-position.service';
import { ISysPosition } from '../sys-position.model';

import { SysPositionUpdateComponent } from './sys-position-update.component';

describe('SysPosition Management Update Component', () => {
  let comp: SysPositionUpdateComponent;
  let fixture: ComponentFixture<SysPositionUpdateComponent>;
  let activatedRoute: ActivatedRoute;
  let sysPositionFormService: SysPositionFormService;
  let sysPositionService: SysPositionService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule, RouterTestingModule.withRoutes([])],
      declarations: [SysPositionUpdateComponent],
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
      .overrideTemplate(SysPositionUpdateComponent, '')
      .compileComponents();

    fixture = TestBed.createComponent(SysPositionUpdateComponent);
    activatedRoute = TestBed.inject(ActivatedRoute);
    sysPositionFormService = TestBed.inject(SysPositionFormService);
    sysPositionService = TestBed.inject(SysPositionService);

    comp = fixture.componentInstance;
  });

  describe('ngOnInit', () => {
    it('Should update editForm', () => {
      const sysPosition: ISysPosition = { id: 'CBA' };

      activatedRoute.data = of({ sysPosition });
      comp.ngOnInit();

      expect(comp.sysPosition).toEqual(sysPosition);
    });
  });

  describe('save', () => {
    it('Should call update service on save for existing entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<ISysPosition>>();
      const sysPosition = { id: 'ABC' };
      jest.spyOn(sysPositionFormService, 'getSysPosition').mockReturnValue(sysPosition);
      jest.spyOn(sysPositionService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ sysPosition });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: sysPosition }));
      saveSubject.complete();

      // THEN
      expect(sysPositionFormService.getSysPosition).toHaveBeenCalled();
      expect(comp.previousState).toHaveBeenCalled();
      expect(sysPositionService.update).toHaveBeenCalledWith(expect.objectContaining(sysPosition));
      expect(comp.isSaving).toEqual(false);
    });

    it('Should call create service on save for new entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<ISysPosition>>();
      const sysPosition = { id: 'ABC' };
      jest.spyOn(sysPositionFormService, 'getSysPosition').mockReturnValue({ id: null });
      jest.spyOn(sysPositionService, 'create').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ sysPosition: null });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: sysPosition }));
      saveSubject.complete();

      // THEN
      expect(sysPositionFormService.getSysPosition).toHaveBeenCalled();
      expect(sysPositionService.create).toHaveBeenCalled();
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).toHaveBeenCalled();
    });

    it('Should set isSaving to false on error', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<ISysPosition>>();
      const sysPosition = { id: 'ABC' };
      jest.spyOn(sysPositionService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ sysPosition });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.error('This is an error!');

      // THEN
      expect(sysPositionService.update).toHaveBeenCalled();
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).not.toHaveBeenCalled();
    });
  });
});
