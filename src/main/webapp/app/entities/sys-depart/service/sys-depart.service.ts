import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import dayjs from 'dayjs/esm';

import { isPresent } from 'app/core/util/operators';
import { DATE_FORMAT } from 'app/config/input.constants';
import { ApplicationConfigService } from 'app/core/config/application-config.service';
import { createRequestOption } from 'app/core/request/request-util';
import { ISysDepart, NewSysDepart } from '../sys-depart.model';

export type PartialUpdateSysDepart = Partial<ISysDepart> & Pick<ISysDepart, 'id'>;

type RestOf<T extends ISysDepart | NewSysDepart> = Omit<T, 'createTime' | 'updateTime'> & {
  createTime?: string | null;
  updateTime?: string | null;
};

export type RestSysDepart = RestOf<ISysDepart>;

export type NewRestSysDepart = RestOf<NewSysDepart>;

export type PartialUpdateRestSysDepart = RestOf<PartialUpdateSysDepart>;

export type EntityResponseType = HttpResponse<ISysDepart>;
export type EntityArrayResponseType = HttpResponse<ISysDepart[]>;

@Injectable({ providedIn: 'root' })
export class SysDepartService {
  protected resourceUrl = this.applicationConfigService.getEndpointFor('api/sys-departs');

  constructor(protected http: HttpClient, protected applicationConfigService: ApplicationConfigService) {}

  create(sysDepart: NewSysDepart): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(sysDepart);
    return this.http
      .post<RestSysDepart>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map(res => this.convertResponseFromServer(res)));
  }

  update(sysDepart: ISysDepart): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(sysDepart);
    return this.http
      .put<RestSysDepart>(`${this.resourceUrl}/${this.getSysDepartIdentifier(sysDepart)}`, copy, { observe: 'response' })
      .pipe(map(res => this.convertResponseFromServer(res)));
  }

  partialUpdate(sysDepart: PartialUpdateSysDepart): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(sysDepart);
    return this.http
      .patch<RestSysDepart>(`${this.resourceUrl}/${this.getSysDepartIdentifier(sysDepart)}`, copy, { observe: 'response' })
      .pipe(map(res => this.convertResponseFromServer(res)));
  }

  find(id: string): Observable<EntityResponseType> {
    return this.http
      .get<RestSysDepart>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map(res => this.convertResponseFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<RestSysDepart[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map(res => this.convertResponseArrayFromServer(res)));
  }

  delete(id: string): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  getSysDepartIdentifier(sysDepart: Pick<ISysDepart, 'id'>): string {
    return sysDepart.id;
  }

  compareSysDepart(o1: Pick<ISysDepart, 'id'> | null, o2: Pick<ISysDepart, 'id'> | null): boolean {
    return o1 && o2 ? this.getSysDepartIdentifier(o1) === this.getSysDepartIdentifier(o2) : o1 === o2;
  }

  addSysDepartToCollectionIfMissing<Type extends Pick<ISysDepart, 'id'>>(
    sysDepartCollection: Type[],
    ...sysDepartsToCheck: (Type | null | undefined)[]
  ): Type[] {
    const sysDeparts: Type[] = sysDepartsToCheck.filter(isPresent);
    if (sysDeparts.length > 0) {
      const sysDepartCollectionIdentifiers = sysDepartCollection.map(sysDepartItem => this.getSysDepartIdentifier(sysDepartItem)!);
      const sysDepartsToAdd = sysDeparts.filter(sysDepartItem => {
        const sysDepartIdentifier = this.getSysDepartIdentifier(sysDepartItem);
        if (sysDepartCollectionIdentifiers.includes(sysDepartIdentifier)) {
          return false;
        }
        sysDepartCollectionIdentifiers.push(sysDepartIdentifier);
        return true;
      });
      return [...sysDepartsToAdd, ...sysDepartCollection];
    }
    return sysDepartCollection;
  }

  protected convertDateFromClient<T extends ISysDepart | NewSysDepart | PartialUpdateSysDepart>(sysDepart: T): RestOf<T> {
    return {
      ...sysDepart,
      createTime: sysDepart.createTime?.format(DATE_FORMAT) ?? null,
      updateTime: sysDepart.updateTime?.format(DATE_FORMAT) ?? null,
    };
  }

  protected convertDateFromServer(restSysDepart: RestSysDepart): ISysDepart {
    return {
      ...restSysDepart,
      createTime: restSysDepart.createTime ? dayjs(restSysDepart.createTime) : undefined,
      updateTime: restSysDepart.updateTime ? dayjs(restSysDepart.updateTime) : undefined,
    };
  }

  protected convertResponseFromServer(res: HttpResponse<RestSysDepart>): HttpResponse<ISysDepart> {
    return res.clone({
      body: res.body ? this.convertDateFromServer(res.body) : null,
    });
  }

  protected convertResponseArrayFromServer(res: HttpResponse<RestSysDepart[]>): HttpResponse<ISysDepart[]> {
    return res.clone({
      body: res.body ? res.body.map(item => this.convertDateFromServer(item)) : null,
    });
  }
}
