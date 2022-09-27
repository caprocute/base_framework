import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';

import { ISysPosition } from '../sys-position.model';
import { SysPositionService } from '../service/sys-position.service';
import { ITEM_DELETED_EVENT } from 'app/config/navigation.constants';

@Component({
  templateUrl: './sys-position-delete-dialog.component.html',
})
export class SysPositionDeleteDialogComponent {
  sysPosition?: ISysPosition;

  constructor(protected sysPositionService: SysPositionService, protected activeModal: NgbActiveModal) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: string): void {
    this.sysPositionService.delete(id).subscribe(() => {
      this.activeModal.close(ITEM_DELETED_EVENT);
    });
  }
}
