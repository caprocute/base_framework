import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { SysDataSourceDetailComponent } from './sys-data-source-detail.component';

describe('SysDataSource Management Detail Component', () => {
  let comp: SysDataSourceDetailComponent;
  let fixture: ComponentFixture<SysDataSourceDetailComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [SysDataSourceDetailComponent],
      providers: [
        {
          provide: ActivatedRoute,
          useValue: { data: of({ sysDataSource: { id: 'ABC' } }) },
        },
      ],
    })
      .overrideTemplate(SysDataSourceDetailComponent, '')
      .compileComponents();
    fixture = TestBed.createComponent(SysDataSourceDetailComponent);
    comp = fixture.componentInstance;
  });

  describe('OnInit', () => {
    it('Should load sysDataSource on init', () => {
      // WHEN
      comp.ngOnInit();

      // THEN
      expect(comp.sysDataSource).toEqual(expect.objectContaining({ id: 'ABC' }));
    });
  });
});
