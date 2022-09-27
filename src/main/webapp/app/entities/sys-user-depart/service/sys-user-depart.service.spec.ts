import { TestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';

import { ISysUserDepart } from '../sys-user-depart.model';
import { sampleWithRequiredData, sampleWithNewData, sampleWithPartialData, sampleWithFullData } from '../sys-user-depart.test-samples';

import { SysUserDepartService } from './sys-user-depart.service';

const requireRestSample: ISysUserDepart = {
  ...sampleWithRequiredData,
};

describe('SysUserDepart Service', () => {
  let service: SysUserDepartService;
  let httpMock: HttpTestingController;
  let expectedResult: ISysUserDepart | ISysUserDepart[] | boolean | null;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule],
    });
    expectedResult = null;
    service = TestBed.inject(SysUserDepartService);
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

    it('should create a SysUserDepart', () => {
      // eslint-disable-next-line @typescript-eslint/no-unused-vars
      const sysUserDepart = { ...sampleWithNewData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.create(sysUserDepart).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'POST' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should update a SysUserDepart', () => {
      const sysUserDepart = { ...sampleWithRequiredData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.update(sysUserDepart).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'PUT' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should partial update a SysUserDepart', () => {
      const patchObject = { ...sampleWithPartialData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.partialUpdate(patchObject).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'PATCH' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should return a list of SysUserDepart', () => {
      const returnedFromService = { ...requireRestSample };

      const expected = { ...sampleWithRequiredData };

      service.query().subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'GET' });
      req.flush([returnedFromService]);
      httpMock.verify();
      expect(expectedResult).toMatchObject([expected]);
    });

    it('should delete a SysUserDepart', () => {
      const expected = true;

      service.delete('ABC').subscribe(resp => (expectedResult = resp.ok));

      const req = httpMock.expectOne({ method: 'DELETE' });
      req.flush({ status: 200 });
      expect(expectedResult).toBe(expected);
    });

    describe('addSysUserDepartToCollectionIfMissing', () => {
      it('should add a SysUserDepart to an empty array', () => {
        const sysUserDepart: ISysUserDepart = sampleWithRequiredData;
        expectedResult = service.addSysUserDepartToCollectionIfMissing([], sysUserDepart);
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(sysUserDepart);
      });

      it('should not add a SysUserDepart to an array that contains it', () => {
        const sysUserDepart: ISysUserDepart = sampleWithRequiredData;
        const sysUserDepartCollection: ISysUserDepart[] = [
          {
            ...sysUserDepart,
          },
          sampleWithPartialData,
        ];
        expectedResult = service.addSysUserDepartToCollectionIfMissing(sysUserDepartCollection, sysUserDepart);
        expect(expectedResult).toHaveLength(2);
      });

      it("should add a SysUserDepart to an array that doesn't contain it", () => {
        const sysUserDepart: ISysUserDepart = sampleWithRequiredData;
        const sysUserDepartCollection: ISysUserDepart[] = [sampleWithPartialData];
        expectedResult = service.addSysUserDepartToCollectionIfMissing(sysUserDepartCollection, sysUserDepart);
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(sysUserDepart);
      });

      it('should add only unique SysUserDepart to an array', () => {
        const sysUserDepartArray: ISysUserDepart[] = [sampleWithRequiredData, sampleWithPartialData, sampleWithFullData];
        const sysUserDepartCollection: ISysUserDepart[] = [sampleWithRequiredData];
        expectedResult = service.addSysUserDepartToCollectionIfMissing(sysUserDepartCollection, ...sysUserDepartArray);
        expect(expectedResult).toHaveLength(3);
      });

      it('should accept varargs', () => {
        const sysUserDepart: ISysUserDepart = sampleWithRequiredData;
        const sysUserDepart2: ISysUserDepart = sampleWithPartialData;
        expectedResult = service.addSysUserDepartToCollectionIfMissing([], sysUserDepart, sysUserDepart2);
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(sysUserDepart);
        expect(expectedResult).toContain(sysUserDepart2);
      });

      it('should accept null and undefined values', () => {
        const sysUserDepart: ISysUserDepart = sampleWithRequiredData;
        expectedResult = service.addSysUserDepartToCollectionIfMissing([], null, sysUserDepart, undefined);
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(sysUserDepart);
      });

      it('should return initial array if no SysUserDepart is added', () => {
        const sysUserDepartCollection: ISysUserDepart[] = [sampleWithRequiredData];
        expectedResult = service.addSysUserDepartToCollectionIfMissing(sysUserDepartCollection, undefined, null);
        expect(expectedResult).toEqual(sysUserDepartCollection);
      });
    });

    describe('compareSysUserDepart', () => {
      it('Should return true if both entities are null', () => {
        const entity1 = null;
        const entity2 = null;

        const compareResult = service.compareSysUserDepart(entity1, entity2);

        expect(compareResult).toEqual(true);
      });

      it('Should return false if one entity is null', () => {
        const entity1 = { id: 'ABC' };
        const entity2 = null;

        const compareResult1 = service.compareSysUserDepart(entity1, entity2);
        const compareResult2 = service.compareSysUserDepart(entity2, entity1);

        expect(compareResult1).toEqual(false);
        expect(compareResult2).toEqual(false);
      });

      it('Should return false if primaryKey differs', () => {
        const entity1 = { id: 'ABC' };
        const entity2 = { id: 'CBA' };

        const compareResult1 = service.compareSysUserDepart(entity1, entity2);
        const compareResult2 = service.compareSysUserDepart(entity2, entity1);

        expect(compareResult1).toEqual(false);
        expect(compareResult2).toEqual(false);
      });

      it('Should return false if primaryKey matches', () => {
        const entity1 = { id: 'ABC' };
        const entity2 = { id: 'ABC' };

        const compareResult1 = service.compareSysUserDepart(entity1, entity2);
        const compareResult2 = service.compareSysUserDepart(entity2, entity1);

        expect(compareResult1).toEqual(true);
        expect(compareResult2).toEqual(true);
      });
    });
  });

  afterEach(() => {
    httpMock.verify();
  });
});
