import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { SysUserRoleDetailComponent } from './sys-user-role-detail.component';

describe('SysUserRole Management Detail Component', () => {
  let comp: SysUserRoleDetailComponent;
  let fixture: ComponentFixture<SysUserRoleDetailComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [SysUserRoleDetailComponent],
      providers: [
        {
          provide: ActivatedRoute,
          useValue: { data: of({ sysUserRole: { id: 'ABC' } }) },
        },
      ],
    })
      .overrideTemplate(SysUserRoleDetailComponent, '')
      .compileComponents();
    fixture = TestBed.createComponent(SysUserRoleDetailComponent);
    comp = fixture.componentInstance;
  });

  describe('OnInit', () => {
    it('Should load sysUserRole on init', () => {
      // WHEN
      comp.ngOnInit();

      // THEN
      expect(comp.sysUserRole).toEqual(expect.objectContaining({ id: 'ABC' }));
    });
  });
});
