import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { mergeMap } from 'rxjs/operators';

import { ISysDataSource } from '../sys-data-source.model';
import { SysDataSourceService } from '../service/sys-data-source.service';

@Injectable({ providedIn: 'root' })
export class SysDataSourceRoutingResolveService implements Resolve<ISysDataSource | null> {
  constructor(protected service: SysDataSourceService, protected router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<ISysDataSource | null | never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        mergeMap((sysDataSource: HttpResponse<ISysDataSource>) => {
          if (sysDataSource.body) {
            return of(sysDataSource.body);
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
