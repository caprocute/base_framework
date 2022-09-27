import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import dayjs from 'dayjs/esm';

import { isPresent } from 'app/core/util/operators';
import { DATE_FORMAT } from 'app/config/input.constants';
import { ApplicationConfigService } from 'app/core/config/application-config.service';
import { createRequestOption } from 'app/core/request/request-util';
import { ISysPermission, NewSysPermission } from '../sys-permission.model';

export type PartialUpdateSysPermission = Partial<ISysPermission> & Pick<ISysPermission, 'id'>;

type RestOf<T extends ISysPermission | NewSysPermission> = Omit<T, 'createTime' | 'updateTime'> & {
  createTime?: string | null;
  updateTime?: string | null;
};

export type RestSysPermission = RestOf<ISysPermission>;

export type NewRestSysPermission = RestOf<NewSysPermission>;

export type PartialUpdateRestSysPermission = RestOf<PartialUpdateSysPermission>;

export type EntityResponseType = HttpResponse<ISysPermission>;
export type EntityArrayResponseType = HttpResponse<ISysPermission[]>;

@Injectable({ providedIn: 'root' })
export class SysPermissionService {
  protected resourceUrl = this.applicationConfigService.getEndpointFor('api/sys-permissions');

  constructor(protected http: HttpClient, protected applicationConfigService: ApplicationConfigService) {}

  create(sysPermission: NewSysPermission): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(sysPermission);
    return this.http
      .post<RestSysPermission>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map(res => this.convertResponseFromServer(res)));
  }

  update(sysPermission: ISysPermission): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(sysPermission);
    return this.http
      .put<RestSysPermission>(`${this.resourceUrl}/${this.getSysPermissionIdentifier(sysPermission)}`, copy, { observe: 'response' })
      .pipe(map(res => this.convertResponseFromServer(res)));
  }

  partialUpdate(sysPermission: PartialUpdateSysPermission): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(sysPermission);
    return this.http
      .patch<RestSysPermission>(`${this.resourceUrl}/${this.getSysPermissionIdentifier(sysPermission)}`, copy, { observe: 'response' })
      .pipe(map(res => this.convertResponseFromServer(res)));
  }

  find(id: string): Observable<EntityResponseType> {
    return this.http
      .get<RestSysPermission>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map(res => this.convertResponseFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<RestSysPermission[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map(res => this.convertResponseArrayFromServer(res)));
  }

  delete(id: string): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  getSysPermissionIdentifier(sysPermission: Pick<ISysPermission, 'id'>): string {
    return sysPermission.id;
  }

  compareSysPermission(o1: Pick<ISysPermission, 'id'> | null, o2: Pick<ISysPermission, 'id'> | null): boolean {
    return o1 && o2 ? this.getSysPermissionIdentifier(o1) === this.getSysPermissionIdentifier(o2) : o1 === o2;
  }

  addSysPermissionToCollectionIfMissing<Type extends Pick<ISysPermission, 'id'>>(
    sysPermissionCollection: Type[],
    ...sysPermissionsToCheck: (Type | null | undefined)[]
  ): Type[] {
    const sysPermissions: Type[] = sysPermissionsToCheck.filter(isPresent);
    if (sysPermissions.length > 0) {
      const sysPermissionCollectionIdentifiers = sysPermissionCollection.map(
        sysPermissionItem => this.getSysPermissionIdentifier(sysPermissionItem)!
      );
      const sysPermissionsToAdd = sysPermissions.filter(sysPermissionItem => {
        const sysPermissionIdentifier = this.getSysPermissionIdentifier(sysPermissionItem);
        if (sysPermissionCollectionIdentifiers.includes(sysPermissionIdentifier)) {
          return false;
        }
        sysPermissionCollectionIdentifiers.push(sysPermissionIdentifier);
        return true;
      });
      return [...sysPermissionsToAdd, ...sysPermissionCollection];
    }
    return sysPermissionCollection;
  }

  protected convertDateFromClient<T extends ISysPermission | NewSysPermission | PartialUpdateSysPermission>(sysPermission: T): RestOf<T> {
    return {
      ...sysPermission,
      createTime: sysPermission.createTime?.format(DATE_FORMAT) ?? null,
      updateTime: sysPermission.updateTime?.format(DATE_FORMAT) ?? null,
    };
  }

  protected convertDateFromServer(restSysPermission: RestSysPermission): ISysPermission {
    return {
      ...restSysPermission,
      createTime: restSysPermission.createTime ? dayjs(restSysPermission.createTime) : undefined,
      updateTime: restSysPermission.updateTime ? dayjs(restSysPermission.updateTime) : undefined,
    };
  }

  protected convertResponseFromServer(res: HttpResponse<RestSysPermission>): HttpResponse<ISysPermission> {
    return res.clone({
      body: res.body ? this.convertDateFromServer(res.body) : null,
    });
  }

  protected convertResponseArrayFromServer(res: HttpResponse<RestSysPermission[]>): HttpResponse<ISysPermission[]> {
    return res.clone({
      body: res.body ? res.body.map(item => this.convertDateFromServer(item)) : null,
    });
  }
}
