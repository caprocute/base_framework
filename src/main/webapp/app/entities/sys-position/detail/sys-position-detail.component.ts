import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { ISysPosition } from '../sys-position.model';

@Component({
  selector: 'jhi-sys-position-detail',
  templateUrl: './sys-position-detail.component.html',
})
export class SysPositionDetailComponent implements OnInit {
  sysPosition: ISysPosition | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ sysPosition }) => {
      this.sysPosition = sysPosition;
    });
  }

  previousState(): void {
    window.history.back();
  }
}
