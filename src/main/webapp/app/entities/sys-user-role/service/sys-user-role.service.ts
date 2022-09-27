import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { isPresent } from 'app/core/util/operators';
import { ApplicationConfigService } from 'app/core/config/application-config.service';
import { createRequestOption } from 'app/core/request/request-util';
import { ISysUserRole, NewSysUserRole } from '../sys-user-role.model';

export type PartialUpdateSysUserRole = Partial<ISysUserRole> & Pick<ISysUserRole, 'id'>;

export type EntityResponseType = HttpResponse<ISysUserRole>;
export type EntityArrayResponseType = HttpResponse<ISysUserRole[]>;

@Injectable({ providedIn: 'root' })
export class SysUserRoleService {
  protected resourceUrl = this.applicationConfigService.getEndpointFor('api/sys-user-roles');

  constructor(protected http: HttpClient, protected applicationConfigService: ApplicationConfigService) {}

  create(sysUserRole: NewSysUserRole): Observable<EntityResponseType> {
    return this.http.post<ISysUserRole>(this.resourceUrl, sysUserRole, { observe: 'response' });
  }

  update(sysUserRole: ISysUserRole): Observable<EntityResponseType> {
    return this.http.put<ISysUserRole>(`${this.resourceUrl}/${this.getSysUserRoleIdentifier(sysUserRole)}`, sysUserRole, {
      observe: 'response',
    });
  }

  partialUpdate(sysUserRole: PartialUpdateSysUserRole): Observable<EntityResponseType> {
    return this.http.patch<ISysUserRole>(`${this.resourceUrl}/${this.getSysUserRoleIdentifier(sysUserRole)}`, sysUserRole, {
      observe: 'response',
    });
  }

  find(id: string): Observable<EntityResponseType> {
    return this.http.get<ISysUserRole>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<ISysUserRole[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: string): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  getSysUserRoleIdentifier(sysUserRole: Pick<ISysUserRole, 'id'>): string {
    return sysUserRole.id;
  }

  compareSysUserRole(o1: Pick<ISysUserRole, 'id'> | null, o2: Pick<ISysUserRole, 'id'> | null): boolean {
    return o1 && o2 ? this.getSysUserRoleIdentifier(o1) === this.getSysUserRoleIdentifier(o2) : o1 === o2;
  }

  addSysUserRoleToCollectionIfMissing<Type extends Pick<ISysUserRole, 'id'>>(
    sysUserRoleCollection: Type[],
    ...sysUserRolesToCheck: (Type | null | undefined)[]
  ): Type[] {
    const sysUserRoles: Type[] = sysUserRolesToCheck.filter(isPresent);
    if (sysUserRoles.length > 0) {
      const sysUserRoleCollectionIdentifiers = sysUserRoleCollection.map(
        sysUserRoleItem => this.getSysUserRoleIdentifier(sysUserRoleItem)!
      );
      const sysUserRolesToAdd = sysUserRoles.filter(sysUserRoleItem => {
        const sysUserRoleIdentifier = this.getSysUserRoleIdentifier(sysUserRoleItem);
        if (sysUserRoleCollectionIdentifiers.includes(sysUserRoleIdentifier)) {
          return false;
        }
        sysUserRoleCollectionIdentifiers.push(sysUserRoleIdentifier);
        return true;
      });
      return [...sysUserRolesToAdd, ...sysUserRoleCollection];
    }
    return sysUserRoleCollection;
  }
}
