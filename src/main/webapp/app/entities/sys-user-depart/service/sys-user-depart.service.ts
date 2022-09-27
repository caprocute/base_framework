import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { isPresent } from 'app/core/util/operators';
import { ApplicationConfigService } from 'app/core/config/application-config.service';
import { createRequestOption } from 'app/core/request/request-util';
import { ISysUserDepart, NewSysUserDepart } from '../sys-user-depart.model';

export type PartialUpdateSysUserDepart = Partial<ISysUserDepart> & Pick<ISysUserDepart, 'id'>;

export type EntityResponseType = HttpResponse<ISysUserDepart>;
export type EntityArrayResponseType = HttpResponse<ISysUserDepart[]>;

@Injectable({ providedIn: 'root' })
export class SysUserDepartService {
  protected resourceUrl = this.applicationConfigService.getEndpointFor('api/sys-user-departs');

  constructor(protected http: HttpClient, protected applicationConfigService: ApplicationConfigService) {}

  create(sysUserDepart: NewSysUserDepart): Observable<EntityResponseType> {
    return this.http.post<ISysUserDepart>(this.resourceUrl, sysUserDepart, { observe: 'response' });
  }

  update(sysUserDepart: ISysUserDepart): Observable<EntityResponseType> {
    return this.http.put<ISysUserDepart>(`${this.resourceUrl}/${this.getSysUserDepartIdentifier(sysUserDepart)}`, sysUserDepart, {
      observe: 'response',
    });
  }

  partialUpdate(sysUserDepart: PartialUpdateSysUserDepart): Observable<EntityResponseType> {
    return this.http.patch<ISysUserDepart>(`${this.resourceUrl}/${this.getSysUserDepartIdentifier(sysUserDepart)}`, sysUserDepart, {
      observe: 'response',
    });
  }

  find(id: string): Observable<EntityResponseType> {
    return this.http.get<ISysUserDepart>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<ISysUserDepart[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: string): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  getSysUserDepartIdentifier(sysUserDepart: Pick<ISysUserDepart, 'id'>): string {
    return sysUserDepart.id;
  }

  compareSysUserDepart(o1: Pick<ISysUserDepart, 'id'> | null, o2: Pick<ISysUserDepart, 'id'> | null): boolean {
    return o1 && o2 ? this.getSysUserDepartIdentifier(o1) === this.getSysUserDepartIdentifier(o2) : o1 === o2;
  }

  addSysUserDepartToCollectionIfMissing<Type extends Pick<ISysUserDepart, 'id'>>(
    sysUserDepartCollection: Type[],
    ...sysUserDepartsToCheck: (Type | null | undefined)[]
  ): Type[] {
    const sysUserDeparts: Type[] = sysUserDepartsToCheck.filter(isPresent);
    if (sysUserDeparts.length > 0) {
      const sysUserDepartCollectionIdentifiers = sysUserDepartCollection.map(
        sysUserDepartItem => this.getSysUserDepartIdentifier(sysUserDepartItem)!
      );
      const sysUserDepartsToAdd = sysUserDeparts.filter(sysUserDepartItem => {
        const sysUserDepartIdentifier = this.getSysUserDepartIdentifier(sysUserDepartItem);
        if (sysUserDepartCollectionIdentifiers.includes(sysUserDepartIdentifier)) {
          return false;
        }
        sysUserDepartCollectionIdentifiers.push(sysUserDepartIdentifier);
        return true;
      });
      return [...sysUserDepartsToAdd, ...sysUserDepartCollection];
    }
    return sysUserDepartCollection;
  }
}
