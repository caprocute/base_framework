import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { UserRouteAccessService } from 'app/core/auth/user-route-access.service';
import { SysDataLogComponent } from '../list/sys-data-log.component';
import { SysDataLogDetailComponent } from '../detail/sys-data-log-detail.component';
import { SysDataLogUpdateComponent } from '../update/sys-data-log-update.component';
import { SysDataLogRoutingResolveService } from './sys-data-log-routing-resolve.service';
import { ASC } from 'app/config/navigation.constants';

const sysDataLogRoute: Routes = [
  {
    path: '',
    component: SysDataLogComponent,
    data: {
      defaultSort: 'id,' + ASC,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: SysDataLogDetailComponent,
    resolve: {
      sysDataLog: SysDataLogRoutingResolveService,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: SysDataLogUpdateComponent,
    resolve: {
      sysDataLog: SysDataLogRoutingResolveService,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: SysDataLogUpdateComponent,
    resolve: {
      sysDataLog: SysDataLogRoutingResolveService,
    },
    canActivate: [UserRouteAccessService],
  },
];

@NgModule({
  imports: [RouterModule.forChild(sysDataLogRoute)],
  exports: [RouterModule],
})
export class SysDataLogRoutingModule {}
