import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { finalize } from 'rxjs/operators';

import { SysDataSourceFormService, SysDataSourceFormGroup } from './sys-data-source-form.service';
import { ISysDataSource } from '../sys-data-source.model';
import { SysDataSourceService } from '../service/sys-data-source.service';

@Component({
  selector: 'jhi-sys-data-source-update',
  templateUrl: './sys-data-source-update.component.html',
})
export class SysDataSourceUpdateComponent implements OnInit {
  isSaving = false;
  sysDataSource: ISysDataSource | null = null;

  editForm: SysDataSourceFormGroup = this.sysDataSourceFormService.createSysDataSourceFormGroup();

  constructor(
    protected sysDataSourceService: SysDataSourceService,
    protected sysDataSourceFormService: SysDataSourceFormService,
    protected activatedRoute: ActivatedRoute
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ sysDataSource }) => {
      this.sysDataSource = sysDataSource;
      if (sysDataSource) {
        this.updateForm(sysDataSource);
      }
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const sysDataSource = this.sysDataSourceFormService.getSysDataSource(this.editForm);
    if (sysDataSource.id !== null) {
      this.subscribeToSaveResponse(this.sysDataSourceService.update(sysDataSource));
    } else {
      this.subscribeToSaveResponse(this.sysDataSourceService.create(sysDataSource));
    }
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<ISysDataSource>>): void {
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

  protected updateForm(sysDataSource: ISysDataSource): void {
    this.sysDataSource = sysDataSource;
    this.sysDataSourceFormService.resetForm(this.editForm, sysDataSource);
  }
}
