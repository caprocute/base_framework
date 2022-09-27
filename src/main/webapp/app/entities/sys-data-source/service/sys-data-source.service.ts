import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import dayjs from 'dayjs/esm';

import { isPresent } from 'app/core/util/operators';
import { DATE_FORMAT } from 'app/config/input.constants';
import { ApplicationConfigService } from 'app/core/config/application-config.service';
import { createRequestOption } from 'app/core/request/request-util';
import { ISysDataSource, NewSysDataSource } from '../sys-data-source.model';

export type PartialUpdateSysDataSource = Partial<ISysDataSource> & Pick<ISysDataSource, 'id'>;

type RestOf<T extends ISysDataSource | NewSysDataSource> = Omit<T, 'createTime' | 'updateTime'> & {
  createTime?: string | null;
  updateTime?: string | null;
};

export type RestSysDataSource = RestOf<ISysDataSource>;

export type NewRestSysDataSource = RestOf<NewSysDataSource>;

export type PartialUpdateRestSysDataSource = RestOf<PartialUpdateSysDataSource>;

export type EntityResponseType = HttpResponse<ISysDataSource>;
export type EntityArrayResponseType = HttpResponse<ISysDataSource[]>;

@Injectable({ providedIn: 'root' })
export class SysDataSourceService {
  protected resourceUrl = this.applicationConfigService.getEndpointFor('api/sys-data-sources');

  constructor(protected http: HttpClient, protected applicationConfigService: ApplicationConfigService) {}

  create(sysDataSource: NewSysDataSource): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(sysDataSource);
    return this.http
      .post<RestSysDataSource>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map(res => this.convertResponseFromServer(res)));
  }

  update(sysDataSource: ISysDataSource): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(sysDataSource);
    return this.http
      .put<RestSysDataSource>(`${this.resourceUrl}/${this.getSysDataSourceIdentifier(sysDataSource)}`, copy, { observe: 'response' })
      .pipe(map(res => this.convertResponseFromServer(res)));
  }

  partialUpdate(sysDataSource: PartialUpdateSysDataSource): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(sysDataSource);
    return this.http
      .patch<RestSysDataSource>(`${this.resourceUrl}/${this.getSysDataSourceIdentifier(sysDataSource)}`, copy, { observe: 'response' })
      .pipe(map(res => this.convertResponseFromServer(res)));
  }

  find(id: string): Observable<EntityResponseType> {
    return this.http
      .get<RestSysDataSource>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map(res => this.convertResponseFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<RestSysDataSource[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map(res => this.convertResponseArrayFromServer(res)));
  }

  delete(id: string): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  getSysDataSourceIdentifier(sysDataSource: Pick<ISysDataSource, 'id'>): string {
    return sysDataSource.id;
  }

  compareSysDataSource(o1: Pick<ISysDataSource, 'id'> | null, o2: Pick<ISysDataSource, 'id'> | null): boolean {
    return o1 && o2 ? this.getSysDataSourceIdentifier(o1) === this.getSysDataSourceIdentifier(o2) : o1 === o2;
  }

  addSysDataSourceToCollectionIfMissing<Type extends Pick<ISysDataSource, 'id'>>(
    sysDataSourceCollection: Type[],
    ...sysDataSourcesToCheck: (Type | null | undefined)[]
  ): Type[] {
    const sysDataSources: Type[] = sysDataSourcesToCheck.filter(isPresent);
    if (sysDataSources.length > 0) {
      const sysDataSourceCollectionIdentifiers = sysDataSourceCollection.map(
        sysDataSourceItem => this.getSysDataSourceIdentifier(sysDataSourceItem)!
      );
      const sysDataSourcesToAdd = sysDataSources.filter(sysDataSourceItem => {
        const sysDataSourceIdentifier = this.getSysDataSourceIdentifier(sysDataSourceItem);
        if (sysDataSourceCollectionIdentifiers.includes(sysDataSourceIdentifier)) {
          return false;
        }
        sysDataSourceCollectionIdentifiers.push(sysDataSourceIdentifier);
        return true;
      });
      return [...sysDataSourcesToAdd, ...sysDataSourceCollection];
    }
    return sysDataSourceCollection;
  }

  protected convertDateFromClient<T extends ISysDataSource | NewSysDataSource | PartialUpdateSysDataSource>(sysDataSource: T): RestOf<T> {
    return {
      ...sysDataSource,
      createTime: sysDataSource.createTime?.format(DATE_FORMAT) ?? null,
      updateTime: sysDataSource.updateTime?.format(DATE_FORMAT) ?? null,
    };
  }

  protected convertDateFromServer(restSysDataSource: RestSysDataSource): ISysDataSource {
    return {
      ...restSysDataSource,
      createTime: restSysDataSource.createTime ? dayjs(restSysDataSource.createTime) : undefined,
      updateTime: restSysDataSource.updateTime ? dayjs(restSysDataSource.updateTime) : undefined,
    };
  }

  protected convertResponseFromServer(res: HttpResponse<RestSysDataSource>): HttpResponse<ISysDataSource> {
    return res.clone({
      body: res.body ? this.convertDateFromServer(res.body) : null,
    });
  }

  protected convertResponseArrayFromServer(res: HttpResponse<RestSysDataSource[]>): HttpResponse<ISysDataSource[]> {
    return res.clone({
      body: res.body ? res.body.map(item => this.convertDateFromServer(item)) : null,
    });
  }
}
