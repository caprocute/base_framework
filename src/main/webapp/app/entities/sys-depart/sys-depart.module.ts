import { NgModule } from '@angular/core';
import { SharedModule } from 'app/shared/shared.module';
import { SysDepartComponent } from './list/sys-depart.component';
import { SysDepartDetailComponent } from './detail/sys-depart-detail.component';
import { SysDepartUpdateComponent } from './update/sys-depart-update.component';
import { SysDepartDeleteDialogComponent } from './delete/sys-depart-delete-dialog.component';
import { SysDepartRoutingModule } from './route/sys-depart-routing.module';

@NgModule({
  imports: [SharedModule, SysDepartRoutingModule],
  declarations: [SysDepartComponent, SysDepartDetailComponent, SysDepartUpdateComponent, SysDepartDeleteDialogComponent],
})
export class SysDepartModule {}
