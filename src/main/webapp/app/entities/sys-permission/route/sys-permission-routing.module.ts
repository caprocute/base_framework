import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { UserRouteAccessService } from 'app/core/auth/user-route-access.service';
import { SysPermissionComponent } from '../list/sys-permission.component';
import { SysPermissionDetailComponent } from '../detail/sys-permission-detail.component';
import { SysPermissionUpdateComponent } from '../update/sys-permission-update.component';
import { SysPermissionRoutingResolveService } from './sys-permission-routing-resolve.service';
import { ASC } from 'app/config/navigation.constants';

const sysPermissionRoute: Routes = [
  {
    path: '',
    component: SysPermissionComponent,
    data: {
      defaultSort: 'id,' + ASC,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: SysPermissionDetailComponent,
    resolve: {
      sysPermission: SysPermissionRoutingResolveService,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: SysPermissionUpdateComponent,
    resolve: {
      sysPermission: SysPermissionRoutingResolveService,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: SysPermissionUpdateComponent,
    resolve: {
      sysPermission: SysPermissionRoutingResolveService,
    },
    canActivate: [UserRouteAccessService],
  },
];

@NgModule({
  imports: [RouterModule.forChild(sysPermissionRoute)],
  exports: [RouterModule],
})
export class SysPermissionRoutingModule {}
