import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { SysTenantDetailComponent } from './sys-tenant-detail.component';

describe('SysTenant Management Detail Component', () => {
  let comp: SysTenantDetailComponent;
  let fixture: ComponentFixture<SysTenantDetailComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [SysTenantDetailComponent],
      providers: [
        {
          provide: ActivatedRoute,
          useValue: { data: of({ sysTenant: { id: 'ABC' } }) },
        },
      ],
    })
      .overrideTemplate(SysTenantDetailComponent, '')
      .compileComponents();
    fixture = TestBed.createComponent(SysTenantDetailComponent);
    comp = fixture.componentInstance;
  });

  describe('OnInit', () => {
    it('Should load sysTenant on init', () => {
      // WHEN
      comp.ngOnInit();

      // THEN
      expect(comp.sysTenant).toEqual(expect.objectContaining({ id: 'ABC' }));
    });
  });
});
