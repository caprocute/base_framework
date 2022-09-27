import { ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { FormBuilder } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { RouterTestingModule } from '@angular/router/testing';
import { of, Subject, from } from 'rxjs';

import { SysDepartRoleFormService } from './sys-depart-role-form.service';
import { SysDepartRoleService } from '../service/sys-depart-role.service';
import { ISysDepartRole } from '../sys-depart-role.model';

import { SysDepartRoleUpdateComponent } from './sys-depart-role-update.component';

describe('SysDepartRole Management Update Component', () => {
  let comp: SysDepartRoleUpdateComponent;
  let fixture: ComponentFixture<SysDepartRoleUpdateComponent>;
  let activatedRoute: ActivatedRoute;
  let sysDepartRoleFormService: SysDepartRoleFormService;
  let sysDepartRoleService: SysDepartRoleService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule, RouterTestingModule.withRoutes([])],
      declarations: [SysDepartRoleUpdateComponent],
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
      .overrideTemplate(SysDepartRoleUpdateComponent, '')
      .compileComponents();

    fixture = TestBed.createComponent(SysDepartRoleUpdateComponent);
    activatedRoute = TestBed.inject(ActivatedRoute);
    sysDepartRoleFormService = TestBed.inject(SysDepartRoleFormService);
    sysDepartRoleService = TestBed.inject(SysDepartRoleService);

    comp = fixture.componentInstance;
  });

  describe('ngOnInit', () => {
    it('Should update editForm', () => {
      const sysDepartRole: ISysDepartRole = { id: 'CBA' };

      activatedRoute.data = of({ sysDepartRole });
      comp.ngOnInit();

      expect(comp.sysDepartRole).toEqual(sysDepartRole);
    });
  });

  describe('save', () => {
    it('Should call update service on save for existing entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<ISysDepartRole>>();
      const sysDepartRole = { id: 'ABC' };
      jest.spyOn(sysDepartRoleFormService, 'getSysDepartRole').mockReturnValue(sysDepartRole);
      jest.spyOn(sysDepartRoleService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ sysDepartRole });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: sysDepartRole }));
      saveSubject.complete();

      // THEN
      expect(sysDepartRoleFormService.getSysDepartRole).toHaveBeenCalled();
      expect(comp.previousState).toHaveBeenCalled();
      expect(sysDepartRoleService.update).toHaveBeenCalledWith(expect.objectContaining(sysDepartRole));
      expect(comp.isSaving).toEqual(false);
    });

    it('Should call create service on save for new entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<ISysDepartRole>>();
      const sysDepartRole = { id: 'ABC' };
      jest.spyOn(sysDepartRoleFormService, 'getSysDepartRole').mockReturnValue({ id: null });
      jest.spyOn(sysDepartRoleService, 'create').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ sysDepartRole: null });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: sysDepartRole }));
      saveSubject.complete();

      // THEN
      expect(sysDepartRoleFormService.getSysDepartRole).toHaveBeenCalled();
      expect(sysDepartRoleService.create).toHaveBeenCalled();
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).toHaveBeenCalled();
    });

    it('Should set isSaving to false on error', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<ISysDepartRole>>();
      const sysDepartRole = { id: 'ABC' };
      jest.spyOn(sysDepartRoleService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ sysDepartRole });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.error('This is an error!');

      // THEN
      expect(sysDepartRoleService.update).toHaveBeenCalled();
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).not.toHaveBeenCalled();
    });
  });
});
