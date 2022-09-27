import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { ISysUserRole } from '../sys-user-role.model';

@Component({
  selector: 'jhi-sys-user-role-detail',
  templateUrl: './sys-user-role-detail.component.html',
})
export class SysUserRoleDetailComponent implements OnInit {
  sysUserRole: ISysUserRole | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ sysUserRole }) => {
      this.sysUserRole = sysUserRole;
    });
  }

  previousState(): void {
    window.history.back();
  }
}
