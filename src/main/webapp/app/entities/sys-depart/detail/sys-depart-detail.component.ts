import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { ISysDepart } from '../sys-depart.model';

@Component({
  selector: 'jhi-sys-depart-detail',
  templateUrl: './sys-depart-detail.component.html',
})
export class SysDepartDetailComponent implements OnInit {
  sysDepart: ISysDepart | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ sysDepart }) => {
      this.sysDepart = sysDepart;
    });
  }

  previousState(): void {
    window.history.back();
  }
}
