import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { UserRouteAccessService } from 'app/core/auth/user-route-access.service';
import { SysPermissionDataRuleComponent } from '../list/sys-permission-data-rule.component';
import { SysPermissionDataRuleDetailComponent } from '../detail/sys-permission-data-rule-detail.component';
import { SysPermissionDataRuleUpdateComponent } from '../update/sys-permission-data-rule-update.component';
import { SysPermissionDataRuleRoutingResolveService } from './sys-permission-data-rule-routing-resolve.service';
import { ASC } from 'app/config/navigation.constants';

const sysPermissionDataRuleRoute: Routes = [
  {
    path: '',
    component: SysPermissionDataRuleComponent,
    data: {
      defaultSort: 'id,' + ASC,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: SysPermissionDataRuleDetailComponent,
    resolve: {
      sysPermissionDataRule: SysPermissionDataRuleRoutingResolveService,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: SysPermissionDataRuleUpdateComponent,
    resolve: {
      sysPermissionDataRule: SysPermissionDataRuleRoutingResolveService,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: SysPermissionDataRuleUpdateComponent,
    resolve: {
      sysPermissionDataRule: SysPermissionDataRuleRoutingResolveService,
    },
    canActivate: [UserRouteAccessService],
  },
];

@NgModule({
  imports: [RouterModule.forChild(sysPermissionDataRuleRoute)],
  exports: [RouterModule],
})
export class SysPermissionDataRuleRoutingModule {}
