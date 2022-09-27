import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';

import { ISysRolePermission } from '../sys-role-permission.model';
import { SysRolePermissionService } from '../service/sys-role-permission.service';
import { ITEM_DELETED_EVENT } from 'app/config/navigation.constants';

@Component({
  templateUrl: './sys-role-permission-delete-dialog.component.html',
})
export class SysRolePermissionDeleteDialogComponent {
  sysRolePermission?: ISysRolePermission;

  constructor(protected sysRolePermissionService: SysRolePermissionService, protected activeModal: NgbActiveModal) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: string): void {
    this.sysRolePermissionService.delete(id).subscribe(() => {
      this.activeModal.close(ITEM_DELETED_EVENT);
    });
  }
}
