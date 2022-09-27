import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { finalize } from 'rxjs/operators';

import { SysDepartFormService, SysDepartFormGroup } from './sys-depart-form.service';
import { ISysDepart } from '../sys-depart.model';
import { SysDepartService } from '../service/sys-depart.service';

@Component({
  selector: 'jhi-sys-depart-update',
  templateUrl: './sys-depart-update.component.html',
})
export class SysDepartUpdateComponent implements OnInit {
  isSaving = false;
  sysDepart: ISysDepart | null = null;

  editForm: SysDepartFormGroup = this.sysDepartFormService.createSysDepartFormGroup();

  constructor(
    protected sysDepartService: SysDepartService,
    protected sysDepartFormService: SysDepartFormService,
    protected activatedRoute: ActivatedRoute
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ sysDepart }) => {
      this.sysDepart = sysDepart;
      if (sysDepart) {
        this.updateForm(sysDepart);
      }
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const sysDepart = this.sysDepartFormService.getSysDepart(this.editForm);
    if (sysDepart.id !== null) {
      this.subscribeToSaveResponse(this.sysDepartService.update(sysDepart));
    } else {
      this.subscribeToSaveResponse(this.sysDepartService.create(sysDepart));
    }
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<ISysDepart>>): void {
    result.pipe(finalize(() => this.onSaveFinalize())).subscribe({
      next: () => this.onSaveSuccess(),
      error: () => this.onSaveError(),
    });
  }

  protected onSaveSuccess(): void {
    this.previousState();
  }

  protected onSaveError(): void {
    // Api for inheritance.
  }

  protected onSaveFinalize(): void {
    this.isSaving = false;
  }

  protected updateForm(sysDepart: ISysDepart): void {
    this.sysDepart = sysDepart;
    this.sysDepartFormService.resetForm(this.editForm, sysDepart);
  }
}
