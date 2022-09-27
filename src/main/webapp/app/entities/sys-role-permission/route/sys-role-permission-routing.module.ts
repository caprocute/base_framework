import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { UserRouteAccessService } from 'app/core/auth/user-route-access.service';
import { SysRolePermissionComponent } from '../list/sys-role-permission.component';
import { SysRolePermissionDetailComponent } from '../detail/sys-role-permission-detail.component';
import { SysRolePermissionUpdateComponent } from '../update/sys-role-permission-update.component';
import { SysRolePermissionRoutingResolveService } from './sys-role-permission-routing-resolve.service';
import { ASC } from 'app/config/navigation.constants';

const sysRolePermissionRoute: Routes = [
  {
    path: '',
    component: SysRolePermissionComponent,
    data: {
      defaultSort: 'id,' + ASC,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: SysRolePermissionDetailComponent,
    resolve: {
      sysRolePermission: SysRolePermissionRoutingResolveService,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: SysRolePermissionUpdateComponent,
    resolve: {
      sysRolePermission: SysRolePermissionRoutingResolveService,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: SysRolePermissionUpdateComponent,
    resolve: {
      sysRolePermission: SysRolePermissionRoutingResolveService,
    },
    canActivate: [UserRouteAccessService],
  },
];

@NgModule({
  imports: [RouterModule.forChild(sysRolePermissionRoute)],
  exports: [RouterModule],
})
export class SysRolePermissionRoutingModule {}
