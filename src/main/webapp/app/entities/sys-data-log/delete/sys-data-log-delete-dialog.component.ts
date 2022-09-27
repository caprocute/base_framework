import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';

import { ISysDataLog } from '../sys-data-log.model';
import { SysDataLogService } from '../service/sys-data-log.service';
import { ITEM_DELETED_EVENT } from 'app/config/navigation.constants';

@Component({
  templateUrl: './sys-data-log-delete-dialog.component.html',
})
export class SysDataLogDeleteDialogComponent {
  sysDataLog?: ISysDataLog;

  constructor(protected sysDataLogService: SysDataLogService, protected activeModal: NgbActiveModal) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: string): void {
    this.sysDataLogService.delete(id).subscribe(() => {
      this.activeModal.close(ITEM_DELETED_EVENT);
    });
  }
}
