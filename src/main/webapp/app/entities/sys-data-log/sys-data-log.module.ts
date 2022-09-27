import { NgModule } from '@angular/core';
import { SharedModule } from 'app/shared/shared.module';
import { SysDataLogComponent } from './list/sys-data-log.component';
import { SysDataLogDetailComponent } from './detail/sys-data-log-detail.component';
import { SysDataLogUpdateComponent } from './update/sys-data-log-update.component';
import { SysDataLogDeleteDialogComponent } from './delete/sys-data-log-delete-dialog.component';
import { SysDataLogRoutingModule } from './route/sys-data-log-routing.module';

@NgModule({
  imports: [SharedModule, SysDataLogRoutingModule],
  declarations: [SysDataLogComponent, SysDataLogDetailComponent, SysDataLogUpdateComponent, SysDataLogDeleteDialogComponent],
})
export class SysDataLogModule {}
