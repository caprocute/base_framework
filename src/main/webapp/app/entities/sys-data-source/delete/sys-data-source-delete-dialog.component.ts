import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';

import { ISysDataSource } from '../sys-data-source.model';
import { SysDataSourceService } from '../service/sys-data-source.service';
import { ITEM_DELETED_EVENT } from 'app/config/navigation.constants';

@Component({
  templateUrl: './sys-data-source-delete-dialog.component.html',
})
export class SysDataSourceDeleteDialogComponent {
  sysDataSource?: ISysDataSource;

  constructor(protected sysDataSourceService: SysDataSourceService, protected activeModal: NgbActiveModal) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: string): void {
    this.sysDataSourceService.delete(id).subscribe(() => {
      this.activeModal.close(ITEM_DELETED_EVENT);
    });
  }
}
