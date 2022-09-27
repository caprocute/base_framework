import { NgModule } from '@angular/core';
import { SharedModule } from 'app/shared/shared.module';
import { SysRolePermissionComponent } from './list/sys-role-permission.component';
import { SysRolePermissionDetailComponent } from './detail/sys-role-permission-detail.component';
import { SysRolePermissionUpdateComponent } from './update/sys-role-permission-update.component';
import { SysRolePermissionDeleteDialogComponent } from './delete/sys-role-permission-delete-dialog.component';
import { SysRolePermissionRoutingModule } from './route/sys-role-permission-routing.module';

@NgModule({
  imports: [SharedModule, SysRolePermissionRoutingModule],
  declarations: [
    SysRolePermissionComponent,
    SysRolePermissionDetailComponent,
    SysRolePermissionUpdateComponent,
    SysRolePermissionDeleteDialogComponent,
  ],
})
export class SysRolePermissionModule {}
