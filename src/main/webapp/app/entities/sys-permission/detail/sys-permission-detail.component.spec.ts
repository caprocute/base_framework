import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { SysPermissionDetailComponent } from './sys-permission-detail.component';

describe('SysPermission Management Detail Component', () => {
  let comp: SysPermissionDetailComponent;
  let fixture: ComponentFixture<SysPermissionDetailComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [SysPermissionDetailComponent],
      providers: [
        {
          provide: ActivatedRoute,
          useValue: { data: of({ sysPermission: { id: 'ABC' } }) },
        },
      ],
    })
      .overrideTemplate(SysPermissionDetailComponent, '')
      .compileComponents();
    fixture = TestBed.createComponent(SysPermissionDetailComponent);
    comp = fixture.componentInstance;
  });

  describe('OnInit', () => {
    it('Should load sysPermission on init', () => {
      // WHEN
      comp.ngOnInit();

      // THEN
      expect(comp.sysPermission).toEqual(expect.objectContaining({ id: 'ABC' }));
    });
  });
});
