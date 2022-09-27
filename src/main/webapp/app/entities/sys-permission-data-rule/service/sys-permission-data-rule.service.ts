import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import dayjs from 'dayjs/esm';

import { isPresent } from 'app/core/util/operators';
import { DATE_FORMAT } from 'app/config/input.constants';
import { ApplicationConfigService } from 'app/core/config/application-config.service';
import { createRequestOption } from 'app/core/request/request-util';
import { ISysPermissionDataRule, NewSysPermissionDataRule } from '../sys-permission-data-rule.model';

export type PartialUpdateSysPermissionDataRule = Partial<ISysPermissionDataRule> & Pick<ISysPermissionDataRule, 'id'>;

type RestOf<T extends ISysPermissionDataRule | NewSysPermissionDataRule> = Omit<T, 'createTime' | 'updateTime'> & {
  createTime?: string | null;
  updateTime?: string | null;
};

export type RestSysPermissionDataRule = RestOf<ISysPermissionDataRule>;

export type NewRestSysPermissionDataRule = RestOf<NewSysPermissionDataRule>;

export type PartialUpdateRestSysPermissionDataRule = RestOf<PartialUpdateSysPermissionDataRule>;

export type EntityResponseType = HttpResponse<ISysPermissionDataRule>;
export type EntityArrayResponseType = HttpResponse<ISysPermissionDataRule[]>;

@Injectable({ providedIn: 'root' })
export class SysPermissionDataRuleService {
  protected resourceUrl = this.applicationConfigService.getEndpointFor('api/sys-permission-data-rules');

  constructor(protected http: HttpClient, protected applicationConfigService: ApplicationConfigService) {}

  create(sysPermissionDataRule: NewSysPermissionDataRule): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(sysPermissionDataRule);
    return this.http
      .post<RestSysPermissionDataRule>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map(res => this.convertResponseFromServer(res)));
  }

  update(sysPermissionDataRule: ISysPermissionDataRule): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(sysPermissionDataRule);
    return this.http
      .put<RestSysPermissionDataRule>(`${this.resourceUrl}/${this.getSysPermissionDataRuleIdentifier(sysPermissionDataRule)}`, copy, {
        observe: 'response',
      })
      .pipe(map(res => this.convertResponseFromServer(res)));
  }

  partialUpdate(sysPermissionDataRule: PartialUpdateSysPermissionDataRule): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(sysPermissionDataRule);
    return this.http
      .patch<RestSysPermissionDataRule>(`${this.resourceUrl}/${this.getSysPermissionDataRuleIdentifier(sysPermissionDataRule)}`, copy, {
        observe: 'response',
      })
      .pipe(map(res => this.convertResponseFromServer(res)));
  }

  find(id: string): Observable<EntityResponseType> {
    return this.http
      .get<RestSysPermissionDataRule>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map(res => this.convertResponseFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<RestSysPermissionDataRule[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map(res => this.convertResponseArrayFromServer(res)));
  }

  delete(id: string): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  getSysPermissionDataRuleIdentifier(sysPermissionDataRule: Pick<ISysPermissionDataRule, 'id'>): string {
    return sysPermissionDataRule.id;
  }

  compareSysPermissionDataRule(o1: Pick<ISysPermissionDataRule, 'id'> | null, o2: Pick<ISysPermissionDataRule, 'id'> | null): boolean {
    return o1 && o2 ? this.getSysPermissionDataRuleIdentifier(o1) === this.getSysPermissionDataRuleIdentifier(o2) : o1 === o2;
  }

  addSysPermissionDataRuleToCollectionIfMissing<Type extends Pick<ISysPermissionDataRule, 'id'>>(
    sysPermissionDataRuleCollection: Type[],
    ...sysPermissionDataRulesToCheck: (Type | null | undefined)[]
  ): Type[] {
    const sysPermissionDataRules: Type[] = sysPermissionDataRulesToCheck.filter(isPresent);
    if (sysPermissionDataRules.length > 0) {
      const sysPermissionDataRuleCollectionIdentifiers = sysPermissionDataRuleCollection.map(
        sysPermissionDataRuleItem => this.getSysPermissionDataRuleIdentifier(sysPermissionDataRuleItem)!
      );
      const sysPermissionDataRulesToAdd = sysPermissionDataRules.filter(sysPermissionDataRuleItem => {
        const sysPermissionDataRuleIdentifier = this.getSysPermissionDataRuleIdentifier(sysPermissionDataRuleItem);
        if (sysPermissionDataRuleCollectionIdentifiers.includes(sysPermissionDataRuleIdentifier)) {
          return false;
        }
        sysPermissionDataRuleCollectionIdentifiers.push(sysPermissionDataRuleIdentifier);
        return true;
      });
      return [...sysPermissionDataRulesToAdd, ...sysPermissionDataRuleCollection];
    }
    return sysPermissionDataRuleCollection;
  }

  protected convertDateFromClient<T extends ISysPermissionDataRule | NewSysPermissionDataRule | PartialUpdateSysPermissionDataRule>(
    sysPermissionDataRule: T
  ): RestOf<T> {
    return {
      ...sysPermissionDataRule,
      createTime: sysPermissionDataRule.createTime?.format(DATE_FORMAT) ?? null,
      updateTime: sysPermissionDataRule.updateTime?.format(DATE_FORMAT) ?? null,
    };
  }

  protected convertDateFromServer(restSysPermissionDataRule: RestSysPermissionDataRule): ISysPermissionDataRule {
    return {
      ...restSysPermissionDataRule,
      createTime: restSysPermissionDataRule.createTime ? dayjs(restSysPermissionDataRule.createTime) : undefined,
      updateTime: restSysPermissionDataRule.updateTime ? dayjs(restSysPermissionDataRule.updateTime) : undefined,
    };
  }

  protected convertResponseFromServer(res: HttpResponse<RestSysPermissionDataRule>): HttpResponse<ISysPermissionDataRule> {
    return res.clone({
      body: res.body ? this.convertDateFromServer(res.body) : null,
    });
  }

  protected convertResponseArrayFromServer(res: HttpResponse<RestSysPermissionDataRule[]>): HttpResponse<ISysPermissionDataRule[]> {
    return res.clone({
      body: res.body ? res.body.map(item => this.convertDateFromServer(item)) : null,
    });
  }
}
