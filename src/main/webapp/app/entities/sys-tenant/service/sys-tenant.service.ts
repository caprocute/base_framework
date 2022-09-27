import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import dayjs from 'dayjs/esm';

import { isPresent } from 'app/core/util/operators';
import { DATE_FORMAT } from 'app/config/input.constants';
import { ApplicationConfigService } from 'app/core/config/application-config.service';
import { createRequestOption } from 'app/core/request/request-util';
import { ISysTenant, NewSysTenant } from '../sys-tenant.model';

export type PartialUpdateSysTenant = Partial<ISysTenant> & Pick<ISysTenant, 'id'>;

type RestOf<T extends ISysTenant | NewSysTenant> = Omit<T, 'createTime' | 'updateTime'> & {
  createTime?: string | null;
  updateTime?: string | null;
};

export type RestSysTenant = RestOf<ISysTenant>;

export type NewRestSysTenant = RestOf<NewSysTenant>;

export type PartialUpdateRestSysTenant = RestOf<PartialUpdateSysTenant>;

export type EntityResponseType = HttpResponse<ISysTenant>;
export type EntityArrayResponseType = HttpResponse<ISysTenant[]>;

@Injectable({ providedIn: 'root' })
export class SysTenantService {
  protected resourceUrl = this.applicationConfigService.getEndpointFor('api/sys-tenants');

  constructor(protected http: HttpClient, protected applicationConfigService: ApplicationConfigService) {}

  create(sysTenant: NewSysTenant): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(sysTenant);
    return this.http
      .post<RestSysTenant>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map(res => this.convertResponseFromServer(res)));
  }

  update(sysTenant: ISysTenant): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(sysTenant);
    return this.http
      .put<RestSysTenant>(`${this.resourceUrl}/${this.getSysTenantIdentifier(sysTenant)}`, copy, { observe: 'response' })
      .pipe(map(res => this.convertResponseFromServer(res)));
  }

  partialUpdate(sysTenant: PartialUpdateSysTenant): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(sysTenant);
    return this.http
      .patch<RestSysTenant>(`${this.resourceUrl}/${this.getSysTenantIdentifier(sysTenant)}`, copy, { observe: 'response' })
      .pipe(map(res => this.convertResponseFromServer(res)));
  }

  find(id: string): Observable<EntityResponseType> {
    return this.http
      .get<RestSysTenant>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map(res => this.convertResponseFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<RestSysTenant[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map(res => this.convertResponseArrayFromServer(res)));
  }

  delete(id: string): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  getSysTenantIdentifier(sysTenant: Pick<ISysTenant, 'id'>): string {
    return sysTenant.id;
  }

  compareSysTenant(o1: Pick<ISysTenant, 'id'> | null, o2: Pick<ISysTenant, 'id'> | null): boolean {
    return o1 && o2 ? this.getSysTenantIdentifier(o1) === this.getSysTenantIdentifier(o2) : o1 === o2;
  }

  addSysTenantToCollectionIfMissing<Type extends Pick<ISysTenant, 'id'>>(
    sysTenantCollection: Type[],
    ...sysTenantsToCheck: (Type | null | undefined)[]
  ): Type[] {
    const sysTenants: Type[] = sysTenantsToCheck.filter(isPresent);
    if (sysTenants.length > 0) {
      const sysTenantCollectionIdentifiers = sysTenantCollection.map(sysTenantItem => this.getSysTenantIdentifier(sysTenantItem)!);
      const sysTenantsToAdd = sysTenants.filter(sysTenantItem => {
        const sysTenantIdentifier = this.getSysTenantIdentifier(sysTenantItem);
        if (sysTenantCollectionIdentifiers.includes(sysTenantIdentifier)) {
          return false;
        }
        sysTenantCollectionIdentifiers.push(sysTenantIdentifier);
        return true;
      });
      return [...sysTenantsToAdd, ...sysTenantCollection];
    }
    return sysTenantCollection;
  }

  protected convertDateFromClient<T extends ISysTenant | NewSysTenant | PartialUpdateSysTenant>(sysTenant: T): RestOf<T> {
    return {
      ...sysTenant,
      createTime: sysTenant.createTime?.format(DATE_FORMAT) ?? null,
      updateTime: sysTenant.updateTime?.format(DATE_FORMAT) ?? null,
    };
  }

  protected convertDateFromServer(restSysTenant: RestSysTenant): ISysTenant {
    return {
      ...restSysTenant,
      createTime: restSysTenant.createTime ? dayjs(restSysTenant.createTime) : undefined,
      updateTime: restSysTenant.updateTime ? dayjs(restSysTenant.updateTime) : undefined,
    };
  }

  protected convertResponseFromServer(res: HttpResponse<RestSysTenant>): HttpResponse<ISysTenant> {
    return res.clone({
      body: res.body ? this.convertDateFromServer(res.body) : null,
    });
  }

  protected convertResponseArrayFromServer(res: HttpResponse<RestSysTenant[]>): HttpResponse<ISysTenant[]> {
    return res.clone({
      body: res.body ? res.body.map(item => this.convertDateFromServer(item)) : null,
    });
  }
}
