import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { ISysDataSource } from '../sys-data-source.model';

@Component({
  selector: 'jhi-sys-data-source-detail',
  templateUrl: './sys-data-source-detail.component.html',
})
export class SysDataSourceDetailComponent implements OnInit {
  sysDataSource: ISysDataSource | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ sysDataSource }) => {
      this.sysDataSource = sysDataSource;
    });
  }

  previousState(): void {
    window.history.back();
  }
}
