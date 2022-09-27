import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { finalize } from 'rxjs/operators';

import { SysPermissionDataRuleFormService, SysPermissionDataRuleFormGroup } from './sys-permission-data-rule-form.service';
import { ISysPermissionDataRule } from '../sys-permission-data-rule.model';
import { SysPermissionDataRuleService } from '../service/sys-permission-data-rule.service';

@Component({
  selector: 'jhi-sys-permission-data-rule-update',
  templateUrl: './sys-permission-data-rule-update.component.html',
})
export class SysPermissionDataRuleUpdateComponent implements OnInit {
  isSaving = false;
  sysPermissionDataRule: ISysPermissionDataRule | null = null;

  editForm: SysPermissionDataRuleFormGroup = this.sysPermissionDataRuleFormService.createSysPermissionDataRuleFormGroup();

  constructor(
    protected sysPermissionDataRuleService: SysPermissionDataRuleService,
    protected sysPermissionDataRuleFormService: SysPermissionDataRuleFormService,
    protected activatedRoute: ActivatedRoute
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ sysPermissionDataRule }) => {
      this.sysPermissionDataRule = sysPermissionDataRule;
      if (sysPermissionDataRule) {
        this.updateForm(sysPermissionDataRule);
      }
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const sysPermissionDataRule = this.sysPermissionDataRuleFormService.getSysPermissionDataRule(this.editForm);
    if (sysPermissionDataRule.id !== null) {
      this.subscribeToSaveResponse(this.sysPermissionDataRuleService.update(sysPermissionDataRule));
    } else {
      this.subscribeToSaveResponse(this.sysPermissionDataRuleService.create(sysPermissionDataRule));
    }
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<ISysPermissionDataRule>>): void {
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

  protected updateForm(sysPermissionDataRule: ISysPermissionDataRule): void {
    this.sysPermissionDataRule = sysPermissionDataRule;
    this.sysPermissionDataRuleFormService.resetForm(this.editForm, sysPermissionDataRule);
  }
}
