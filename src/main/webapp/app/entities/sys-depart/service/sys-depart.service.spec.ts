import { TestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';

import { DATE_FORMAT } from 'app/config/input.constants';
import { ISysDepart } from '../sys-depart.model';
import { sampleWithRequiredData, sampleWithNewData, sampleWithPartialData, sampleWithFullData } from '../sys-depart.test-samples';

import { SysDepartService, RestSysDepart } from './sys-depart.service';

const requireRestSample: RestSysDepart = {
  ...sampleWithRequiredData,
  createTime: sampleWithRequiredData.createTime?.format(DATE_FORMAT),
  updateTime: sampleWithRequiredData.updateTime?.format(DATE_FORMAT),
};

describe('SysDepart Service', () => {
  let service: SysDepartService;
  let httpMock: HttpTestingController;
  let expectedResult: ISysDepart | ISysDepart[] | boolean | null;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule],
    });
    expectedResult = null;
    service = TestBed.inject(SysDepartService);
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

    it('should create a SysDepart', () => {
      // eslint-disable-next-line @typescript-eslint/no-unused-vars
      const sysDepart = { ...sampleWithNewData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.create(sysDepart).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'POST' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should update a SysDepart', () => {
      const sysDepart = { ...sampleWithRequiredData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.update(sysDepart).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'PUT' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should partial update a SysDepart', () => {
      const patchObject = { ...sampleWithPartialData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.partialUpdate(patchObject).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'PATCH' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should return a list of SysDepart', () => {
      const returnedFromService = { ...requireRestSample };

      const expected = { ...sampleWithRequiredData };

      service.query().subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'GET' });
      req.flush([returnedFromService]);
      httpMock.verify();
      expect(expectedResult).toMatchObject([expected]);
    });

    it('should delete a SysDepart', () => {
      const expected = true;

      service.delete('ABC').subscribe(resp => (expectedResult = resp.ok));

      const req = httpMock.expectOne({ method: 'DELETE' });
      req.flush({ status: 200 });
      expect(expectedResult).toBe(expected);
    });

    describe('addSysDepartToCollectionIfMissing', () => {
      it('should add a SysDepart to an empty array', () => {
        const sysDepart: ISysDepart = sampleWithRequiredData;
        expectedResult = service.addSysDepartToCollectionIfMissing([], sysDepart);
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(sysDepart);
      });

      it('should not add a SysDepart to an array that contains it', () => {
        const sysDepart: ISysDepart = sampleWithRequiredData;
        const sysDepartCollection: ISysDepart[] = [
          {
            ...sysDepart,
          },
          sampleWithPartialData,
        ];
        expectedResult = service.addSysDepartToCollectionIfMissing(sysDepartCollection, sysDepart);
        expect(expectedResult).toHaveLength(2);
      });

      it("should add a SysDepart to an array that doesn't contain it", () => {
        const sysDepart: ISysDepart = sampleWithRequiredData;
        const sysDepartCollection: ISysDepart[] = [sampleWithPartialData];
        expectedResult = service.addSysDepartToCollectionIfMissing(sysDepartCollection, sysDepart);
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(sysDepart);
      });

      it('should add only unique SysDepart to an array', () => {
        const sysDepartArray: ISysDepart[] = [sampleWithRequiredData, sampleWithPartialData, sampleWithFullData];
        const sysDepartCollection: ISysDepart[] = [sampleWithRequiredData];
        expectedResult = service.addSysDepartToCollectionIfMissing(sysDepartCollection, ...sysDepartArray);
        expect(expectedResult).toHaveLength(3);
      });

      it('should accept varargs', () => {
        const sysDepart: ISysDepart = sampleWithRequiredData;
        const sysDepart2: ISysDepart = sampleWithPartialData;
        expectedResult = service.addSysDepartToCollectionIfMissing([], sysDepart, sysDepart2);
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(sysDepart);
        expect(expectedResult).toContain(sysDepart2);
      });

      it('should accept null and undefined values', () => {
        const sysDepart: ISysDepart = sampleWithRequiredData;
        expectedResult = service.addSysDepartToCollectionIfMissing([], null, sysDepart, undefined);
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(sysDepart);
      });

      it('should return initial array if no SysDepart is added', () => {
        const sysDepartCollection: ISysDepart[] = [sampleWithRequiredData];
        expectedResult = service.addSysDepartToCollectionIfMissing(sysDepartCollection, undefined, null);
        expect(expectedResult).toEqual(sysDepartCollection);
      });
    });

    describe('compareSysDepart', () => {
      it('Should return true if both entities are null', () => {
        const entity1 = null;
        const entity2 = null;

        const compareResult = service.compareSysDepart(entity1, entity2);

        expect(compareResult).toEqual(true);
      });

      it('Should return false if one entity is null', () => {
        const entity1 = { id: 'ABC' };
        const entity2 = null;

        const compareResult1 = service.compareSysDepart(entity1, entity2);
        const compareResult2 = service.compareSysDepart(entity2, entity1);

        expect(compareResult1).toEqual(false);
        expect(compareResult2).toEqual(false);
      });

      it('Should return false if primaryKey differs', () => {
        const entity1 = { id: 'ABC' };
        const entity2 = { id: 'CBA' };

        const compareResult1 = service.compareSysDepart(entity1, entity2);
        const compareResult2 = service.compareSysDepart(entity2, entity1);

        expect(compareResult1).toEqual(false);
        expect(compareResult2).toEqual(false);
      });

      it('Should return false if primaryKey matches', () => {
        const entity1 = { id: 'ABC' };
        const entity2 = { id: 'ABC' };

        const compareResult1 = service.compareSysDepart(entity1, entity2);
        const compareResult2 = service.compareSysDepart(entity2, entity1);

        expect(compareResult1).toEqual(true);
        expect(compareResult2).toEqual(true);
      });
    });
  });

  afterEach(() => {
    httpMock.verify();
  });
});
