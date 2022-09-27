import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { mergeMap } from 'rxjs/operators';

import { ISysUserRole } from '../sys-user-role.model';
import { SysUserRoleService } from '../service/sys-user-role.service';

@Injectable({ providedIn: 'root' })
export class SysUserRoleRoutingResolveService implements Resolve<ISysUserRole | null> {
  constructor(protected service: SysUserRoleService, protected router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<ISysUserRole | null | never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        mergeMap((sysUserRole: HttpResponse<ISysUserRole>) => {
          if (sysUserRole.body) {
            return of(sysUserRole.body);
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
