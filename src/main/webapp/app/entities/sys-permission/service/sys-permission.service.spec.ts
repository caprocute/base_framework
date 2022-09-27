import { TestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';

import { DATE_FORMAT } from 'app/config/input.constants';
import { ISysPermission } from '../sys-permission.model';
import { sampleWithRequiredData, sampleWithNewData, sampleWithPartialData, sampleWithFullData } from '../sys-permission.test-samples';

import { SysPermissionService, RestSysPermission } from './sys-permission.service';

const requireRestSample: RestSysPermission = {
  ...sampleWithRequiredData,
  createTime: sampleWithRequiredData.createTime?.format(DATE_FORMAT),
  updateTime: sampleWithRequiredData.updateTime?.format(DATE_FORMAT),
};

describe('SysPermission Service', () => {
  let service: SysPermissionService;
  let httpMock: HttpTestingController;
  let expectedResult: ISysPermission | ISysPermission[] | boolean | null;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule],
    });
    expectedResult = null;
    service = TestBed.inject(SysPermissionService);
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

    it('should create a SysPermission', () => {
      // eslint-disable-next-line @typescript-eslint/no-unused-vars
      const sysPermission = { ...sampleWithNewData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.create(sysPermission).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'POST' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should update a SysPermission', () => {
      const sysPermission = { ...sampleWithRequiredData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.update(sysPermission).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'PUT' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should partial update a SysPermission', () => {
      const patchObject = { ...sampleWithPartialData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.partialUpdate(patchObject).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'PATCH' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should return a list of SysPermission', () => {
      const returnedFromService = { ...requireRestSample };

      const expected = { ...sampleWithRequiredData };

      service.query().subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'GET' });
      req.flush([returnedFromService]);
      httpMock.verify();
      expect(expectedResult).toMatchObject([expected]);
    });

    it('should delete a SysPermission', () => {
      const expected = true;

      service.delete('ABC').subscribe(resp => (expectedResult = resp.ok));

      const req = httpMock.expectOne({ method: 'DELETE' });
      req.flush({ status: 200 });
      expect(expectedResult).toBe(expected);
    });

    describe('addSysPermissionToCollectionIfMissing', () => {
      it('should add a SysPermission to an empty array', () => {
        const sysPermission: ISysPermission = sampleWithRequiredData;
        expectedResult = service.addSysPermissionToCollectionIfMissing([], sysPermission);
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(sysPermission);
      });

      it('should not add a SysPermission to an array that contains it', () => {
        const sysPermission: ISysPermission = sampleWithRequiredData;
        const sysPermissionCollection: ISysPermission[] = [
          {
            ...sysPermission,
          },
          sampleWithPartialData,
        ];
        expectedResult = service.addSysPermissionToCollectionIfMissing(sysPermissionCollection, sysPermission);
        expect(expectedResult).toHaveLength(2);
      });

      it("should add a SysPermission to an array that doesn't contain it", () => {
        const sysPermission: ISysPermission = sampleWithRequiredData;
        const sysPermissionCollection: ISysPermission[] = [sampleWithPartialData];
        expectedResult = service.addSysPermissionToCollectionIfMissing(sysPermissionCollection, sysPermission);
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(sysPermission);
      });

      it('should add only unique SysPermission to an array', () => {
        const sysPermissionArray: ISysPermission[] = [sampleWithRequiredData, sampleWithPartialData, sampleWithFullData];
        const sysPermissionCollection: ISysPermission[] = [sampleWithRequiredData];
        expectedResult = service.addSysPermissionToCollectionIfMissing(sysPermissionCollection, ...sysPermissionArray);
        expect(expectedResult).toHaveLength(3);
      });

      it('should accept varargs', () => {
        const sysPermission: ISysPermission = sampleWithRequiredData;
        const sysPermission2: ISysPermission = sampleWithPartialData;
        expectedResult = service.addSysPermissionToCollectionIfMissing([], sysPermission, sysPermission2);
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(sysPermission);
        expect(expectedResult).toContain(sysPermission2);
      });

      it('should accept null and undefined values', () => {
        const sysPermission: ISysPermission = sampleWithRequiredData;
        expectedResult = service.addSysPermissionToCollectionIfMissing([], null, sysPermission, undefined);
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(sysPermission);
      });

      it('should return initial array if no SysPermission is added', () => {
        const sysPermissionCollection: ISysPermission[] = [sampleWithRequiredData];
        expectedResult = service.addSysPermissionToCollectionIfMissing(sysPermissionCollection, undefined, null);
        expect(expectedResult).toEqual(sysPermissionCollection);
      });
    });

    describe('compareSysPermission', () => {
      it('Should return true if both entities are null', () => {
        const entity1 = null;
        const entity2 = null;

        const compareResult = service.compareSysPermission(entity1, entity2);

        expect(compareResult).toEqual(true);
      });

      it('Should return false if one entity is null', () => {
        const entity1 = { id: 'ABC' };
        const entity2 = null;

        const compareResult1 = service.compareSysPermission(entity1, entity2);
        const compareResult2 = service.compareSysPermission(entity2, entity1);

        expect(compareResult1).toEqual(false);
        expect(compareResult2).toEqual(false);
      });

      it('Should return false if primaryKey differs', () => {
        const entity1 = { id: 'ABC' };
        const entity2 = { id: 'CBA' };

        const compareResult1 = service.compareSysPermission(entity1, entity2);
        const compareResult2 = service.compareSysPermission(entity2, entity1);

        expect(compareResult1).toEqual(false);
        expect(compareResult2).toEqual(false);
      });

      it('Should return false if primaryKey matches', () => {
        const entity1 = { id: 'ABC' };
        const entity2 = { id: 'ABC' };

        const compareResult1 = service.compareSysPermission(entity1, entity2);
        const compareResult2 = service.compareSysPermission(entity2, entity1);

        expect(compareResult1).toEqual(true);
        expect(compareResult2).toEqual(true);
      });
    });
  });

  afterEach(() => {
    httpMock.verify();
  });
});
