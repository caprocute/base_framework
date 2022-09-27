import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { mergeMap } from 'rxjs/operators';

import { ISysDepartRole } from '../sys-depart-role.model';
import { SysDepartRoleService } from '../service/sys-depart-role.service';

@Injectable({ providedIn: 'root' })
export class SysDepartRoleRoutingResolveService implements Resolve<ISysDepartRole | null> {
  constructor(protected service: SysDepartRoleService, protected router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<ISysDepartRole | null | never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        mergeMap((sysDepartRole: HttpResponse<ISysDepartRole>) => {
          if (sysDepartRole.body) {
            return of(sysDepartRole.body);
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
