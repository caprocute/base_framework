import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { mergeMap } from 'rxjs/operators';

import { ISysPosition } from '../sys-position.model';
import { SysPositionService } from '../service/sys-position.service';

@Injectable({ providedIn: 'root' })
export class SysPositionRoutingResolveService implements Resolve<ISysPosition | null> {
  constructor(protected service: SysPositionService, protected router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<ISysPosition | null | never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        mergeMap((sysPosition: HttpResponse<ISysPosition>) => {
          if (sysPosition.body) {
            return of(sysPosition.body);
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
