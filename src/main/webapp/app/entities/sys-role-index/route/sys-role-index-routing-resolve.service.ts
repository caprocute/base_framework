import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { mergeMap } from 'rxjs/operators';

import { ISysRoleIndex } from '../sys-role-index.model';
import { SysRoleIndexService } from '../service/sys-role-index.service';

@Injectable({ providedIn: 'root' })
export class SysRoleIndexRoutingResolveService implements Resolve<ISysRoleIndex | null> {
  constructor(protected service: SysRoleIndexService, protected router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<ISysRoleIndex | null | never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        mergeMap((sysRoleIndex: HttpResponse<ISysRoleIndex>) => {
          if (sysRoleIndex.body) {
            return of(sysRoleIndex.body);
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
