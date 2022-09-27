import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { finalize } from 'rxjs/operators';

import { SysPermissionFormService, SysPermissionFormGroup } from './sys-permission-form.service';
import { ISysPermission } from '../sys-permission.model';
import { SysPermissionService } from '../service/sys-permission.service';

@Component({
  selector: 'jhi-sys-permission-update',
  templateUrl: './sys-permission-update.component.html',
})
export class SysPermissionUpdateComponent implements OnInit {
  isSaving = false;
  sysPermission: ISysPermission | null = null;

  editForm: SysPermissionFormGroup = this.sysPermissionFormService.createSysPermissionFormGroup();

  constructor(
    protected sysPermissionService: SysPermissionService,
    protected sysPermissionFormService: SysPermissionFormService,
    protected activatedRoute: ActivatedRoute
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ sysPermission }) => {
      this.sysPermission = sysPermission;
      if (sysPermission) {
        this.updateForm(sysPermission);
      }
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const sysPermission = this.sysPermissionFormService.getSysPermission(this.editForm);
    if (sysPermission.id !== null) {
      this.subscribeToSaveResponse(this.sysPermissionService.update(sysPermission));
    } else {
      this.subscribeToSaveResponse(this.sysPermissionService.create(sysPermission));
    }
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<ISysPermission>>): void {
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

  protected updateForm(sysPermission: ISysPermission): void {
    this.sysPermission = sysPermission;
    this.sysPermissionFormService.resetForm(this.editForm, sysPermission);
  }
}
