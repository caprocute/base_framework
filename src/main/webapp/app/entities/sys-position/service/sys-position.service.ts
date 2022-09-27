import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import dayjs from 'dayjs/esm';

import { isPresent } from 'app/core/util/operators';
import { DATE_FORMAT } from 'app/config/input.constants';
import { ApplicationConfigService } from 'app/core/config/application-config.service';
import { createRequestOption } from 'app/core/request/request-util';
import { ISysPosition, NewSysPosition } from '../sys-position.model';

export type PartialUpdateSysPosition = Partial<ISysPosition> & Pick<ISysPosition, 'id'>;

type RestOf<T extends ISysPosition | NewSysPosition> = Omit<T, 'createTime' | 'updateTime'> & {
  createTime?: string | null;
  updateTime?: string | null;
};

export type RestSysPosition = RestOf<ISysPosition>;

export type NewRestSysPosition = RestOf<NewSysPosition>;

export type PartialUpdateRestSysPosition = RestOf<PartialUpdateSysPosition>;

export type EntityResponseType = HttpResponse<ISysPosition>;
export type EntityArrayResponseType = HttpResponse<ISysPosition[]>;

@Injectable({ providedIn: 'root' })
export class SysPositionService {
  protected resourceUrl = this.applicationConfigService.getEndpointFor('api/sys-positions');

  constructor(protected http: HttpClient, protected applicationConfigService: ApplicationConfigService) {}

  create(sysPosition: NewSysPosition): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(sysPosition);
    return this.http
      .post<RestSysPosition>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map(res => this.convertResponseFromServer(res)));
  }

  update(sysPosition: ISysPosition): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(sysPosition);
    return this.http
      .put<RestSysPosition>(`${this.resourceUrl}/${this.getSysPositionIdentifier(sysPosition)}`, copy, { observe: 'response' })
      .pipe(map(res => this.convertResponseFromServer(res)));
  }

  partialUpdate(sysPosition: PartialUpdateSysPosition): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(sysPosition);
    return this.http
      .patch<RestSysPosition>(`${this.resourceUrl}/${this.getSysPositionIdentifier(sysPosition)}`, copy, { observe: 'response' })
      .pipe(map(res => this.convertResponseFromServer(res)));
  }

  find(id: string): Observable<EntityResponseType> {
    return this.http
      .get<RestSysPosition>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map(res => this.convertResponseFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<RestSysPosition[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map(res => this.convertResponseArrayFromServer(res)));
  }

  delete(id: string): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  getSysPositionIdentifier(sysPosition: Pick<ISysPosition, 'id'>): string {
    return sysPosition.id;
  }

  compareSysPosition(o1: Pick<ISysPosition, 'id'> | null, o2: Pick<ISysPosition, 'id'> | null): boolean {
    return o1 && o2 ? this.getSysPositionIdentifier(o1) === this.getSysPositionIdentifier(o2) : o1 === o2;
  }

  addSysPositionToCollectionIfMissing<Type extends Pick<ISysPosition, 'id'>>(
    sysPositionCollection: Type[],
    ...sysPositionsToCheck: (Type | null | undefined)[]
  ): Type[] {
    const sysPositions: Type[] = sysPositionsToCheck.filter(isPresent);
    if (sysPositions.length > 0) {
      const sysPositionCollectionIdentifiers = sysPositionCollection.map(
        sysPositionItem => this.getSysPositionIdentifier(sysPositionItem)!
      );
      const sysPositionsToAdd = sysPositions.filter(sysPositionItem => {
        const sysPositionIdentifier = this.getSysPositionIdentifier(sysPositionItem);
        if (sysPositionCollectionIdentifiers.includes(sysPositionIdentifier)) {
          return false;
        }
        sysPositionCollectionIdentifiers.push(sysPositionIdentifier);
        return true;
      });
      return [...sysPositionsToAdd, ...sysPositionCollection];
    }
    return sysPositionCollection;
  }

  protected convertDateFromClient<T extends ISysPosition | NewSysPosition | PartialUpdateSysPosition>(sysPosition: T): RestOf<T> {
    return {
      ...sysPosition,
      createTime: sysPosition.createTime?.format(DATE_FORMAT) ?? null,
      updateTime: sysPosition.updateTime?.format(DATE_FORMAT) ?? null,
    };
  }

  protected convertDateFromServer(restSysPosition: RestSysPosition): ISysPosition {
    return {
      ...restSysPosition,
      createTime: restSysPosition.createTime ? dayjs(restSysPosition.createTime) : undefined,
      updateTime: restSysPosition.updateTime ? dayjs(restSysPosition.updateTime) : undefined,
    };
  }

  protected convertResponseFromServer(res: HttpResponse<RestSysPosition>): HttpResponse<ISysPosition> {
    return res.clone({
      body: res.body ? this.convertDateFromServer(res.body) : null,
    });
  }

  protected convertResponseArrayFromServer(res: HttpResponse<RestSysPosition[]>): HttpResponse<ISysPosition[]> {
    return res.clone({
      body: res.body ? res.body.map(item => this.convertDateFromServer(item)) : null,
    });
  }
}
