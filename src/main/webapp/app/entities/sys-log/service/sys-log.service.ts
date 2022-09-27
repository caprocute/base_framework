import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import dayjs from 'dayjs/esm';

import { isPresent } from 'app/core/util/operators';
import { DATE_FORMAT } from 'app/config/input.constants';
import { ApplicationConfigService } from 'app/core/config/application-config.service';
import { createRequestOption } from 'app/core/request/request-util';
import { ISysLog, NewSysLog } from '../sys-log.model';

export type PartialUpdateSysLog = Partial<ISysLog> & Pick<ISysLog, 'id'>;

type RestOf<T extends ISysLog | NewSysLog> = Omit<T, 'createTime' | 'updateTime'> & {
  createTime?: string | null;
  updateTime?: string | null;
};

export type RestSysLog = RestOf<ISysLog>;

export type NewRestSysLog = RestOf<NewSysLog>;

export type PartialUpdateRestSysLog = RestOf<PartialUpdateSysLog>;

export type EntityResponseType = HttpResponse<ISysLog>;
export type EntityArrayResponseType = HttpResponse<ISysLog[]>;

@Injectable({ providedIn: 'root' })
export class SysLogService {
  protected resourceUrl = this.applicationConfigService.getEndpointFor('api/sys-logs');

  constructor(protected http: HttpClient, protected applicationConfigService: ApplicationConfigService) {}

  create(sysLog: NewSysLog): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(sysLog);
    return this.http
      .post<RestSysLog>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map(res => this.convertResponseFromServer(res)));
  }

  update(sysLog: ISysLog): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(sysLog);
    return this.http
      .put<RestSysLog>(`${this.resourceUrl}/${this.getSysLogIdentifier(sysLog)}`, copy, { observe: 'response' })
      .pipe(map(res => this.convertResponseFromServer(res)));
  }

  partialUpdate(sysLog: PartialUpdateSysLog): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(sysLog);
    return this.http
      .patch<RestSysLog>(`${this.resourceUrl}/${this.getSysLogIdentifier(sysLog)}`, copy, { observe: 'response' })
      .pipe(map(res => this.convertResponseFromServer(res)));
  }

  find(id: string): Observable<EntityResponseType> {
    return this.http
      .get<RestSysLog>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map(res => this.convertResponseFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<RestSysLog[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map(res => this.convertResponseArrayFromServer(res)));
  }

  delete(id: string): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  getSysLogIdentifier(sysLog: Pick<ISysLog, 'id'>): string {
    return sysLog.id;
  }

  compareSysLog(o1: Pick<ISysLog, 'id'> | null, o2: Pick<ISysLog, 'id'> | null): boolean {
    return o1 && o2 ? this.getSysLogIdentifier(o1) === this.getSysLogIdentifier(o2) : o1 === o2;
  }

  addSysLogToCollectionIfMissing<Type extends Pick<ISysLog, 'id'>>(
    sysLogCollection: Type[],
    ...sysLogsToCheck: (Type | null | undefined)[]
  ): Type[] {
    const sysLogs: Type[] = sysLogsToCheck.filter(isPresent);
    if (sysLogs.length > 0) {
      const sysLogCollectionIdentifiers = sysLogCollection.map(sysLogItem => this.getSysLogIdentifier(sysLogItem)!);
      const sysLogsToAdd = sysLogs.filter(sysLogItem => {
        const sysLogIdentifier = this.getSysLogIdentifier(sysLogItem);
        if (sysLogCollectionIdentifiers.includes(sysLogIdentifier)) {
          return false;
        }
        sysLogCollectionIdentifiers.push(sysLogIdentifier);
        return true;
      });
      return [...sysLogsToAdd, ...sysLogCollection];
    }
    return sysLogCollection;
  }

  protected convertDateFromClient<T extends ISysLog | NewSysLog | PartialUpdateSysLog>(sysLog: T): RestOf<T> {
    return {
      ...sysLog,
      createTime: sysLog.createTime?.format(DATE_FORMAT) ?? null,
      updateTime: sysLog.updateTime?.format(DATE_FORMAT) ?? null,
    };
  }

  protected convertDateFromServer(restSysLog: RestSysLog): ISysLog {
    return {
      ...restSysLog,
      createTime: restSysLog.createTime ? dayjs(restSysLog.createTime) : undefined,
      updateTime: restSysLog.updateTime ? dayjs(restSysLog.updateTime) : undefined,
    };
  }

  protected convertResponseFromServer(res: HttpResponse<RestSysLog>): HttpResponse<ISysLog> {
    return res.clone({
      body: res.body ? this.convertDateFromServer(res.body) : null,
    });
  }

  protected convertResponseArrayFromServer(res: HttpResponse<RestSysLog[]>): HttpResponse<ISysLog[]> {
    return res.clone({
      body: res.body ? res.body.map(item => this.convertDateFromServer(item)) : null,
    });
  }
}
