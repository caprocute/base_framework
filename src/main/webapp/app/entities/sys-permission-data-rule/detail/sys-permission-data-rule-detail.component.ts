import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { ISysPermissionDataRule } from '../sys-permission-data-rule.model';

@Component({
  selector: 'jhi-sys-permission-data-rule-detail',
  templateUrl: './sys-permission-data-rule-detail.component.html',
})
export class SysPermissionDataRuleDetailComponent implements OnInit {
  sysPermissionDataRule: ISysPermissionDataRule | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ sysPermissionDataRule }) => {
      this.sysPermissionDataRule = sysPermissionDataRule;
    });
  }

  previousState(): void {
    window.history.back();
  }
}
