import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { mergeMap } from 'rxjs/operators';

import { ISysUserDepart } from '../sys-user-depart.model';
import { SysUserDepartService } from '../service/sys-user-depart.service';

@Injectable({ providedIn: 'root' })
export class SysUserDepartRoutingResolveService implements Resolve<ISysUserDepart | null> {
  constructor(protected service: SysUserDepartService, protected router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<ISysUserDepart | null | never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        mergeMap((sysUserDepart: HttpResponse<ISysUserDepart>) => {
          if (sysUserDepart.body) {
            return of(sysUserDepart.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(null);
  }
}
