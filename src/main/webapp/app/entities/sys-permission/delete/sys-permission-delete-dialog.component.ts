import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';

import { ISysPermission } from '../sys-permission.model';
import { SysPermissionService } from '../service/sys-permission.service';
import { ITEM_DELETED_EVENT } from 'app/config/navigation.constants';

@Component({
  templateUrl: './sys-permission-delete-dialog.component.html',
})
export class SysPermissionDeleteDialogComponent {
  sysPermission?: ISysPermission;

  constructor(protected sysPermissionService: SysPermissionService, protected activeModal: NgbActiveModal) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: string): void {
    this.sysPermissionService.delete(id).subscribe(() => {
      this.activeModal.close(ITEM_DELETED_EVENT);
    });
  }
}
