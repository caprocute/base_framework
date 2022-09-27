import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';

import { ISysDepart } from '../sys-depart.model';
import { SysDepartService } from '../service/sys-depart.service';
import { ITEM_DELETED_EVENT } from 'app/config/navigation.constants';

@Component({
  templateUrl: './sys-depart-delete-dialog.component.html',
})
export class SysDepartDeleteDialogComponent {
  sysDepart?: ISysDepart;

  constructor(protected sysDepartService: SysDepartService, protected activeModal: NgbActiveModal) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: string): void {
    this.sysDepartService.delete(id).subscribe(() => {
      this.activeModal.close(ITEM_DELETED_EVENT);
    });
  }
}
