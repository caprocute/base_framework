import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';

import { ISysTenant } from '../sys-tenant.model';
import { SysTenantService } from '../service/sys-tenant.service';
import { ITEM_DELETED_EVENT } from 'app/config/navigation.constants';

@Component({
  templateUrl: './sys-tenant-delete-dialog.component.html',
})
export class SysTenantDeleteDialogComponent {
  sysTenant?: ISysTenant;

  constructor(protected sysTenantService: SysTenantService, protected activeModal: NgbActiveModal) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: string): void {
    this.sysTenantService.delete(id).subscribe(() => {
      this.activeModal.close(ITEM_DELETED_EVENT);
    });
  }
}
