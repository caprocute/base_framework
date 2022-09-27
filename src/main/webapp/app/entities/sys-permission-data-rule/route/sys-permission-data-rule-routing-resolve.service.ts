import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { mergeMap } from 'rxjs/operators';

import { ISysPermissionDataRule } from '../sys-permission-data-rule.model';
import { SysPermissionDataRuleService } from '../service/sys-permission-data-rule.service';

@Injectable({ providedIn: 'root' })
export class SysPermissionDataRuleRoutingResolveService implements Resolve<ISysPermissionDataRule | null> {
  constructor(protected service: SysPermissionDataRuleService, protected router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<ISysPermissionDataRule | null | never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        mergeMap((sysPermissionDataRule: HttpResponse<ISysPermissionDataRule>) => {
          if (sysPermissionDataRule.body) {
            return of(sysPermissionDataRule.body);
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
