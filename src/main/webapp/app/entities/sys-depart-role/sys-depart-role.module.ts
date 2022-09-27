import { NgModule } from '@angular/core';
import { SharedModule } from 'app/shared/shared.module';
import { SysDepartRoleComponent } from './list/sys-depart-role.component';
import { SysDepartRoleDetailComponent } from './detail/sys-depart-role-detail.component';
import { SysDepartRoleUpdateComponent } from './update/sys-depart-role-update.component';
import { SysDepartRoleDeleteDialogComponent } from './delete/sys-depart-role-delete-dialog.component';
import { SysDepartRoleRoutingModule } from './route/sys-depart-role-routing.module';

@NgModule({
  imports: [SharedModule, SysDepartRoleRoutingModule],
  declarations: [SysDepartRoleComponent, SysDepartRoleDetailComponent, SysDepartRoleUpdateComponent, SysDepartRoleDeleteDialogComponent],
})
export class SysDepartRoleModule {}
