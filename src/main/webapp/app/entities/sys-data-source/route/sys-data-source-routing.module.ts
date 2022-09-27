import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { UserRouteAccessService } from 'app/core/auth/user-route-access.service';
import { SysDataSourceComponent } from '../list/sys-data-source.component';
import { SysDataSourceDetailComponent } from '../detail/sys-data-source-detail.component';
import { SysDataSourceUpdateComponent } from '../update/sys-data-source-update.component';
import { SysDataSourceRoutingResolveService } from './sys-data-source-routing-resolve.service';
import { ASC } from 'app/config/navigation.constants';

const sysDataSourceRoute: Routes = [
  {
    path: '',
    component: SysDataSourceComponent,
    data: {
      defaultSort: 'id,' + ASC,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: SysDataSourceDetailComponent,
    resolve: {
      sysDataSource: SysDataSourceRoutingResolveService,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: SysDataSourceUpdateComponent,
    resolve: {
      sysDataSource: SysDataSourceRoutingResolveService,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: SysDataSourceUpdateComponent,
    resolve: {
      sysDataSource: SysDataSourceRoutingResolveService,
    },
    canActivate: [UserRouteAccessService],
  },
];

@NgModule({
  imports: [RouterModule.forChild(sysDataSourceRoute)],
  exports: [RouterModule],
})
export class SysDataSourceRoutingModule {}
