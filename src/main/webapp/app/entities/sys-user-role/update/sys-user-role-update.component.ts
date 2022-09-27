import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { finalize } from 'rxjs/operators';

import { SysUserRoleFormService, SysUserRoleFormGroup } from './sys-user-role-form.service';
import { ISysUserRole } from '../sys-user-role.model';
import { SysUserRoleService } from '../service/sys-user-role.service';

@Component({
  selector: 'jhi-sys-user-role-update',
  templateUrl: './sys-user-role-update.component.html',
})
export class SysUserRoleUpdateComponent implements OnInit {
  isSaving = false;
  sysUserRole: ISysUserRole | null = null;

  editForm: SysUserRoleFormGroup = this.sysUserRoleFormService.createSysUserRoleFormGroup();

  constructor(
    protected sysUserRoleService: SysUserRoleService,
    protected sysUserRoleFormService: SysUserRoleFormService,
    protected activatedRoute: ActivatedRoute
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ sysUserRole }) => {
      this.sysUserRole = sysUserRole;
      if (sysUserRole) {
        this.updateForm(sysUserRole);
      }
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const sysUserRole = this.sysUserRoleFormService.getSysUserRole(this.editForm);
    if (sysUserRole.id !== null) {
      this.subscribeToSaveResponse(this.sysUserRoleService.update(sysUserRole));
    } else {
      this.subscribeToSaveResponse(this.sysUserRoleService.create(sysUserRole));
    }
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<ISysUserRole>>): void {
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

  protected updateForm(sysUserRole: ISysUserRole): void {
    this.sysUserRole = sysUserRole;
    this.sysUserRoleFormService.resetForm(this.editForm, sysUserRole);
  }
}
