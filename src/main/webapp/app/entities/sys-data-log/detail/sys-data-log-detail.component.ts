import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { ISysDataLog } from '../sys-data-log.model';

@Component({
  selector: 'jhi-sys-data-log-detail',
  templateUrl: './sys-data-log-detail.component.html',
})
export class SysDataLogDetailComponent implements OnInit {
  sysDataLog: ISysDataLog | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ sysDataLog }) => {
      this.sysDataLog = sysDataLog;
    });
  }

  previousState(): void {
    window.history.back();
  }
}
