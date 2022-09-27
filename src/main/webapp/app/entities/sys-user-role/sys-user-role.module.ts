import { NgModule } from '@angular/core';
import { SharedModule } from 'app/shared/shared.module';
import { SysUserRoleComponent } from './list/sys-user-role.component';
import { SysUserRoleDetailComponent } from './detail/sys-user-role-detail.component';
import { SysUserRoleUpdateComponent } from './update/sys-user-role-update.component';
import { SysUserRoleDeleteDialogComponent } from './delete/sys-user-role-delete-dialog.component';
import { SysUserRoleRoutingModule } from './route/sys-user-role-routing.module';

@NgModule({
  imports: [SharedModule, SysUserRoleRoutingModule],
  declarations: [SysUserRoleComponent, SysUserRoleDetailComponent, SysUserRoleUpdateComponent, SysUserRoleDeleteDialogComponent],
})
export class SysUserRoleModule {}
