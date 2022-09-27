import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';

import { ISysUserRole } from '../sys-user-role.model';
import { SysUserRoleService } from '../service/sys-user-role.service';
import { ITEM_DELETED_EVENT } from 'app/config/navigation.constants';

@Component({
  templateUrl: './sys-user-role-delete-dialog.component.html',
})
export class SysUserRoleDeleteDialogComponent {
  sysUserRole?: ISysUserRole;

  constructor(protected sysUserRoleService: SysUserRoleService, protected activeModal: NgbActiveModal) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: string): void {
    this.sysUserRoleService.delete(id).subscribe(() => {
      this.activeModal.close(ITEM_DELETED_EVENT);
    });
  }
}
