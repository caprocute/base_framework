import { ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { FormBuilder } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { RouterTestingModule } from '@angular/router/testing';
import { of, Subject, from } from 'rxjs';

import { SysTenantFormService } from './sys-tenant-form.service';
import { SysTenantService } from '../service/sys-tenant.service';
import { ISysTenant } from '../sys-tenant.model';

import { SysTenantUpdateComponent } from './sys-tenant-update.component';

describe('SysTenant Management Update Component', () => {
  let comp: SysTenantUpdateComponent;
  let fixture: ComponentFixture<SysTenantUpdateComponent>;
  let activatedRoute: ActivatedRoute;
  let sysTenantFormService: SysTenantFormService;
  let sysTenantService: SysTenantService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule, RouterTestingModule.withRoutes([])],
      declarations: [SysTenantUpdateComponent],
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
      .overrideTemplate(SysTenantUpdateComponent, '')
      .compileComponents();

    fixture = TestBed.createComponent(SysTenantUpdateComponent);
    activatedRoute = TestBed.inject(ActivatedRoute);
    sysTenantFormService = TestBed.inject(SysTenantFormService);
    sysTenantService = TestBed.inject(SysTenantService);

    comp = fixture.componentInstance;
  });

  describe('ngOnInit', () => {
    it('Should update editForm', () => {
      const sysTenant: ISysTenant = { id: 'CBA' };

      activatedRoute.data = of({ sysTenant });
      comp.ngOnInit();

      expect(comp.sysTenant).toEqual(sysTenant);
    });
  });

  describe('save', () => {
    it('Should call update service on save for existing entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<ISysTenant>>();
      const sysTenant = { id: 'ABC' };
      jest.spyOn(sysTenantFormService, 'getSysTenant').mockReturnValue(sysTenant);
      jest.spyOn(sysTenantService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ sysTenant });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: sysTenant }));
      saveSubject.complete();

      // THEN
      expect(sysTenantFormService.getSysTenant).toHaveBeenCalled();
      expect(comp.previousState).toHaveBeenCalled();
      expect(sysTenantService.update).toHaveBeenCalledWith(expect.objectContaining(sysTenant));
      expect(comp.isSaving).toEqual(false);
    });

    it('Should call create service on save for new entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<ISysTenant>>();
      const sysTenant = { id: 'ABC' };
      jest.spyOn(sysTenantFormService, 'getSysTenant').mockReturnValue({ id: null });
      jest.spyOn(sysTenantService, 'create').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ sysTenant: null });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: sysTenant }));
      saveSubject.complete();

      // THEN
      expect(sysTenantFormService.getSysTenant).toHaveBeenCalled();
      expect(sysTenantService.create).toHaveBeenCalled();
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).toHaveBeenCalled();
    });

    it('Should set isSaving to false on error', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<ISysTenant>>();
      const sysTenant = { id: 'ABC' };
      jest.spyOn(sysTenantService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ sysTenant });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.error('This is an error!');

      // THEN
      expect(sysTenantService.update).toHaveBeenCalled();
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).not.toHaveBeenCalled();
    });
  });
});
