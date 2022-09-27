import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { UserRouteAccessService } from 'app/core/auth/user-route-access.service';
import { SysDepartRoleComponent } from '../list/sys-depart-role.component';
import { SysDepartRoleDetailComponent } from '../detail/sys-depart-role-detail.component';
import { SysDepartRoleUpdateComponent } from '../update/sys-depart-role-update.component';
import { SysDepartRoleRoutingResolveService } from './sys-depart-role-routing-resolve.service';
import { ASC } from 'app/config/navigation.constants';

const sysDepartRoleRoute: Routes = [
  {
    path: '',
    component: SysDepartRoleComponent,
    data: {
      defaultSort: 'id,' + ASC,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: SysDepartRoleDetailComponent,
    resolve: {
      sysDepartRole: SysDepartRoleRoutingResolveService,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: SysDepartRoleUpdateComponent,
    resolve: {
      sysDepartRole: SysDepartRoleRoutingResolveService,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: SysDepartRoleUpdateComponent,
    resolve: {
      sysDepartRole: SysDepartRoleRoutingResolveService,
    },
    canActivate: [UserRouteAccessService],
  },
];

@NgModule({
  imports: [RouterModule.forChild(sysDepartRoleRoute)],
  exports: [RouterModule],
})
export class SysDepartRoleRoutingModule {}
