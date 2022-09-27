import { NgModule } from '@angular/core';
import { SharedModule } from 'app/shared/shared.module';
import { SysPermissionComponent } from './list/sys-permission.component';
import { SysPermissionDetailComponent } from './detail/sys-permission-detail.component';
import { SysPermissionUpdateComponent } from './update/sys-permission-update.component';
import { SysPermissionDeleteDialogComponent } from './delete/sys-permission-delete-dialog.component';
import { SysPermissionRoutingModule } from './route/sys-permission-routing.module';

@NgModule({
  imports: [SharedModule, SysPermissionRoutingModule],
  declarations: [SysPermissionComponent, SysPermissionDetailComponent, SysPermissionUpdateComponent, SysPermissionDeleteDialogComponent],
})
export class SysPermissionModule {}
