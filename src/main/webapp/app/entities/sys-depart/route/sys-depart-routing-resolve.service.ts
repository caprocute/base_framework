import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { mergeMap } from 'rxjs/operators';

import { ISysDepart } from '../sys-depart.model';
import { SysDepartService } from '../service/sys-depart.service';

@Injectable({ providedIn: 'root' })
export class SysDepartRoutingResolveService implements Resolve<ISysDepart | null> {
  constructor(protected service: SysDepartService, protected router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<ISysDepart | null | never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        mergeMap((sysDepart: HttpResponse<ISysDepart>) => {
          if (sysDepart.body) {
            return of(sysDepart.body);
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
