import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { UserRouteAccessService } from 'app/core/auth/user-route-access.service';
import { SysTenantComponent } from '../list/sys-tenant.component';
import { SysTenantDetailComponent } from '../detail/sys-tenant-detail.component';
import { SysTenantUpdateComponent } from '../update/sys-tenant-update.component';
import { SysTenantRoutingResolveService } from './sys-tenant-routing-resolve.service';
import { ASC } from 'app/config/navigation.constants';

const sysTenantRoute: Routes = [
  {
    path: '',
    component: SysTenantComponent,
    data: {
      defaultSort: 'id,' + ASC,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: SysTenantDetailComponent,
    resolve: {
      sysTenant: SysTenantRoutingResolveService,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: SysTenantUpdateComponent,
    resolve: {
      sysTenant: SysTenantRoutingResolveService,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: SysTenantUpdateComponent,
    resolve: {
      sysTenant: SysTenantRoutingResolveService,
    },
    canActivate: [UserRouteAccessService],
  },
];

@NgModule({
  imports: [RouterModule.forChild(sysTenantRoute)],
  exports: [RouterModule],
})
export class SysTenantRoutingModule {}
