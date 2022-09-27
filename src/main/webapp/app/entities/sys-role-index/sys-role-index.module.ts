import { NgModule } from '@angular/core';
import { SharedModule } from 'app/shared/shared.module';
import { SysRoleIndexComponent } from './list/sys-role-index.component';
import { SysRoleIndexDetailComponent } from './detail/sys-role-index-detail.component';
import { SysRoleIndexUpdateComponent } from './update/sys-role-index-update.component';
import { SysRoleIndexDeleteDialogComponent } from './delete/sys-role-index-delete-dialog.component';
import { SysRoleIndexRoutingModule } from './route/sys-role-index-routing.module';

@NgModule({
  imports: [SharedModule, SysRoleIndexRoutingModule],
  declarations: [SysRoleIndexComponent, SysRoleIndexDetailComponent, SysRoleIndexUpdateComponent, SysRoleIndexDeleteDialogComponent],
})
export class SysRoleIndexModule {}
