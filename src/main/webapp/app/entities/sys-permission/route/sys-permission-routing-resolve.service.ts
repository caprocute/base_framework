import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { mergeMap } from 'rxjs/operators';

import { ISysPermission } from '../sys-permission.model';
import { SysPermissionService } from '../service/sys-permission.service';

@Injectable({ providedIn: 'root' })
export class SysPermissionRoutingResolveService implements Resolve<ISysPermission | null> {
  constructor(protected service: SysPermissionService, protected router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<ISysPermission | null | never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        mergeMap((sysPermission: HttpResponse<ISysPermission>) => {
          if (sysPermission.body) {
            return of(sysPermission.body);
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
