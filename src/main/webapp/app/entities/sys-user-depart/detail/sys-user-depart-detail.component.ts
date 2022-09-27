import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { ISysUserDepart } from '../sys-user-depart.model';

@Component({
  selector: 'jhi-sys-user-depart-detail',
  templateUrl: './sys-user-depart-detail.component.html',
})
export class SysUserDepartDetailComponent implements OnInit {
  sysUserDepart: ISysUserDepart | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ sysUserDepart }) => {
      this.sysUserDepart = sysUserDepart;
    });
  }

  previousState(): void {
    window.history.back();
  }
}
