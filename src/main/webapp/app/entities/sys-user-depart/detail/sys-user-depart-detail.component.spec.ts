import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { SysUserDepartDetailComponent } from './sys-user-depart-detail.component';

describe('SysUserDepart Management Detail Component', () => {
  let comp: SysUserDepartDetailComponent;
  let fixture: ComponentFixture<SysUserDepartDetailComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [SysUserDepartDetailComponent],
      providers: [
        {
          provide: ActivatedRoute,
          useValue: { data: of({ sysUserDepart: { id: 'ABC' } }) },
        },
      ],
    })
      .overrideTemplate(SysUserDepartDetailComponent, '')
      .compileComponents();
    fixture = TestBed.createComponent(SysUserDepartDetailComponent);
    comp = fixture.componentInstance;
  });

  describe('OnInit', () => {
    it('Should load sysUserDepart on init', () => {
      // WHEN
      comp.ngOnInit();

      // THEN
      expect(comp.sysUserDepart).toEqual(expect.objectContaining({ id: 'ABC' }));
    });
  });
});
