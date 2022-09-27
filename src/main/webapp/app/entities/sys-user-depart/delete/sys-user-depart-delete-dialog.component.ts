import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';

import { ISysUserDepart } from '../sys-user-depart.model';
import { SysUserDepartService } from '../service/sys-user-depart.service';
import { ITEM_DELETED_EVENT } from 'app/config/navigation.constants';

@Component({
  templateUrl: './sys-user-depart-delete-dialog.component.html',
})
export class SysUserDepartDeleteDialogComponent {
  sysUserDepart?: ISysUserDepart;

  constructor(protected sysUserDepartService: SysUserDepartService, protected activeModal: NgbActiveModal) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: string): void {
    this.sysUserDepartService.delete(id).subscribe(() => {
      this.activeModal.close(ITEM_DELETED_EVENT);
    });
  }
}
