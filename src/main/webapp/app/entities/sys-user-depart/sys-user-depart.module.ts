import { NgModule } from '@angular/core';
import { SharedModule } from 'app/shared/shared.module';
import { SysUserDepartComponent } from './list/sys-user-depart.component';
import { SysUserDepartDetailComponent } from './detail/sys-user-depart-detail.component';
import { SysUserDepartUpdateComponent } from './update/sys-user-depart-update.component';
import { SysUserDepartDeleteDialogComponent } from './delete/sys-user-depart-delete-dialog.component';
import { SysUserDepartRoutingModule } from './route/sys-user-depart-routing.module';

@NgModule({
  imports: [SharedModule, SysUserDepartRoutingModule],
  declarations: [SysUserDepartComponent, SysUserDepartDetailComponent, SysUserDepartUpdateComponent, SysUserDepartDeleteDialogComponent],
})
export class SysUserDepartModule {}
