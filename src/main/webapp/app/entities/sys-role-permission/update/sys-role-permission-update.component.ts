import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { finalize } from 'rxjs/operators';

import { SysRolePermissionFormService, SysRolePermissionFormGroup } from './sys-role-permission-form.service';
import { ISysRolePermission } from '../sys-role-permission.model';
import { SysRolePermissionService } from '../service/sys-role-permission.service';

@Component({
  selector: 'jhi-sys-role-permission-update',
  templateUrl: './sys-role-permission-update.component.html',
})
export class SysRolePermissionUpdateComponent implements OnInit {
  isSaving = false;
  sysRolePermission: ISysRolePermission | null = null;

  editForm: SysRolePermissionFormGroup = this.sysRolePermissionFormService.createSysRolePermissionFormGroup();

  constructor(
    protected sysRolePermissionService: SysRolePermissionService,
    protected sysRolePermissionFormService: SysRolePermissionFormService,
    protected activatedRoute: ActivatedRoute
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ sysRolePermission }) => {
      this.sysRolePermission = sysRolePermission;
      if (sysRolePermission) {
        this.updateForm(sysRolePermission);
      }
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const sysRolePermission = this.sysRolePermissionFormService.getSysRolePermission(this.editForm);
    if (sysRolePermission.id !== null) {
      this.subscribeToSaveResponse(this.sysRolePermissionService.update(sysRolePermission));
    } else {
      this.subscribeToSaveResponse(this.sysRolePermissionService.create(sysRolePermission));
    }
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<ISysRolePermission>>): void {
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

  protected updateForm(sysRolePermission: ISysRolePermission): void {
    this.sysRolePermission = sysRolePermission;
    this.sysRolePermissionFormService.resetForm(this.editForm, sysRolePermission);
  }
}
