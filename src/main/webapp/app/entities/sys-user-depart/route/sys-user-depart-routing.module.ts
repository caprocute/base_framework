import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { UserRouteAccessService } from 'app/core/auth/user-route-access.service';
import { SysUserDepartComponent } from '../list/sys-user-depart.component';
import { SysUserDepartDetailComponent } from '../detail/sys-user-depart-detail.component';
import { SysUserDepartUpdateComponent } from '../update/sys-user-depart-update.component';
import { SysUserDepartRoutingResolveService } from './sys-user-depart-routing-resolve.service';
import { ASC } from 'app/config/navigation.constants';

const sysUserDepartRoute: Routes = [
  {
    path: '',
    component: SysUserDepartComponent,
    data: {
      defaultSort: 'id,' + ASC,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: SysUserDepartDetailComponent,
    resolve: {
      sysUserDepart: SysUserDepartRoutingResolveService,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: SysUserDepartUpdateComponent,
    resolve: {
      sysUserDepart: SysUserDepartRoutingResolveService,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: SysUserDepartUpdateComponent,
    resolve: {
      sysUserDepart: SysUserDepartRoutingResolveService,
    },
    canActivate: [UserRouteAccessService],
  },
];

@NgModule({
  imports: [RouterModule.forChild(sysUserDepartRoute)],
  exports: [RouterModule],
})
export class SysUserDepartRoutingModule {}
