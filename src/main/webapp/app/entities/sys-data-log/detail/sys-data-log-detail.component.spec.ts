import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { SysDataLogDetailComponent } from './sys-data-log-detail.component';

describe('SysDataLog Management Detail Component', () => {
  let comp: SysDataLogDetailComponent;
  let fixture: ComponentFixture<SysDataLogDetailComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [SysDataLogDetailComponent],
      providers: [
        {
          provide: ActivatedRoute,
          useValue: { data: of({ sysDataLog: { id: 'ABC' } }) },
        },
      ],
    })
      .overrideTemplate(SysDataLogDetailComponent, '')
      .compileComponents();
    fixture = TestBed.createComponent(SysDataLogDetailComponent);
    comp = fixture.componentInstance;
  });

  describe('OnInit', () => {
    it('Should load sysDataLog on init', () => {
      // WHEN
      comp.ngOnInit();

      // THEN
      expect(comp.sysDataLog).toEqual(expect.objectContaining({ id: 'ABC' }));
    });
  });
});
