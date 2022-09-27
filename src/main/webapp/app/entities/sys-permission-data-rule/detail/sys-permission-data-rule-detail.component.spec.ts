import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { SysPermissionDataRuleDetailComponent } from './sys-permission-data-rule-detail.component';

describe('SysPermissionDataRule Management Detail Component', () => {
  let comp: SysPermissionDataRuleDetailComponent;
  let fixture: ComponentFixture<SysPermissionDataRuleDetailComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [SysPermissionDataRuleDetailComponent],
      providers: [
        {
          provide: ActivatedRoute,
          useValue: { data: of({ sysPermissionDataRule: { id: 'ABC' } }) },
        },
      ],
    })
      .overrideTemplate(SysPermissionDataRuleDetailComponent, '')
      .compileComponents();
    fixture = TestBed.createComponent(SysPermissionDataRuleDetailComponent);
    comp = fixture.componentInstance;
  });

  describe('OnInit', () => {
    it('Should load sysPermissionDataRule on init', () => {
      // WHEN
      comp.ngOnInit();

      // THEN
      expect(comp.sysPermissionDataRule).toEqual(expect.objectContaining({ id: 'ABC' }));
    });
  });
});
