import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { ISysPermission } from '../sys-permission.model';

@Component({
  selector: 'jhi-sys-permission-detail',
  templateUrl: './sys-permission-detail.component.html',
})
export class SysPermissionDetailComponent implements OnInit {
  sysPermission: ISysPermission | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ sysPermission }) => {
      this.sysPermission = sysPermission;
    });
  }

  previousState(): void {
    window.history.back();
  }
}
