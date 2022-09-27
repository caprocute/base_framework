import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { SysPositionDetailComponent } from './sys-position-detail.component';

describe('SysPosition Management Detail Component', () => {
  let comp: SysPositionDetailComponent;
  let fixture: ComponentFixture<SysPositionDetailComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [SysPositionDetailComponent],
      providers: [
        {
          provide: ActivatedRoute,
          useValue: { data: of({ sysPosition: { id: 'ABC' } }) },
        },
      ],
    })
      .overrideTemplate(SysPositionDetailComponent, '')
      .compileComponents();
    fixture = TestBed.createComponent(SysPositionDetailComponent);
    comp = fixture.componentInstance;
  });

  describe('OnInit', () => {
    it('Should load sysPosition on init', () => {
      // WHEN
      comp.ngOnInit();

      // THEN
      expect(comp.sysPosition).toEqual(expect.objectContaining({ id: 'ABC' }));
    });
  });
});
