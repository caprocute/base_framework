import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { finalize } from 'rxjs/operators';

import { SysDepartRoleFormService, SysDepartRoleFormGroup } from './sys-depart-role-form.service';
import { ISysDepartRole } from '../sys-depart-role.model';
import { SysDepartRoleService } from '../service/sys-depart-role.service';

@Component({
  selector: 'jhi-sys-depart-role-update',
  templateUrl: './sys-depart-role-update.component.html',
})
export class SysDepartRoleUpdateComponent implements OnInit {
  isSaving = false;
  sysDepartRole: ISysDepartRole | null = null;

  editForm: SysDepartRoleFormGroup = this.sysDepartRoleFormService.createSysDepartRoleFormGroup();

  constructor(
    protected sysDepartRoleService: SysDepartRoleService,
    protected sysDepartRoleFormService: SysDepartRoleFormService,
    protected activatedRoute: ActivatedRoute
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ sysDepartRole }) => {
      this.sysDepartRole = sysDepartRole;
      if (sysDepartRole) {
        this.updateForm(sysDepartRole);
      }
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const sysDepartRole = this.sysDepartRoleFormService.getSysDepartRole(this.editForm);
    if (sysDepartRole.id !== null) {
      this.subscribeToSaveResponse(this.sysDepartRoleService.update(sysDepartRole));
    } else {
      this.subscribeToSaveResponse(this.sysDepartRoleService.create(sysDepartRole));
    }
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<ISysDepartRole>>): void {
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

  protected updateForm(sysDepartRole: ISysDepartRole): void {
    this.sysDepartRole = sysDepartRole;
    this.sysDepartRoleFormService.resetForm(this.editForm, sysDepartRole);
  }
}
