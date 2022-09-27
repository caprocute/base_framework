import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { ISysRolePermission } from '../sys-role-permission.model';

@Component({
  selector: 'jhi-sys-role-permission-detail',
  templateUrl: './sys-role-permission-detail.component.html',
})
export class SysRolePermissionDetailComponent implements OnInit {
  sysRolePermission: ISysRolePermission | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ sysRolePermission }) => {
      this.sysRolePermission = sysRolePermission;
    });
  }

  previousState(): void {
    window.history.back();
  }
}
