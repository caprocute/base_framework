import { ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { FormBuilder } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { RouterTestingModule } from '@angular/router/testing';
import { of, Subject, from } from 'rxjs';

import { SysRoleIndexFormService } from './sys-role-index-form.service';
import { SysRoleIndexService } from '../service/sys-role-index.service';
import { ISysRoleIndex } from '../sys-role-index.model';

import { SysRoleIndexUpdateComponent } from './sys-role-index-update.component';

describe('SysRoleIndex Management Update Component', () => {
  let comp: SysRoleIndexUpdateComponent;
  let fixture: ComponentFixture<SysRoleIndexUpdateComponent>;
  let activatedRoute: ActivatedRoute;
  let sysRoleIndexFormService: SysRoleIndexFormService;
  let sysRoleIndexService: SysRoleIndexService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule, RouterTestingModule.withRoutes([])],
      declarations: [SysRoleIndexUpdateComponent],
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
      .overrideTemplate(SysRoleIndexUpdateComponent, '')
      .compileComponents();

    fixture = TestBed.createComponent(SysRoleIndexUpdateComponent);
    activatedRoute = TestBed.inject(ActivatedRoute);
    sysRoleIndexFormService = TestBed.inject(SysRoleIndexFormService);
    sysRoleIndexService = TestBed.inject(SysRoleIndexService);

    comp = fixture.componentInstance;
  });

  describe('ngOnInit', () => {
    it('Should update editForm', () => {
      const sysRoleIndex: ISysRoleIndex = { id: 'CBA' };

      activatedRoute.data = of({ sysRoleIndex });
      comp.ngOnInit();

      expect(comp.sysRoleIndex).toEqual(sysRoleIndex);
    });
  });

  describe('save', () => {
    it('Should call update service on save for existing entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<ISysRoleIndex>>();
      const sysRoleIndex = { id: 'ABC' };
      jest.spyOn(sysRoleIndexFormService, 'getSysRoleIndex').mockReturnValue(sysRoleIndex);
      jest.spyOn(sysRoleIndexService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ sysRoleIndex });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: sysRoleIndex }));
      saveSubject.complete();

      // THEN
      expect(sysRoleIndexFormService.getSysRoleIndex).toHaveBeenCalled();
      expect(comp.previousState).toHaveBeenCalled();
      expect(sysRoleIndexService.update).toHaveBeenCalledWith(expect.objectContaining(sysRoleIndex));
      expect(comp.isSaving).toEqual(false);
    });

    it('Should call create service on save for new entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<ISysRoleIndex>>();
      const sysRoleIndex = { id: 'ABC' };
      jest.spyOn(sysRoleIndexFormService, 'getSysRoleIndex').mockReturnValue({ id: null });
      jest.spyOn(sysRoleIndexService, 'create').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ sysRoleIndex: null });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: sysRoleIndex }));
      saveSubject.complete();

      // THEN
      expect(sysRoleIndexFormService.getSysRoleIndex).toHaveBeenCalled();
      expect(sysRoleIndexService.create).toHaveBeenCalled();
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).toHaveBeenCalled();
    });

    it('Should set isSaving to false on error', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<ISysRoleIndex>>();
      const sysRoleIndex = { id: 'ABC' };
      jest.spyOn(sysRoleIndexService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ sysRoleIndex });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.error('This is an error!');

      // THEN
      expect(sysRoleIndexService.update).toHaveBeenCalled();
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).not.toHaveBeenCalled();
    });
  });
});
