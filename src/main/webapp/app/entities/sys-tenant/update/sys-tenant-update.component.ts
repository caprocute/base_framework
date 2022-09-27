import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { finalize } from 'rxjs/operators';

import { SysTenantFormService, SysTenantFormGroup } from './sys-tenant-form.service';
import { ISysTenant } from '../sys-tenant.model';
import { SysTenantService } from '../service/sys-tenant.service';

@Component({
  selector: 'jhi-sys-tenant-update',
  templateUrl: './sys-tenant-update.component.html',
})
export class SysTenantUpdateComponent implements OnInit {
  isSaving = false;
  sysTenant: ISysTenant | null = null;

  editForm: SysTenantFormGroup = this.sysTenantFormService.createSysTenantFormGroup();

  constructor(
    protected sysTenantService: SysTenantService,
    protected sysTenantFormService: SysTenantFormService,
    protected activatedRoute: ActivatedRoute
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ sysTenant }) => {
      this.sysTenant = sysTenant;
      if (sysTenant) {
        this.updateForm(sysTenant);
      }
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const sysTenant = this.sysTenantFormService.getSysTenant(this.editForm);
    if (sysTenant.id !== null) {
      this.subscribeToSaveResponse(this.sysTenantService.update(sysTenant));
    } else {
      this.subscribeToSaveResponse(this.sysTenantService.create(sysTenant));
    }
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<ISysTenant>>): void {
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

  protected updateForm(sysTenant: ISysTenant): void {
    this.sysTenant = sysTenant;
    this.sysTenantFormService.resetForm(this.editForm, sysTenant);
  }
}
