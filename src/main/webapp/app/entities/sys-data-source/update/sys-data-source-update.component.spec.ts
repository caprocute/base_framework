import { ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { FormBuilder } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { RouterTestingModule } from '@angular/router/testing';
import { of, Subject, from } from 'rxjs';

import { SysDataSourceFormService } from './sys-data-source-form.service';
import { SysDataSourceService } from '../service/sys-data-source.service';
import { ISysDataSource } from '../sys-data-source.model';

import { SysDataSourceUpdateComponent } from './sys-data-source-update.component';

describe('SysDataSource Management Update Component', () => {
  let comp: SysDataSourceUpdateComponent;
  let fixture: ComponentFixture<SysDataSourceUpdateComponent>;
  let activatedRoute: ActivatedRoute;
  let sysDataSourceFormService: SysDataSourceFormService;
  let sysDataSourceService: SysDataSourceService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule, RouterTestingModule.withRoutes([])],
      declarations: [SysDataSourceUpdateComponent],
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
      .overrideTemplate(SysDataSourceUpdateComponent, '')
      .compileComponents();

    fixture = TestBed.createComponent(SysDataSourceUpdateComponent);
    activatedRoute = TestBed.inject(ActivatedRoute);
    sysDataSourceFormService = TestBed.inject(SysDataSourceFormService);
    sysDataSourceService = TestBed.inject(SysDataSourceService);

    comp = fixture.componentInstance;
  });

  describe('ngOnInit', () => {
    it('Should update editForm', () => {
      const sysDataSource: ISysDataSource = { id: 'CBA' };

      activatedRoute.data = of({ sysDataSource });
      comp.ngOnInit();

      expect(comp.sysDataSource).toEqual(sysDataSource);
    });
  });

  describe('save', () => {
    it('Should call update service on save for existing entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<ISysDataSource>>();
      const sysDataSource = { id: 'ABC' };
      jest.spyOn(sysDataSourceFormService, 'getSysDataSource').mockReturnValue(sysDataSource);
      jest.spyOn(sysDataSourceService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ sysDataSource });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: sysDataSource }));
      saveSubject.complete();

      // THEN
      expect(sysDataSourceFormService.getSysDataSource).toHaveBeenCalled();
      expect(comp.previousState).toHaveBeenCalled();
      expect(sysDataSourceService.update).toHaveBeenCalledWith(expect.objectContaining(sysDataSource));
      expect(comp.isSaving).toEqual(false);
    });

    it('Should call create service on save for new entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<ISysDataSource>>();
      const sysDataSource = { id: 'ABC' };
      jest.spyOn(sysDataSourceFormService, 'getSysDataSource').mockReturnValue({ id: null });
      jest.spyOn(sysDataSourceService, 'create').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ sysDataSource: null });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: sysDataSource }));
      saveSubject.complete();

      // THEN
      expect(sysDataSourceFormService.getSysDataSource).toHaveBeenCalled();
      expect(sysDataSourceService.create).toHaveBeenCalled();
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).toHaveBeenCalled();
    });

    it('Should set isSaving to false on error', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<ISysDataSource>>();
      const sysDataSource = { id: 'ABC' };
      jest.spyOn(sysDataSourceService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ sysDataSource });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.error('This is an error!');

      // THEN
      expect(sysDataSourceService.update).toHaveBeenCalled();
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).not.toHaveBeenCalled();
    });
  });
});
