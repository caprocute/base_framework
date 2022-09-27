import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import dayjs from 'dayjs/esm';

import { isPresent } from 'app/core/util/operators';
import { DATE_FORMAT } from 'app/config/input.constants';
import { ApplicationConfigService } from 'app/core/config/application-config.service';
import { createRequestOption } from 'app/core/request/request-util';
import { ISysDepartRole, NewSysDepartRole } from '../sys-depart-role.model';

export type PartialUpdateSysDepartRole = Partial<ISysDepartRole> & Pick<ISysDepartRole, 'id'>;

type RestOf<T extends ISysDepartRole | NewSysDepartRole> = Omit<T, 'createTime' | 'updateTime'> & {
  createTime?: string | null;
  updateTime?: string | null;
};

export type RestSysDepartRole = RestOf<ISysDepartRole>;

export type NewRestSysDepartRole = RestOf<NewSysDepartRole>;

export type PartialUpdateRestSysDepartRole = RestOf<PartialUpdateSysDepartRole>;

export type EntityResponseType = HttpResponse<ISysDepartRole>;
export type EntityArrayResponseType = HttpResponse<ISysDepartRole[]>;

@Injectable({ providedIn: 'root' })
export class SysDepartRoleService {
  protected resourceUrl = this.applicationConfigService.getEndpointFor('api/sys-depart-roles');

  constructor(protected http: HttpClient, protected applicationConfigService: ApplicationConfigService) {}

  create(sysDepartRole: NewSysDepartRole): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(sysDepartRole);
    return this.http
      .post<RestSysDepartRole>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map(res => this.convertResponseFromServer(res)));
  }

  update(sysDepartRole: ISysDepartRole): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(sysDepartRole);
    return this.http
      .put<RestSysDepartRole>(`${this.resourceUrl}/${this.getSysDepartRoleIdentifier(sysDepartRole)}`, copy, { observe: 'response' })
      .pipe(map(res => this.convertResponseFromServer(res)));
  }

  partialUpdate(sysDepartRole: PartialUpdateSysDepartRole): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(sysDepartRole);
    return this.http
      .patch<RestSysDepartRole>(`${this.resourceUrl}/${this.getSysDepartRoleIdentifier(sysDepartRole)}`, copy, { observe: 'response' })
      .pipe(map(res => this.convertResponseFromServer(res)));
  }

  find(id: string): Observable<EntityResponseType> {
    return this.http
      .get<RestSysDepartRole>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map(res => this.convertResponseFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<RestSysDepartRole[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map(res => this.convertResponseArrayFromServer(res)));
  }

  delete(id: string): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  getSysDepartRoleIdentifier(sysDepartRole: Pick<ISysDepartRole, 'id'>): string {
    return sysDepartRole.id;
  }

  compareSysDepartRole(o1: Pick<ISysDepartRole, 'id'> | null, o2: Pick<ISysDepartRole, 'id'> | null): boolean {
    return o1 && o2 ? this.getSysDepartRoleIdentifier(o1) === this.getSysDepartRoleIdentifier(o2) : o1 === o2;
  }

  addSysDepartRoleToCollectionIfMissing<Type extends Pick<ISysDepartRole, 'id'>>(
    sysDepartRoleCollection: Type[],
    ...sysDepartRolesToCheck: (Type | null | undefined)[]
  ): Type[] {
    const sysDepartRoles: Type[] = sysDepartRolesToCheck.filter(isPresent);
    if (sysDepartRoles.length > 0) {
      const sysDepartRoleCollectionIdentifiers = sysDepartRoleCollection.map(
        sysDepartRoleItem => this.getSysDepartRoleIdentifier(sysDepartRoleItem)!
      );
      const sysDepartRolesToAdd = sysDepartRoles.filter(sysDepartRoleItem => {
        const sysDepartRoleIdentifier = this.getSysDepartRoleIdentifier(sysDepartRoleItem);
        if (sysDepartRoleCollectionIdentifiers.includes(sysDepartRoleIdentifier)) {
          return false;
        }
        sysDepartRoleCollectionIdentifiers.push(sysDepartRoleIdentifier);
        return true;
      });
      return [...sysDepartRolesToAdd, ...sysDepartRoleCollection];
    }
    return sysDepartRoleCollection;
  }

  protected convertDateFromClient<T extends ISysDepartRole | NewSysDepartRole | PartialUpdateSysDepartRole>(sysDepartRole: T): RestOf<T> {
    return {
      ...sysDepartRole,
      createTime: sysDepartRole.createTime?.format(DATE_FORMAT) ?? null,
      updateTime: sysDepartRole.updateTime?.format(DATE_FORMAT) ?? null,
    };
  }

  protected convertDateFromServer(restSysDepartRole: RestSysDepartRole): ISysDepartRole {
    return {
      ...restSysDepartRole,
      createTime: restSysDepartRole.createTime ? dayjs(restSysDepartRole.createTime) : undefined,
      updateTime: restSysDepartRole.updateTime ? dayjs(restSysDepartRole.updateTime) : undefined,
    };
  }

  protected convertResponseFromServer(res: HttpResponse<RestSysDepartRole>): HttpResponse<ISysDepartRole> {
    return res.clone({
      body: res.body ? this.convertDateFromServer(res.body) : null,
    });
  }

  protected convertResponseArrayFromServer(res: HttpResponse<RestSysDepartRole[]>): HttpResponse<ISysDepartRole[]> {
    return res.clone({
      body: res.body ? res.body.map(item => this.convertDateFromServer(item)) : null,
    });
  }
}
