import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { finalize } from 'rxjs/operators';

import { SysPositionFormService, SysPositionFormGroup } from './sys-position-form.service';
import { ISysPosition } from '../sys-position.model';
import { SysPositionService } from '../service/sys-position.service';

@Component({
  selector: 'jhi-sys-position-update',
  templateUrl: './sys-position-update.component.html',
})
export class SysPositionUpdateComponent implements OnInit {
  isSaving = false;
  sysPosition: ISysPosition | null = null;

  editForm: SysPositionFormGroup = this.sysPositionFormService.createSysPositionFormGroup();

  constructor(
    protected sysPositionService: SysPositionService,
    protected sysPositionFormService: SysPositionFormService,
    protected activatedRoute: ActivatedRoute
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ sysPosition }) => {
      this.sysPosition = sysPosition;
      if (sysPosition) {
        this.updateForm(sysPosition);
      }
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const sysPosition = this.sysPositionFormService.getSysPosition(this.editForm);
    if (sysPosition.id !== null) {
      this.subscribeToSaveResponse(this.sysPositionService.update(sysPosition));
    } else {
      this.subscribeToSaveResponse(this.sysPositionService.create(sysPosition));
    }
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<ISysPosition>>): void {
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

  protected updateForm(sysPosition: ISysPosition): void {
    this.sysPosition = sysPosition;
    this.sysPositionFormService.resetForm(this.editForm, sysPosition);
  }
}
