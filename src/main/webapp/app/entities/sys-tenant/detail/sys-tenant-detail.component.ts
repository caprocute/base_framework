import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { ISysTenant } from '../sys-tenant.model';

@Component({
  selector: 'jhi-sys-tenant-detail',
  templateUrl: './sys-tenant-detail.component.html',
})
export class SysTenantDetailComponent implements OnInit {
  sysTenant: ISysTenant | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ sysTenant }) => {
      this.sysTenant = sysTenant;
    });
  }

  previousState(): void {
    window.history.back();
  }
}
