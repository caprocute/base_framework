import { TestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';

import { DATE_FORMAT } from 'app/config/input.constants';
import { ISysRolePermission } from '../sys-role-permission.model';
import { sampleWithRequiredData, sampleWithNewData, sampleWithPartialData, sampleWithFullData } from '../sys-role-permission.test-samples';

import { SysRolePermissionService, RestSysRolePermission } from './sys-role-permission.service';

const requireRestSample: RestSysRolePermission = {
  ...sampleWithRequiredData,
  operateDate: sampleWithRequiredData.operateDate?.format(DATE_FORMAT),
};

describe('SysRolePermission Service', () => {
  let service: SysRolePermissionService;
  let httpMock: HttpTestingController;
  let expectedResult: ISysRolePermission | ISysRolePermission[] | boolean | null;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule],
    });
    expectedResult = null;
    service = TestBed.inject(SysRolePermissionService);
    httpMock = TestBed.inject(HttpTestingController);
  });

  describe('Service methods', () => {
    it('should find an element', () => {
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.find('ABC').subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'GET' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should create a SysRolePermission', () => {
      // eslint-disable-next-line @typescript-eslint/no-unused-vars
      const sysRolePermission = { ...sampleWithNewData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.create(sysRolePermission).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'POST' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should update a SysRolePermission', () => {
      const sysRolePermission = { ...sampleWithRequiredData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.update(sysRolePermission).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'PUT' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should partial update a SysRolePermission', () => {
      const patchObject = { ...sampleWithPartialData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.partialUpdate(patchObject).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'PATCH' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should return a list of SysRolePermission', () => {
      const returnedFromService = { ...requireRestSample };

      const expected = { ...sampleWithRequiredData };

      service.query().subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'GET' });
      req.flush([returnedFromService]);
      httpMock.verify();
      expect(expectedResult).toMatchObject([expected]);
    });

    it('should delete a SysRolePermission', () => {
      const expected = true;

      service.delete('ABC').subscribe(resp => (expectedResult = resp.ok));

      const req = httpMock.expectOne({ method: 'DELETE' });
      req.flush({ status: 200 });
      expect(expectedResult).toBe(expected);
    });

    describe('addSysRolePermissionToCollectionIfMissing', () => {
      it('should add a SysRolePermission to an empty array', () => {
        const sysRolePermission: ISysRolePermission = sampleWithRequiredData;
        expectedResult = service.addSysRolePermissionToCollectionIfMissing([], sysRolePermission);
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(sysRolePermission);
      });

      it('should not add a SysRolePermission to an array that contains it', () => {
        const sysRolePermission: ISysRolePermission = sampleWithRequiredData;
        const sysRolePermissionCollection: ISysRolePermission[] = [
          {
            ...sysRolePermission,
          },
          sampleWithPartialData,
        ];
        expectedResult = service.addSysRolePermissionToCollectionIfMissing(sysRolePermissionCollection, sysRolePermission);
        expect(expectedResult).toHaveLength(2);
      });

      it("should add a SysRolePermission to an array that doesn't contain it", () => {
        const sysRolePermission: ISysRolePermission = sampleWithRequiredData;
        const sysRolePermissionCollection: ISysRolePermission[] = [sampleWithPartialData];
        expectedResult = service.addSysRolePermissionToCollectionIfMissing(sysRolePermissionCollection, sysRolePermission);
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(sysRolePermission);
      });

      it('should add only unique SysRolePermission to an array', () => {
        const sysRolePermissionArray: ISysRolePermission[] = [sampleWithRequiredData, sampleWithPartialData, sampleWithFullData];
        const sysRolePermissionCollection: ISysRolePermission[] = [sampleWithRequiredData];
        expectedResult = service.addSysRolePermissionToCollectionIfMissing(sysRolePermissionCollection, ...sysRolePermissionArray);
        expect(expectedResult).toHaveLength(3);
      });

      it('should accept varargs', () => {
        const sysRolePermission: ISysRolePermission = sampleWithRequiredData;
        const sysRolePermission2: ISysRolePermission = sampleWithPartialData;
        expectedResult = service.addSysRolePermissionToCollectionIfMissing([], sysRolePermission, sysRolePermission2);
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(sysRolePermission);
        expect(expectedResult).toContain(sysRolePermission2);
      });

      it('should accept null and undefined values', () => {
        const sysRolePermission: ISysRolePermission = sampleWithRequiredData;
        expectedResult = service.addSysRolePermissionToCollectionIfMissing([], null, sysRolePermission, undefined);
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(sysRolePermission);
      });

      it('should return initial array if no SysRolePermission is added', () => {
        const sysRolePermissionCollection: ISysRolePermission[] = [sampleWithRequiredData];
        expectedResult = service.addSysRolePermissionToCollectionIfMissing(sysRolePermissionCollection, undefined, null);
        expect(expectedResult).toEqual(sysRolePermissionCollection);
      });
    });

    describe('compareSysRolePermission', () => {
      it('Should return true if both entities are null', () => {
        const entity1 = null;
        const entity2 = null;

        const compareResult = service.compareSysRolePermission(entity1, entity2);

        expect(compareResult).toEqual(true);
      });

      it('Should return false if one entity is null', () => {
        const entity1 = { id: 'ABC' };
        const entity2 = null;

        const compareResult1 = service.compareSysRolePermission(entity1, entity2);
        const compareResult2 = service.compareSysRolePermission(entity2, entity1);

        expect(compareResult1).toEqual(false);
        expect(compareResult2).toEqual(false);
      });

      it('Should return false if primaryKey differs', () => {
        const entity1 = { id: 'ABC' };
        const entity2 = { id: 'CBA' };

        const compareResult1 = service.compareSysRolePermission(entity1, entity2);
        const compareResult2 = service.compareSysRolePermission(entity2, entity1);

        expect(compareResult1).toEqual(false);
        expect(compareResult2).toEqual(false);
      });

      it('Should return false if primaryKey matches', () => {
        const entity1 = { id: 'ABC' };
        const entity2 = { id: 'ABC' };

        const compareResult1 = service.compareSysRolePermission(entity1, entity2);
        const compareResult2 = service.compareSysRolePermission(entity2, entity1);

        expect(compareResult1).toEqual(true);
        expect(compareResult2).toEqual(true);
      });
    });
  });

  afterEach(() => {
    httpMock.verify();
  });
});
