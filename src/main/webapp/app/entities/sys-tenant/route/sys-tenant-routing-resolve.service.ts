import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { mergeMap } from 'rxjs/operators';

import { ISysTenant } from '../sys-tenant.model';
import { SysTenantService } from '../service/sys-tenant.service';

@Injectable({ providedIn: 'root' })
export class SysTenantRoutingResolveService implements Resolve<ISysTenant | null> {
  constructor(protected service: SysTenantService, protected router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<ISysTenant | null | never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        mergeMap((sysTenant: HttpResponse<ISysTenant>) => {
          if (sysTenant.body) {
            return of(sysTenant.body);
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
