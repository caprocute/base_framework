import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { ISysDepartRole } from '../sys-depart-role.model';

@Component({
  selector: 'jhi-sys-depart-role-detail',
  templateUrl: './sys-depart-role-detail.component.html',
})
export class SysDepartRoleDetailComponent implements OnInit {
  sysDepartRole: ISysDepartRole | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ sysDepartRole }) => {
      this.sysDepartRole = sysDepartRole;
    });
  }

  previousState(): void {
    window.history.back();
  }
}
