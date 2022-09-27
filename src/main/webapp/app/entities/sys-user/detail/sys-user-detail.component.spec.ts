import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { SysUserDetailComponent } from './sys-user-detail.component';

describe('SysUser Management Detail Component', () => {
  let comp: SysUserDetailComponent;
  let fixture: ComponentFixture<SysUserDetailComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [SysUserDetailComponent],
      providers: [
        {
          provide: ActivatedRoute,
          useValue: { data: of({ sysUser: { id: 'ABC' } }) },
        },
      ],
    })
      .overrideTemplate(SysUserDetailComponent, '')
      .compileComponents();
    fixture = TestBed.createComponent(SysUserDetailComponent);
    comp = fixture.componentInstance;
  });

  describe('OnInit', () => {
    it('Should load sysUser on init', () => {
      // WHEN
      comp.ngOnInit();

      // THEN
      expect(comp.sysUser).toEqual(expect.objectContaining({ id: 'ABC' }));
    });
  });
});
