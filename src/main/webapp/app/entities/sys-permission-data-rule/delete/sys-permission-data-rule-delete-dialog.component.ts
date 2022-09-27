import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';

import { ISysPermissionDataRule } from '../sys-permission-data-rule.model';
import { SysPermissionDataRuleService } from '../service/sys-permission-data-rule.service';
import { ITEM_DELETED_EVENT } from 'app/config/navigation.constants';

@Component({
  templateUrl: './sys-permission-data-rule-delete-dialog.component.html',
})
export class SysPermissionDataRuleDeleteDialogComponent {
  sysPermissionDataRule?: ISysPermissionDataRule;

  constructor(protected sysPermissionDataRuleService: SysPermissionDataRuleService, protected activeModal: NgbActiveModal) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: string): void {
    this.sysPermissionDataRuleService.delete(id).subscribe(() => {
      this.activeModal.close(ITEM_DELETED_EVENT);
    });
  }
}
