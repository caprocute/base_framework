import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { ISysRoleIndex } from '../sys-role-index.model';

@Component({
  selector: 'jhi-sys-role-index-detail',
  templateUrl: './sys-role-index-detail.component.html',
})
export class SysRoleIndexDetailComponent implements OnInit {
  sysRoleIndex: ISysRoleIndex | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ sysRoleIndex }) => {
      this.sysRoleIndex = sysRoleIndex;
    });
  }

  previousState(): void {
    window.history.back();
  }
}
