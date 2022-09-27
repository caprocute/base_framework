import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { finalize } from 'rxjs/operators';

import { SysLogFormService, SysLogFormGroup } from './sys-log-form.service';
import { ISysLog } from '../sys-log.model';
import { SysLogService } from '../service/sys-log.service';

@Component({
  selector: 'jhi-sys-log-update',
  templateUrl: './sys-log-update.component.html',
})
export class SysLogUpdateComponent implements OnInit {
  isSaving = false;
  sysLog: ISysLog | null = null;

  editForm: SysLogFormGroup = this.sysLogFormService.createSysLogFormGroup();

  constructor(
    protected sysLogService: SysLogService,
    protected sysLogFormService: SysLogFormService,
    protected activatedRoute: ActivatedRoute
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ sysLog }) => {
      this.sysLog = sysLog;
      if (sysLog) {
        this.updateForm(sysLog);
      }
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const sysLog = this.sysLogFormService.getSysLog(this.editForm);
    if (sysLog.id !== null) {
      this.subscribeToSaveResponse(this.sysLogService.update(sysLog));
    } else {
      this.subscribeToSaveResponse(this.sysLogService.create(sysLog));
    }
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<ISysLog>>): void {
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

  protected updateForm(sysLog: ISysLog): void {
    this.sysLog = sysLog;
    this.sysLogFormService.resetForm(this.editForm, sysLog);
  }
}
