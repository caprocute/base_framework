import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { finalize } from 'rxjs/operators';

import { SysUserDepartFormService, SysUserDepartFormGroup } from './sys-user-depart-form.service';
import { ISysUserDepart } from '../sys-user-depart.model';
import { SysUserDepartService } from '../service/sys-user-depart.service';

@Component({
  selector: 'jhi-sys-user-depart-update',
  templateUrl: './sys-user-depart-update.component.html',
})
export class SysUserDepartUpdateComponent implements OnInit {
  isSaving = false;
  sysUserDepart: ISysUserDepart | null = null;

  editForm: SysUserDepartFormGroup = this.sysUserDepartFormService.createSysUserDepartFormGroup();

  constructor(
    protected sysUserDepartService: SysUserDepartService,
    protected sysUserDepartFormService: SysUserDepartFormService,
    protected activatedRoute: ActivatedRoute
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ sysUserDepart }) => {
      this.sysUserDepart = sysUserDepart;
      if (sysUserDepart) {
        this.updateForm(sysUserDepart);
      }
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const sysUserDepart = this.sysUserDepartFormService.getSysUserDepart(this.editForm);
    if (sysUserDepart.id !== null) {
      this.subscribeToSaveResponse(this.sysUserDepartService.update(sysUserDepart));
    } else {
      this.subscribeToSaveResponse(this.sysUserDepartService.create(sysUserDepart));
    }
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<ISysUserDepart>>): void {
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

  protected updateForm(sysUserDepart: ISysUserDepart): void {
    this.sysUserDepart = sysUserDepart;
    this.sysUserDepartFormService.resetForm(this.editForm, sysUserDepart);
  }
}
