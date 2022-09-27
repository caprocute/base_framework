import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';

import { ISysDepartRole } from '../sys-depart-role.model';
import { SysDepartRoleService } from '../service/sys-depart-role.service';
import { ITEM_DELETED_EVENT } from 'app/config/navigation.constants';

@Component({
  templateUrl: './sys-depart-role-delete-dialog.component.html',
})
export class SysDepartRoleDeleteDialogComponent {
  sysDepartRole?: ISysDepartRole;

  constructor(protected sysDepartRoleService: SysDepartRoleService, protected activeModal: NgbActiveModal) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: string): void {
    this.sysDepartRoleService.delete(id).subscribe(() => {
      this.activeModal.close(ITEM_DELETED_EVENT);
    });
  }
}
