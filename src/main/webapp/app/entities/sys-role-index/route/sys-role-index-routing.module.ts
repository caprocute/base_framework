import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { UserRouteAccessService } from 'app/core/auth/user-route-access.service';
import { SysRoleIndexComponent } from '../list/sys-role-index.component';
import { SysRoleIndexDetailComponent } from '../detail/sys-role-index-detail.component';
import { SysRoleIndexUpdateComponent } from '../update/sys-role-index-update.component';
import { SysRoleIndexRoutingResolveService } from './sys-role-index-routing-resolve.service';
import { ASC } from 'app/config/navigation.constants';

const sysRoleIndexRoute: Routes = [
  {
    path: '',
    component: SysRoleIndexComponent,
    data: {
      defaultSort: 'id,' + ASC,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: SysRoleIndexDetailComponent,
    resolve: {
      sysRoleIndex: SysRoleIndexRoutingResolveService,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: SysRoleIndexUpdateComponent,
    resolve: {
      sysRoleIndex: SysRoleIndexRoutingResolveService,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: SysRoleIndexUpdateComponent,
    resolve: {
      sysRoleIndex: SysRoleIndexRoutingResolveService,
    },
    canActivate: [UserRouteAccessService],
  },
];

@NgModule({
  imports: [RouterModule.forChild(sysRoleIndexRoute)],
  exports: [RouterModule],
})
export class SysRoleIndexRoutingModule {}
