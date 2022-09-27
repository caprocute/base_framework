import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import dayjs from 'dayjs/esm';

import { isPresent } from 'app/core/util/operators';
import { DATE_FORMAT } from 'app/config/input.constants';
import { ApplicationConfigService } from 'app/core/config/application-config.service';
import { createRequestOption } from 'app/core/request/request-util';
import { ISysRoleIndex, NewSysRoleIndex } from '../sys-role-index.model';

export type PartialUpdateSysRoleIndex = Partial<ISysRoleIndex> & Pick<ISysRoleIndex, 'id'>;

type RestOf<T extends ISysRoleIndex | NewSysRoleIndex> = Omit<T, 'createTime' | 'updateTime'> & {
  createTime?: string | null;
  updateTime?: string | null;
};

export type RestSysRoleIndex = RestOf<ISysRoleIndex>;

export type NewRestSysRoleIndex = RestOf<NewSysRoleIndex>;

export type PartialUpdateRestSysRoleIndex = RestOf<PartialUpdateSysRoleIndex>;

export type EntityResponseType = HttpResponse<ISysRoleIndex>;
export type EntityArrayResponseType = HttpResponse<ISysRoleIndex[]>;

@Injectable({ providedIn: 'root' })
export class SysRoleIndexService {
  protected resourceUrl = this.applicationConfigService.getEndpointFor('api/sys-role-indices');

  constructor(protected http: HttpClient, protected applicationConfigService: ApplicationConfigService) {}

  create(sysRoleIndex: NewSysRoleIndex): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(sysRoleIndex);
    return this.http
      .post<RestSysRoleIndex>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map(res => this.convertResponseFromServer(res)));
  }

  update(sysRoleIndex: ISysRoleIndex): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(sysRoleIndex);
    return this.http
      .put<RestSysRoleIndex>(`${this.resourceUrl}/${this.getSysRoleIndexIdentifier(sysRoleIndex)}`, copy, { observe: 'response' })
      .pipe(map(res => this.convertResponseFromServer(res)));
  }

  partialUpdate(sysRoleIndex: PartialUpdateSysRoleIndex): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(sysRoleIndex);
    return this.http
      .patch<RestSysRoleIndex>(`${this.resourceUrl}/${this.getSysRoleIndexIdentifier(sysRoleIndex)}`, copy, { observe: 'response' })
      .pipe(map(res => this.convertResponseFromServer(res)));
  }

  find(id: string): Observable<EntityResponseType> {
    return this.http
      .get<RestSysRoleIndex>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map(res => this.convertResponseFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<RestSysRoleIndex[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map(res => this.convertResponseArrayFromServer(res)));
  }

  delete(id: string): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  getSysRoleIndexIdentifier(sysRoleIndex: Pick<ISysRoleIndex, 'id'>): string {
    return sysRoleIndex.id;
  }

  compareSysRoleIndex(o1: Pick<ISysRoleIndex, 'id'> | null, o2: Pick<ISysRoleIndex, 'id'> | null): boolean {
    return o1 && o2 ? this.getSysRoleIndexIdentifier(o1) === this.getSysRoleIndexIdentifier(o2) : o1 === o2;
  }

  addSysRoleIndexToCollectionIfMissing<Type extends Pick<ISysRoleIndex, 'id'>>(
    sysRoleIndexCollection: Type[],
    ...sysRoleIndicesToCheck: (Type | null | undefined)[]
  ): Type[] {
    const sysRoleIndices: Type[] = sysRoleIndicesToCheck.filter(isPresent);
    if (sysRoleIndices.length > 0) {
      const sysRoleIndexCollectionIdentifiers = sysRoleIndexCollection.map(
        sysRoleIndexItem => this.getSysRoleIndexIdentifier(sysRoleIndexItem)!
      );
      const sysRoleIndicesToAdd = sysRoleIndices.filter(sysRoleIndexItem => {
        const sysRoleIndexIdentifier = this.getSysRoleIndexIdentifier(sysRoleIndexItem);
        if (sysRoleIndexCollectionIdentifiers.includes(sysRoleIndexIdentifier)) {
          return false;
        }
        sysRoleIndexCollectionIdentifiers.push(sysRoleIndexIdentifier);
        return true;
      });
      return [...sysRoleIndicesToAdd, ...sysRoleIndexCollection];
    }
    return sysRoleIndexCollection;
  }

  protected convertDateFromClient<T extends ISysRoleIndex | NewSysRoleIndex | PartialUpdateSysRoleIndex>(sysRoleIndex: T): RestOf<T> {
    return {
      ...sysRoleIndex,
      createTime: sysRoleIndex.createTime?.format(DATE_FORMAT) ?? null,
      updateTime: sysRoleIndex.updateTime?.format(DATE_FORMAT) ?? null,
    };
  }

  protected convertDateFromServer(restSysRoleIndex: RestSysRoleIndex): ISysRoleIndex {
    return {
      ...restSysRoleIndex,
      createTime: restSysRoleIndex.createTime ? dayjs(restSysRoleIndex.createTime) : undefined,
      updateTime: restSysRoleIndex.updateTime ? dayjs(restSysRoleIndex.updateTime) : undefined,
    };
  }

  protected convertResponseFromServer(res: HttpResponse<RestSysRoleIndex>): HttpResponse<ISysRoleIndex> {
    return res.clone({
      body: res.body ? this.convertDateFromServer(res.body) : null,
    });
  }

  protected convertResponseArrayFromServer(res: HttpResponse<RestSysRoleIndex[]>): HttpResponse<ISysRoleIndex[]> {
    return res.clone({
      body: res.body ? res.body.map(item => this.convertDateFromServer(item)) : null,
    });
  }
}
