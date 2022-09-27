import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';

import { ISysRoleIndex } from '../sys-role-index.model';
import { SysRoleIndexService } from '../service/sys-role-index.service';
import { ITEM_DELETED_EVENT } from 'app/config/navigation.constants';

@Component({
  templateUrl: './sys-role-index-delete-dialog.component.html',
})
export class SysRoleIndexDeleteDialogComponent {
  sysRoleIndex?: ISysRoleIndex;

  constructor(protected sysRoleIndexService: SysRoleIndexService, protected activeModal: NgbActiveModal) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: string): void {
    this.sysRoleIndexService.delete(id).subscribe(() => {
      this.activeModal.close(ITEM_DELETED_EVENT);
    });
  }
}
