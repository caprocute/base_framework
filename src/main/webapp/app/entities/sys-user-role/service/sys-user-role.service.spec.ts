import { TestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';

import { ISysUserRole } from '../sys-user-role.model';
import { sampleWithRequiredData, sampleWithNewData, sampleWithPartialData, sampleWithFullData } from '../sys-user-role.test-samples';

import { SysUserRoleService } from './sys-user-role.service';

const requireRestSample: ISysUserRole = {
  ...sampleWithRequiredData,
};

describe('SysUserRole Service', () => {
  let service: SysUserRoleService;
  let httpMock: HttpTestingController;
  let expectedResult: ISysUserRole | ISysUserRole[] | boolean | null;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule],
    });
    expectedResult = null;
    service = TestBed.inject(SysUserRoleService);
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

    it('should create a SysUserRole', () => {
      // eslint-disable-next-line @typescript-eslint/no-unused-vars
      const sysUserRole = { ...sampleWithNewData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.create(sysUserRole).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'POST' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should update a SysUserRole', () => {
      const sysUserRole = { ...sampleWithRequiredData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.update(sysUserRole).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'PUT' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should partial update a SysUserRole', () => {
      const patchObject = { ...sampleWithPartialData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.partialUpdate(patchObject).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'PATCH' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should return a list of SysUserRole', () => {
      const returnedFromService = { ...requireRestSample };

      const expected = { ...sampleWithRequiredData };

      service.query().subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'GET' });
      req.flush([returnedFromService]);
      httpMock.verify();
      expect(expectedResult).toMatchObject([expected]);
    });

    it('should delete a SysUserRole', () => {
      const expected = true;

      service.delete('ABC').subscribe(resp => (expectedResult = resp.ok));

      const req = httpMock.expectOne({ method: 'DELETE' });
      req.flush({ status: 200 });
      expect(expectedResult).toBe(expected);
    });

    describe('addSysUserRoleToCollectionIfMissing', () => {
      it('should add a SysUserRole to an empty array', () => {
        const sysUserRole: ISysUserRole = sampleWithRequiredData;
        expectedResult = service.addSysUserRoleToCollectionIfMissing([], sysUserRole);
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(sysUserRole);
      });

      it('should not add a SysUserRole to an array that contains it', () => {
        const sysUserRole: ISysUserRole = sampleWithRequiredData;
        const sysUserRoleCollection: ISysUserRole[] = [
          {
            ...sysUserRole,
          },
          sampleWithPartialData,
        ];
        expectedResult = service.addSysUserRoleToCollectionIfMissing(sysUserRoleCollection, sysUserRole);
        expect(expectedResult).toHaveLength(2);
      });

      it("should add a SysUserRole to an array that doesn't contain it", () => {
        const sysUserRole: ISysUserRole = sampleWithRequiredData;
        const sysUserRoleCollection: ISysUserRole[] = [sampleWithPartialData];
        expectedResult = service.addSysUserRoleToCollectionIfMissing(sysUserRoleCollection, sysUserRole);
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(sysUserRole);
      });

      it('should add only unique SysUserRole to an array', () => {
        const sysUserRoleArray: ISysUserRole[] = [sampleWithRequiredData, sampleWithPartialData, sampleWithFullData];
        const sysUserRoleCollection: ISysUserRole[] = [sampleWithRequiredData];
        expectedResult = service.addSysUserRoleToCollectionIfMissing(sysUserRoleCollection, ...sysUserRoleArray);
        expect(expectedResult).toHaveLength(3);
      });

      it('should accept varargs', () => {
        const sysUserRole: ISysUserRole = sampleWithRequiredData;
        const sysUserRole2: ISysUserRole = sampleWithPartialData;
        expectedResult = service.addSysUserRoleToCollectionIfMissing([], sysUserRole, sysUserRole2);
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(sysUserRole);
        expect(expectedResult).toContain(sysUserRole2);
      });

      it('should accept null and undefined values', () => {
        const sysUserRole: ISysUserRole = sampleWithRequiredData;
        expectedResult = service.addSysUserRoleToCollectionIfMissing([], null, sysUserRole, undefined);
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(sysUserRole);
      });

      it('should return initial array if no SysUserRole is added', () => {
        const sysUserRoleCollection: ISysUserRole[] = [sampleWithRequiredData];
        expectedResult = service.addSysUserRoleToCollectionIfMissing(sysUserRoleCollection, undefined, null);
        expect(expectedResult).toEqual(sysUserRoleCollection);
      });
    });

    describe('compareSysUserRole', () => {
      it('Should return true if both entities are null', () => {
        const entity1 = null;
        const entity2 = null;

        const compareResult = service.compareSysUserRole(entity1, entity2);

        expect(compareResult).toEqual(true);
      });

      it('Should return false if one entity is null', () => {
        const entity1 = { id: 'ABC' };
        const entity2 = null;

        const compareResult1 = service.compareSysUserRole(entity1, entity2);
        const compareResult2 = service.compareSysUserRole(entity2, entity1);

        expect(compareResult1).toEqual(false);
        expect(compareResult2).toEqual(false);
      });

      it('Should return false if primaryKey differs', () => {
        const entity1 = { id: 'ABC' };
        const entity2 = { id: 'CBA' };

        const compareResult1 = service.compareSysUserRole(entity1, entity2);
        const compareResult2 = service.compareSysUserRole(entity2, entity1);

        expect(compareResult1).toEqual(false);
        expect(compareResult2).toEqual(false);
      });

      it('Should return false if primaryKey matches', () => {
        const entity1 = { id: 'ABC' };
        const entity2 = { id: 'ABC' };

        const compareResult1 = service.compareSysUserRole(entity1, entity2);
        const compareResult2 = service.compareSysUserRole(entity2, entity1);

        expect(compareResult1).toEqual(true);
        expect(compareResult2).toEqual(true);
      });
    });
  });

  afterEach(() => {
    httpMock.verify();
  });
});
