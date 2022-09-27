import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { finalize } from 'rxjs/operators';

import { SysDataLogFormService, SysDataLogFormGroup } from './sys-data-log-form.service';
import { ISysDataLog } from '../sys-data-log.model';
import { SysDataLogService } from '../service/sys-data-log.service';

@Component({
  selector: 'jhi-sys-data-log-update',
  templateUrl: './sys-data-log-update.component.html',
})
export class SysDataLogUpdateComponent implements OnInit {
  isSaving = false;
  sysDataLog: ISysDataLog | null = null;

  editForm: SysDataLogFormGroup = this.sysDataLogFormService.createSysDataLogFormGroup();

  constructor(
    protected sysDataLogService: SysDataLogService,
    protected sysDataLogFormService: SysDataLogFormService,
    protected activatedRoute: ActivatedRoute
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ sysDataLog }) => {
      this.sysDataLog = sysDataLog;
      if (sysDataLog) {
        this.updateForm(sysDataLog);
      }
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const sysDataLog = this.sysDataLogFormService.getSysDataLog(this.editForm);
    if (sysDataLog.id !== null) {
      this.subscribeToSaveResponse(this.sysDataLogService.update(sysDataLog));
    } else {
      this.subscribeToSaveResponse(this.sysDataLogService.create(sysDataLog));
    }
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<ISysDataLog>>): void {
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

  protected updateForm(sysDataLog: ISysDataLog): void {
    this.sysDataLog = sysDataLog;
    this.sysDataLogFormService.resetForm(this.editForm, sysDataLog);
  }
}
