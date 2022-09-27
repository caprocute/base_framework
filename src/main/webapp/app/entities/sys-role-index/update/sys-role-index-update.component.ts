import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { finalize } from 'rxjs/operators';

import { SysRoleIndexFormService, SysRoleIndexFormGroup } from './sys-role-index-form.service';
import { ISysRoleIndex } from '../sys-role-index.model';
import { SysRoleIndexService } from '../service/sys-role-index.service';

@Component({
  selector: 'jhi-sys-role-index-update',
  templateUrl: './sys-role-index-update.component.html',
})
export class SysRoleIndexUpdateComponent implements OnInit {
  isSaving = false;
  sysRoleIndex: ISysRoleIndex | null = null;

  editForm: SysRoleIndexFormGroup = this.sysRoleIndexFormService.createSysRoleIndexFormGroup();

  constructor(
    protected sysRoleIndexService: SysRoleIndexService,
    protected sysRoleIndexFormService: SysRoleIndexFormService,
    protected activatedRoute: ActivatedRoute
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ sysRoleIndex }) => {
      this.sysRoleIndex = sysRoleIndex;
      if (sysRoleIndex) {
        this.updateForm(sysRoleIndex);
      }
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const sysRoleIndex = this.sysRoleIndexFormService.getSysRoleIndex(this.editForm);
    if (sysRoleIndex.id !== null) {
      this.subscribeToSaveResponse(this.sysRoleIndexService.update(sysRoleIndex));
    } else {
      this.subscribeToSaveResponse(this.sysRoleIndexService.create(sysRoleIndex));
    }
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<ISysRoleIndex>>): void {
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

  protected updateForm(sysRoleIndex: ISysRoleIndex): void {
    this.sysRoleIndex = sysRoleIndex;
    this.sysRoleIndexFormService.resetForm(this.editForm, sysRoleIndex);
  }
}
