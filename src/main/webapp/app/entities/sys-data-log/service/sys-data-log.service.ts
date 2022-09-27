import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import dayjs from 'dayjs/esm';

import { isPresent } from 'app/core/util/operators';
import { DATE_FORMAT } from 'app/config/input.constants';
import { ApplicationConfigService } from 'app/core/config/application-config.service';
import { createRequestOption } from 'app/core/request/request-util';
import { ISysDataLog, NewSysDataLog } from '../sys-data-log.model';

export type PartialUpdateSysDataLog = Partial<ISysDataLog> & Pick<ISysDataLog, 'id'>;

type RestOf<T extends ISysDataLog | NewSysDataLog> = Omit<T, 'createTime' | 'updateTime'> & {
  createTime?: string | null;
  updateTime?: string | null;
};

export type RestSysDataLog = RestOf<ISysDataLog>;

export type NewRestSysDataLog = RestOf<NewSysDataLog>;

export type PartialUpdateRestSysDataLog = RestOf<PartialUpdateSysDataLog>;

export type EntityResponseType = HttpResponse<ISysDataLog>;
export type EntityArrayResponseType = HttpResponse<ISysDataLog[]>;

@Injectable({ providedIn: 'root' })
export class SysDataLogService {
  protected resourceUrl = this.applicationConfigService.getEndpointFor('api/sys-data-logs');

  constructor(protected http: HttpClient, protected applicationConfigService: ApplicationConfigService) {}

  create(sysDataLog: NewSysDataLog): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(sysDataLog);
    return this.http
      .post<RestSysDataLog>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map(res => this.convertResponseFromServer(res)));
  }

  update(sysDataLog: ISysDataLog): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(sysDataLog);
    return this.http
      .put<RestSysDataLog>(`${this.resourceUrl}/${this.getSysDataLogIdentifier(sysDataLog)}`, copy, { observe: 'response' })
      .pipe(map(res => this.convertResponseFromServer(res)));
  }

  partialUpdate(sysDataLog: PartialUpdateSysDataLog): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(sysDataLog);
    return this.http
      .patch<RestSysDataLog>(`${this.resourceUrl}/${this.getSysDataLogIdentifier(sysDataLog)}`, copy, { observe: 'response' })
      .pipe(map(res => this.convertResponseFromServer(res)));
  }

  find(id: string): Observable<EntityResponseType> {
    return this.http
      .get<RestSysDataLog>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map(res => this.convertResponseFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<RestSysDataLog[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map(res => this.convertResponseArrayFromServer(res)));
  }

  delete(id: string): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  getSysDataLogIdentifier(sysDataLog: Pick<ISysDataLog, 'id'>): string {
    return sysDataLog.id;
  }

  compareSysDataLog(o1: Pick<ISysDataLog, 'id'> | null, o2: Pick<ISysDataLog, 'id'> | null): boolean {
    return o1 && o2 ? this.getSysDataLogIdentifier(o1) === this.getSysDataLogIdentifier(o2) : o1 === o2;
  }

  addSysDataLogToCollectionIfMissing<Type extends Pick<ISysDataLog, 'id'>>(
    sysDataLogCollection: Type[],
    ...sysDataLogsToCheck: (Type | null | undefined)[]
  ): Type[] {
    const sysDataLogs: Type[] = sysDataLogsToCheck.filter(isPresent);
    if (sysDataLogs.length > 0) {
      const sysDataLogCollectionIdentifiers = sysDataLogCollection.map(sysDataLogItem => this.getSysDataLogIdentifier(sysDataLogItem)!);
      const sysDataLogsToAdd = sysDataLogs.filter(sysDataLogItem => {
        const sysDataLogIdentifier = this.getSysDataLogIdentifier(sysDataLogItem);
        if (sysDataLogCollectionIdentifiers.includes(sysDataLogIdentifier)) {
          return false;
        }
        sysDataLogCollectionIdentifiers.push(sysDataLogIdentifier);
        return true;
      });
      return [...sysDataLogsToAdd, ...sysDataLogCollection];
    }
    return sysDataLogCollection;
  }

  protected convertDateFromClient<T extends ISysDataLog | NewSysDataLog | PartialUpdateSysDataLog>(sysDataLog: T): RestOf<T> {
    return {
      ...sysDataLog,
      createTime: sysDataLog.createTime?.format(DATE_FORMAT) ?? null,
      updateTime: sysDataLog.updateTime?.format(DATE_FORMAT) ?? null,
    };
  }

  protected convertDateFromServer(restSysDataLog: RestSysDataLog): ISysDataLog {
    return {
      ...restSysDataLog,
      createTime: restSysDataLog.createTime ? dayjs(restSysDataLog.createTime) : undefined,
      updateTime: restSysDataLog.updateTime ? dayjs(restSysDataLog.updateTime) : undefined,
    };
  }

  protected convertResponseFromServer(res: HttpResponse<RestSysDataLog>): HttpResponse<ISysDataLog> {
    return res.clone({
      body: res.body ? this.convertDateFromServer(res.body) : null,
    });
  }

  protected convertResponseArrayFromServer(res: HttpResponse<RestSysDataLog[]>): HttpResponse<ISysDataLog[]> {
    return res.clone({
      body: res.body ? res.body.map(item => this.convertDateFromServer(item)) : null,
    });
  }
}
