import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { SysRoleIndexDetailComponent } from './sys-role-index-detail.component';

describe('SysRoleIndex Management Detail Component', () => {
  let comp: SysRoleIndexDetailComponent;
  let fixture: ComponentFixture<SysRoleIndexDetailComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [SysRoleIndexDetailComponent],
      providers: [
        {
          provide: ActivatedRoute,
          useValue: { data: of({ sysRoleIndex: { id: 'ABC' } }) },
        },
      ],
    })
      .overrideTemplate(SysRoleIndexDetailComponent, '')
      .compileComponents();
    fixture = TestBed.createComponent(SysRoleIndexDetailComponent);
    comp = fixture.componentInstance;
  });

  describe('OnInit', () => {
    it('Should load sysRoleIndex on init', () => {
      // WHEN
      comp.ngOnInit();

      // THEN
      expect(comp.sysRoleIndex).toEqual(expect.objectContaining({ id: 'ABC' }));
    });
  });
});
