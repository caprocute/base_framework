import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { SysRolePermissionDetailComponent } from './sys-role-permission-detail.component';

describe('SysRolePermission Management Detail Component', () => {
  let comp: SysRolePermissionDetailComponent;
  let fixture: ComponentFixture<SysRolePermissionDetailComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [SysRolePermissionDetailComponent],
      providers: [
        {
          provide: ActivatedRoute,
          useValue: { data: of({ sysRolePermission: { id: 'ABC' } }) },
        },
      ],
    })
      .overrideTemplate(SysRolePermissionDetailComponent, '')
      .compileComponents();
    fixture = TestBed.createComponent(SysRolePermissionDetailComponent);
    comp = fixture.componentInstance;
  });

  describe('OnInit', () => {
    it('Should load sysRolePermission on init', () => {
      // WHEN
      comp.ngOnInit();

      // THEN
      expect(comp.sysRolePermission).toEqual(expect.objectContaining({ id: 'ABC' }));
    });
  });
});
