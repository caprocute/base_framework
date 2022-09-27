import { NgModule } from '@angular/core';
import { SharedModule } from 'app/shared/shared.module';
import { SysTenantComponent } from './list/sys-tenant.component';
import { SysTenantDetailComponent } from './detail/sys-tenant-detail.component';
import { SysTenantUpdateComponent } from './update/sys-tenant-update.component';
import { SysTenantDeleteDialogComponent } from './delete/sys-tenant-delete-dialog.component';
import { SysTenantRoutingModule } from './route/sys-tenant-routing.module';

@NgModule({
  imports: [SharedModule, SysTenantRoutingModule],
  declarations: [SysTenantComponent, SysTenantDetailComponent, SysTenantUpdateComponent, SysTenantDeleteDialogComponent],
})
export class SysTenantModule {}
