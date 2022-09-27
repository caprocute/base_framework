import { TestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';

import { DATE_FORMAT } from 'app/config/input.constants';
import { ISysDepartRole } from '../sys-depart-role.model';
import { sampleWithRequiredData, sampleWithNewData, sampleWithPartialData, sampleWithFullData } from '../sys-depart-role.test-samples';

import { SysDepartRoleService, RestSysDepartRole } from './sys-depart-role.service';

const requireRestSample: RestSysDepartRole = {
  ...sampleWithRequiredData,
  createTime: sampleWithRequiredData.createTime?.format(DATE_FORMAT),
  updateTime: sampleWithRequiredData.updateTime?.format(DATE_FORMAT),
};

describe('SysDepartRole Service', () => {
  let service: SysDepartRoleService;
  let httpMock: HttpTestingController;
  let expectedResult: ISysDepartRole | ISysDepartRole[] | boolean | null;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule],
    });
    expectedResult = null;
    service = TestBed.inject(SysDepartRoleService);
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

    it('should create a SysDepartRole', () => {
      // eslint-disable-next-line @typescript-eslint/no-unused-vars
      const sysDepartRole = { ...sampleWithNewData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.create(sysDepartRole).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'POST' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should update a SysDepartRole', () => {
      const sysDepartRole = { ...sampleWithRequiredData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.update(sysDepartRole).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'PUT' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should partial update a SysDepartRole', () => {
      const patchObject = { ...sampleWithPartialData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.partialUpdate(patchObject).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'PATCH' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should return a list of SysDepartRole', () => {
      const returnedFromService = { ...requireRestSample };

      const expected = { ...sampleWithRequiredData };

      service.query().subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'GET' });
      req.flush([returnedFromService]);
      httpMock.verify();
      expect(expectedResult).toMatchObject([expected]);
    });

    it('should delete a SysDepartRole', () => {
      const expected = true;

      service.delete('ABC').subscribe(resp => (expectedResult = resp.ok));

      const req = httpMock.expectOne({ method: 'DELETE' });
      req.flush({ status: 200 });
      expect(expectedResult).toBe(expected);
    });

    describe('addSysDepartRoleToCollectionIfMissing', () => {
      it('should add a SysDepartRole to an empty array', () => {
        const sysDepartRole: ISysDepartRole = sampleWithRequiredData;
        expectedResult = service.addSysDepartRoleToCollectionIfMissing([], sysDepartRole);
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(sysDepartRole);
      });

      it('should not add a SysDepartRole to an array that contains it', () => {
        const sysDepartRole: ISysDepartRole = sampleWithRequiredData;
        const sysDepartRoleCollection: ISysDepartRole[] = [
          {
            ...sysDepartRole,
          },
          sampleWithPartialData,
        ];
        expectedResult = service.addSysDepartRoleToCollectionIfMissing(sysDepartRoleCollection, sysDepartRole);
        expect(expectedResult).toHaveLength(2);
      });

      it("should add a SysDepartRole to an array that doesn't contain it", () => {
        const sysDepartRole: ISysDepartRole = sampleWithRequiredData;
        const sysDepartRoleCollection: ISysDepartRole[] = [sampleWithPartialData];
        expectedResult = service.addSysDepartRoleToCollectionIfMissing(sysDepartRoleCollection, sysDepartRole);
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(sysDepartRole);
      });

      it('should add only unique SysDepartRole to an array', () => {
        const sysDepartRoleArray: ISysDepartRole[] = [sampleWithRequiredData, sampleWithPartialData, sampleWithFullData];
        const sysDepartRoleCollection: ISysDepartRole[] = [sampleWithRequiredData];
        expectedResult = service.addSysDepartRoleToCollectionIfMissing(sysDepartRoleCollection, ...sysDepartRoleArray);
        expect(expectedResult).toHaveLength(3);
      });

      it('should accept varargs', () => {
        const sysDepartRole: ISysDepartRole = sampleWithRequiredData;
        const sysDepartRole2: ISysDepartRole = sampleWithPartialData;
        expectedResult = service.addSysDepartRoleToCollectionIfMissing([], sysDepartRole, sysDepartRole2);
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(sysDepartRole);
        expect(expectedResult).toContain(sysDepartRole2);
      });

      it('should accept null and undefined values', () => {
        const sysDepartRole: ISysDepartRole = sampleWithRequiredData;
        expectedResult = service.addSysDepartRoleToCollectionIfMissing([], null, sysDepartRole, undefined);
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(sysDepartRole);
      });

      it('should return initial array if no SysDepartRole is added', () => {
        const sysDepartRoleCollection: ISysDepartRole[] = [sampleWithRequiredData];
        expectedResult = service.addSysDepartRoleToCollectionIfMissing(sysDepartRoleCollection, undefined, null);
        expect(expectedResult).toEqual(sysDepartRoleCollection);
      });
    });

    describe('compareSysDepartRole', () => {
      it('Should return true if both entities are null', () => {
        const entity1 = null;
        const entity2 = null;

        const compareResult = service.compareSysDepartRole(entity1, entity2);

        expect(compareResult).toEqual(true);
      });

      it('Should return false if one entity is null', () => {
        const entity1 = { id: 'ABC' };
        const entity2 = null;

        const compareResult1 = service.compareSysDepartRole(entity1, entity2);
        const compareResult2 = service.compareSysDepartRole(entity2, entity1);

        expect(compareResult1).toEqual(false);
        expect(compareResult2).toEqual(false);
      });

      it('Should return false if primaryKey differs', () => {
        const entity1 = { id: 'ABC' };
        const entity2 = { id: 'CBA' };

        const compareResult1 = service.compareSysDepartRole(entity1, entity2);
        const compareResult2 = service.compareSysDepartRole(entity2, entity1);

        expect(compareResult1).toEqual(false);
        expect(compareResult2).toEqual(false);
      });

      it('Should return false if primaryKey matches', () => {
        const entity1 = { id: 'ABC' };
        const entity2 = { id: 'ABC' };

        const compareResult1 = service.compareSysDepartRole(entity1, entity2);
        const compareResult2 = service.compareSysDepartRole(entity2, entity1);

        expect(compareResult1).toEqual(true);
        expect(compareResult2).toEqual(true);
      });
    });
  });

  afterEach(() => {
    httpMock.verify();
  });
});
