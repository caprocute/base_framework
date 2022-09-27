import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { mergeMap } from 'rxjs/operators';

import { ISysDataLog } from '../sys-data-log.model';
import { SysDataLogService } from '../service/sys-data-log.service';

@Injectable({ providedIn: 'root' })
export class SysDataLogRoutingResolveService implements Resolve<ISysDataLog | null> {
  constructor(protected service: SysDataLogService, protected router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<ISysDataLog | null | never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        mergeMap((sysDataLog: HttpResponse<ISysDataLog>) => {
          if (sysDataLog.body) {
            return of(sysDataLog.body);
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
