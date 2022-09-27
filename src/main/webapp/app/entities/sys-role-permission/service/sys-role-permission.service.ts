import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import dayjs from 'dayjs/esm';

import { isPresent } from 'app/core/util/operators';
import { DATE_FORMAT } from 'app/config/input.constants';
import { ApplicationConfigService } from 'app/core/config/application-config.service';
import { createRequestOption } from 'app/core/request/request-util';
import { ISysRolePermission, NewSysRolePermission } from '../sys-role-permission.model';

export type PartialUpdateSysRolePermission = Partial<ISysRolePermission> & Pick<ISysRolePermission, 'id'>;

type RestOf<T extends ISysRolePermission | NewSysRolePermission> = Omit<T, 'operateDate'> & {
  operateDate?: string | null;
};

export type RestSysRolePermission = RestOf<ISysRolePermission>;

export type NewRestSysRolePermission = RestOf<NewSysRolePermission>;

export type PartialUpdateRestSysRolePermission = RestOf<PartialUpdateSysRolePermission>;

export type EntityResponseType = HttpResponse<ISysRolePermission>;
export type EntityArrayResponseType = HttpResponse<ISysRolePermission[]>;

@Injectable({ providedIn: 'root' })
export class SysRolePermissionService {
  protected resourceUrl = this.applicationConfigService.getEndpointFor('api/sys-role-permissions');

  constructor(protected http: HttpClient, protected applicationConfigService: ApplicationConfigService) {}

  create(sysRolePermission: NewSysRolePermission): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(sysRolePermission);
    return this.http
      .post<RestSysRolePermission>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map(res => this.convertResponseFromServer(res)));
  }

  update(sysRolePermission: ISysRolePermission): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(sysRolePermission);
    return this.http
      .put<RestSysRolePermission>(`${this.resourceUrl}/${this.getSysRolePermissionIdentifier(sysRolePermission)}`, copy, {
        observe: 'response',
      })
      .pipe(map(res => this.convertResponseFromServer(res)));
  }

  partialUpdate(sysRolePermission: PartialUpdateSysRolePermission): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(sysRolePermission);
    return this.http
      .patch<RestSysRolePermission>(`${this.resourceUrl}/${this.getSysRolePermissionIdentifier(sysRolePermission)}`, copy, {
        observe: 'response',
      })
      .pipe(map(res => this.convertResponseFromServer(res)));
  }

  find(id: string): Observable<EntityResponseType> {
    return this.http
      .get<RestSysRolePermission>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map(res => this.convertResponseFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<RestSysRolePermission[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map(res => this.convertResponseArrayFromServer(res)));
  }

  delete(id: string): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  getSysRolePermissionIdentifier(sysRolePermission: Pick<ISysRolePermission, 'id'>): string {
    return sysRolePermission.id;
  }

  compareSysRolePermission(o1: Pick<ISysRolePermission, 'id'> | null, o2: Pick<ISysRolePermission, 'id'> | null): boolean {
    return o1 && o2 ? this.getSysRolePermissionIdentifier(o1) === this.getSysRolePermissionIdentifier(o2) : o1 === o2;
  }

  addSysRolePermissionToCollectionIfMissing<Type extends Pick<ISysRolePermission, 'id'>>(
    sysRolePermissionCollection: Type[],
    ...sysRolePermissionsToCheck: (Type | null | undefined)[]
  ): Type[] {
    const sysRolePermissions: Type[] = sysRolePermissionsToCheck.filter(isPresent);
    if (sysRolePermissions.length > 0) {
      const sysRolePermissionCollectionIdentifiers = sysRolePermissionCollection.map(
        sysRolePermissionItem => this.getSysRolePermissionIdentifier(sysRolePermissionItem)!
      );
      const sysRolePermissionsToAdd = sysRolePermissions.filter(sysRolePermissionItem => {
        const sysRolePermissionIdentifier = this.getSysRolePermissionIdentifier(sysRolePermissionItem);
        if (sysRolePermissionCollectionIdentifiers.includes(sysRolePermissionIdentifier)) {
          return false;
        }
        sysRolePermissionCollectionIdentifiers.push(sysRolePermissionIdentifier);
        return true;
      });
      return [...sysRolePermissionsToAdd, ...sysRolePermissionCollection];
    }
    return sysRolePermissionCollection;
  }

  protected convertDateFromClient<T extends ISysRolePermission | NewSysRolePermission | PartialUpdateSysRolePermission>(
    sysRolePermission: T
  ): RestOf<T> {
    return {
      ...sysRolePermission,
      operateDate: sysRolePermission.operateDate?.format(DATE_FORMAT) ?? null,
    };
  }

  protected convertDateFromServer(restSysRolePermission: RestSysRolePermission): ISysRolePermission {
    return {
      ...restSysRolePermission,
      operateDate: restSysRolePermission.operateDate ? dayjs(restSysRolePermission.operateDate) : undefined,
    };
  }

  protected convertResponseFromServer(res: HttpResponse<RestSysRolePermission>): HttpResponse<ISysRolePermission> {
    return res.clone({
      body: res.body ? this.convertDateFromServer(res.body) : null,
    });
  }

  protected convertResponseArrayFromServer(res: HttpResponse<RestSysRolePermission[]>): HttpResponse<ISysRolePermission[]> {
    return res.clone({
      body: res.body ? res.body.map(item => this.convertDateFromServer(item)) : null,
    });
  }
}
