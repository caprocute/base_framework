import { TestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';

import { DATE_FORMAT } from 'app/config/input.constants';
import { ISysTenant } from '../sys-tenant.model';
import { sampleWithRequiredData, sampleWithNewData, sampleWithPartialData, sampleWithFullData } from '../sys-tenant.test-samples';

import { SysTenantService, RestSysTenant } from './sys-tenant.service';

const requireRestSample: RestSysTenant = {
  ...sampleWithRequiredData,
  createTime: sampleWithRequiredData.createTime?.format(DATE_FORMAT),
  updateTime: sampleWithRequiredData.updateTime?.format(DATE_FORMAT),
};

describe('SysTenant Service', () => {
  let service: SysTenantService;
  let httpMock: HttpTestingController;
  let expectedResult: ISysTenant | ISysTenant[] | boolean | null;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule],
    });
    expectedResult = null;
    service = TestBed.inject(SysTenantService);
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

    it('should create a SysTenant', () => {
      // eslint-disable-next-line @typescript-eslint/no-unused-vars
      const sysTenant = { ...sampleWithNewData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.create(sysTenant).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'POST' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should update a SysTenant', () => {
      const sysTenant = { ...sampleWithRequiredData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.update(sysTenant).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'PUT' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should partial update a SysTenant', () => {
      const patchObject = { ...sampleWithPartialData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.partialUpdate(patchObject).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'PATCH' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should return a list of SysTenant', () => {
      const returnedFromService = { ...requireRestSample };

      const expected = { ...sampleWithRequiredData };

      service.query().subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'GET' });
      req.flush([returnedFromService]);
      httpMock.verify();
      expect(expectedResult).toMatchObject([expected]);
    });

    it('should delete a SysTenant', () => {
      const expected = true;

      service.delete('ABC').subscribe(resp => (expectedResult = resp.ok));

      const req = httpMock.expectOne({ method: 'DELETE' });
      req.flush({ status: 200 });
      expect(expectedResult).toBe(expected);
    });

    describe('addSysTenantToCollectionIfMissing', () => {
      it('should add a SysTenant to an empty array', () => {
        const sysTenant: ISysTenant = sampleWithRequiredData;
        expectedResult = service.addSysTenantToCollectionIfMissing([], sysTenant);
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(sysTenant);
      });

      it('should not add a SysTenant to an array that contains it', () => {
        const sysTenant: ISysTenant = sampleWithRequiredData;
        const sysTenantCollection: ISysTenant[] = [
          {
            ...sysTenant,
          },
          sampleWithPartialData,
        ];
        expectedResult = service.addSysTenantToCollectionIfMissing(sysTenantCollection, sysTenant);
        expect(expectedResult).toHaveLength(2);
      });

      it("should add a SysTenant to an array that doesn't contain it", () => {
        const sysTenant: ISysTenant = sampleWithRequiredData;
        const sysTenantCollection: ISysTenant[] = [sampleWithPartialData];
        expectedResult = service.addSysTenantToCollectionIfMissing(sysTenantCollection, sysTenant);
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(sysTenant);
      });

      it('should add only unique SysTenant to an array', () => {
        const sysTenantArray: ISysTenant[] = [sampleWithRequiredData, sampleWithPartialData, sampleWithFullData];
        const sysTenantCollection: ISysTenant[] = [sampleWithRequiredData];
        expectedResult = service.addSysTenantToCollectionIfMissing(sysTenantCollection, ...sysTenantArray);
        expect(expectedResult).toHaveLength(3);
      });

      it('should accept varargs', () => {
        const sysTenant: ISysTenant = sampleWithRequiredData;
        const sysTenant2: ISysTenant = sampleWithPartialData;
        expectedResult = service.addSysTenantToCollectionIfMissing([], sysTenant, sysTenant2);
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(sysTenant);
        expect(expectedResult).toContain(sysTenant2);
      });

      it('should accept null and undefined values', () => {
        const sysTenant: ISysTenant = sampleWithRequiredData;
        expectedResult = service.addSysTenantToCollectionIfMissing([], null, sysTenant, undefined);
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(sysTenant);
      });

      it('should return initial array if no SysTenant is added', () => {
        const sysTenantCollection: ISysTenant[] = [sampleWithRequiredData];
        expectedResult = service.addSysTenantToCollectionIfMissing(sysTenantCollection, undefined, null);
        expect(expectedResult).toEqual(sysTenantCollection);
      });
    });

    describe('compareSysTenant', () => {
      it('Should return true if both entities are null', () => {
        const entity1 = null;
        const entity2 = null;

        const compareResult = service.compareSysTenant(entity1, entity2);

        expect(compareResult).toEqual(true);
      });

      it('Should return false if one entity is null', () => {
        const entity1 = { id: 'ABC' };
        const entity2 = null;

        const compareResult1 = service.compareSysTenant(entity1, entity2);
        const compareResult2 = service.compareSysTenant(entity2, entity1);

        expect(compareResult1).toEqual(false);
        expect(compareResult2).toEqual(false);
      });

      it('Should return false if primaryKey differs', () => {
        const entity1 = { id: 'ABC' };
        const entity2 = { id: 'CBA' };

        const compareResult1 = service.compareSysTenant(entity1, entity2);
        const compareResult2 = service.compareSysTenant(entity2, entity1);

        expect(compareResult1).toEqual(false);
        expect(compareResult2).toEqual(false);
      });

      it('Should return false if primaryKey matches', () => {
        const entity1 = { id: 'ABC' };
        const entity2 = { id: 'ABC' };

        const compareResult1 = service.compareSysTenant(entity1, entity2);
        const compareResult2 = service.compareSysTenant(entity2, entity1);

        expect(compareResult1).toEqual(true);
        expect(compareResult2).toEqual(true);
      });
    });
  });

  afterEach(() => {
    httpMock.verify();
  });
});
