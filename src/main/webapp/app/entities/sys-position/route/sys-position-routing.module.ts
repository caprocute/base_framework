import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { UserRouteAccessService } from 'app/core/auth/user-route-access.service';
import { SysPositionComponent } from '../list/sys-position.component';
import { SysPositionDetailComponent } from '../detail/sys-position-detail.component';
import { SysPositionUpdateComponent } from '../update/sys-position-update.component';
import { SysPositionRoutingResolveService } from './sys-position-routing-resolve.service';
import { ASC } from 'app/config/navigation.constants';

const sysPositionRoute: Routes = [
  {
    path: '',
    component: SysPositionComponent,
    data: {
      defaultSort: 'id,' + ASC,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: SysPositionDetailComponent,
    resolve: {
      sysPosition: SysPositionRoutingResolveService,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: SysPositionUpdateComponent,
    resolve: {
      sysPosition: SysPositionRoutingResolveService,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: SysPositionUpdateComponent,
    resolve: {
      sysPosition: SysPositionRoutingResolveService,
    },
    canActivate: [UserRouteAccessService],
  },
];

@NgModule({
  imports: [RouterModule.forChild(sysPositionRoute)],
  exports: [RouterModule],
})
export class SysPositionRoutingModule {}
