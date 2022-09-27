import { NgModule } from '@angular/core';
import { SharedModule } from 'app/shared/shared.module';
import { SysDataSourceComponent } from './list/sys-data-source.component';
import { SysDataSourceDetailComponent } from './detail/sys-data-source-detail.component';
import { SysDataSourceUpdateComponent } from './update/sys-data-source-update.component';
import { SysDataSourceDeleteDialogComponent } from './delete/sys-data-source-delete-dialog.component';
import { SysDataSourceRoutingModule } from './route/sys-data-source-routing.module';

@NgModule({
  imports: [SharedModule, SysDataSourceRoutingModule],
  declarations: [SysDataSourceComponent, SysDataSourceDetailComponent, SysDataSourceUpdateComponent, SysDataSourceDeleteDialogComponent],
})
export class SysDataSourceModule {}
