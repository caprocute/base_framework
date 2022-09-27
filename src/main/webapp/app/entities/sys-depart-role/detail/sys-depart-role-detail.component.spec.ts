import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { SysDepartRoleDetailComponent } from './sys-depart-role-detail.component';

describe('SysDepartRole Management Detail Component', () => {
  let comp: SysDepartRoleDetailComponent;
  let fixture: ComponentFixture<SysDepartRoleDetailComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [SysDepartRoleDetailComponent],
      providers: [
        {
          provide: ActivatedRoute,
          useValue: { data: of({ sysDepartRole: { id: 'ABC' } }) },
        },
      ],
    })
      .overrideTemplate(SysDepartRoleDetailComponent, '')
      .compileComponents();
    fixture = TestBed.createComponent(SysDepartRoleDetailComponent);
    comp = fixture.componentInstance;
  });

  describe('OnInit', () => {
    it('Should load sysDepartRole on init', () => {
      // WHEN
      comp.ngOnInit();

      // THEN
      expect(comp.sysDepartRole).toEqual(expect.objectContaining({ id: 'ABC' }));
    });
  });
});
