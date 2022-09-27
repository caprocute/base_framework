import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { SysDepartDetailComponent } from './sys-depart-detail.component';

describe('SysDepart Management Detail Component', () => {
  let comp: SysDepartDetailComponent;
  let fixture: ComponentFixture<SysDepartDetailComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [SysDepartDetailComponent],
      providers: [
        {
          provide: ActivatedRoute,
          useValue: { data: of({ sysDepart: { id: 'ABC' } }) },
        },
      ],
    })
      .overrideTemplate(SysDepartDetailComponent, '')
      .compileComponents();
    fixture = TestBed.createComponent(SysDepartDetailComponent);
    comp = fixture.componentInstance;
  });

  describe('OnInit', () => {
    it('Should load sysDepart on init', () => {
      // WHEN
      comp.ngOnInit();

      // THEN
      expect(comp.sysDepart).toEqual(expect.objectContaining({ id: 'ABC' }));
    });
  });
});
