import { NgModule } from '@angular/core';
import { SharedModule } from 'app/shared/shared.module';
import { SysPositionComponent } from './list/sys-position.component';
import { SysPositionDetailComponent } from './detail/sys-position-detail.component';
import { SysPositionUpdateComponent } from './update/sys-position-update.component';
import { SysPositionDeleteDialogComponent } from './delete/sys-position-delete-dialog.component';
import { SysPositionRoutingModule } from './route/sys-position-routing.module';

@NgModule({
  imports: [SharedModule, SysPositionRoutingModule],
  declarations: [SysPositionComponent, SysPositionDetailComponent, SysPositionUpdateComponent, SysPositionDeleteDialogComponent],
})
export class SysPositionModule {}
