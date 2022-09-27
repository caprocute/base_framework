import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { UserRouteAccessService } from 'app/core/auth/user-route-access.service';
import { SysUserRoleComponent } from '../list/sys-user-role.component';
import { SysUserRoleDetailComponent } from '../detail/sys-user-role-detail.component';
import { SysUserRoleUpdateComponent } from '../update/sys-user-role-update.component';
import { SysUserRoleRoutingResolveService } from './sys-user-role-routing-resolve.service';
import { ASC } from 'app/config/navigation.constants';

const sysUserRoleRoute: Routes = [
  {
    path: '',
    component: SysUserRoleComponent,
    data: {
      defaultSort: 'id,' + ASC,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: SysUserRoleDetailComponent,
    resolve: {
      sysUserRole: SysUserRoleRoutingResolveService,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: SysUserRoleUpdateComponent,
    resolve: {
      sysUserRole: SysUserRoleRoutingResolveService,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: SysUserRoleUpdateComponent,
    resolve: {
      sysUserRole: SysUserRoleRoutingResolveService,
    },
    canActivate: [UserRouteAccessService],
  },
];

@NgModule({
  imports: [RouterModule.forChild(sysUserRoleRoute)],
  exports: [RouterModule],
})
export class SysUserRoleRoutingModule {}
