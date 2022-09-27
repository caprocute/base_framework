import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { UserRouteAccessService } from 'app/core/auth/user-route-access.service';
import { SysDepartComponent } from '../list/sys-depart.component';
import { SysDepartDetailComponent } from '../detail/sys-depart-detail.component';
import { SysDepartUpdateComponent } from '../update/sys-depart-update.component';
import { SysDepartRoutingResolveService } from './sys-depart-routing-resolve.service';
import { ASC } from 'app/config/navigation.constants';

const sysDepartRoute: Routes = [
  {
    path: '',
    component: SysDepartComponent,
    data: {
      defaultSort: 'id,' + ASC,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: SysDepartDetailComponent,
    resolve: {
      sysDepart: SysDepartRoutingResolveService,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: SysDepartUpdateComponent,
    resolve: {
      sysDepart: SysDepartRoutingResolveService,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: SysDepartUpdateComponent,
    resolve: {
      sysDepart: SysDepartRoutingResolveService,
    },
    canActivate: [UserRouteAccessService],
  },
];

@NgModule({
  imports: [RouterModule.forChild(sysDepartRoute)],
  exports: [RouterModule],
})
export class SysDepartRoutingModule {}
