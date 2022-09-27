import { TestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';

import { DATE_FORMAT } from 'app/config/input.constants';
import { ISysRoleIndex } from '../sys-role-index.model';
import { sampleWithRequiredData, sampleWithNewData, sampleWithPartialData, sampleWithFullData } from '../sys-role-index.test-samples';

import { SysRoleIndexService, RestSysRoleIndex } from './sys-role-index.service';

const requireRestSample: RestSysRoleIndex = {
  ...sampleWithRequiredData,
  createTime: sampleWithRequiredData.createTime?.format(DATE_FORMAT),
  updateTime: sampleWithRequiredData.updateTime?.format(DATE_FORMAT),
};

describe('SysRoleIndex Service', () => {
  let service: SysRoleIndexService;
  let httpMock: HttpTestingController;
  let expectedResult: ISysRoleIndex | ISysRoleIndex[] | boolean | null;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule],
    });
    expectedResult = null;
    service = TestBed.inject(SysRoleIndexService);
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

    it('should create a SysRoleIndex', () => {
      // eslint-disable-next-line @typescript-eslint/no-unused-vars
      const sysRoleIndex = { ...sampleWithNewData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.create(sysRoleIndex).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'POST' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should update a SysRoleIndex', () => {
      const sysRoleIndex = { ...sampleWithRequiredData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.update(sysRoleIndex).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'PUT' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should partial update a SysRoleIndex', () => {
      const patchObject = { ...sampleWithPartialData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.partialUpdate(patchObject).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'PATCH' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should return a list of SysRoleIndex', () => {
      const returnedFromService = { ...requireRestSample };

      const expected = { ...sampleWithRequiredData };

      service.query().subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'GET' });
      req.flush([returnedFromService]);
      httpMock.verify();
      expect(expectedResult).toMatchObject([expected]);
    });

    it('should delete a SysRoleIndex', () => {
      const expected = true;

      service.delete('ABC').subscribe(resp => (expectedResult = resp.ok));

      const req = httpMock.expectOne({ method: 'DELETE' });
      req.flush({ status: 200 });
      expect(expectedResult).toBe(expected);
    });

    describe('addSysRoleIndexToCollectionIfMissing', () => {
      it('should add a SysRoleIndex to an empty array', () => {
        const sysRoleIndex: ISysRoleIndex = sampleWithRequiredData;
        expectedResult = service.addSysRoleIndexToCollectionIfMissing([], sysRoleIndex);
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(sysRoleIndex);
      });

      it('should not add a SysRoleIndex to an array that contains it', () => {
        const sysRoleIndex: ISysRoleIndex = sampleWithRequiredData;
        const sysRoleIndexCollection: ISysRoleIndex[] = [
          {
            ...sysRoleIndex,
          },
          sampleWithPartialData,
        ];
        expectedResult = service.addSysRoleIndexToCollectionIfMissing(sysRoleIndexCollection, sysRoleIndex);
        expect(expectedResult).toHaveLength(2);
      });

      it("should add a SysRoleIndex to an array that doesn't contain it", () => {
        const sysRoleIndex: ISysRoleIndex = sampleWithRequiredData;
        const sysRoleIndexCollection: ISysRoleIndex[] = [sampleWithPartialData];
        expectedResult = service.addSysRoleIndexToCollectionIfMissing(sysRoleIndexCollection, sysRoleIndex);
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(sysRoleIndex);
      });

      it('should add only unique SysRoleIndex to an array', () => {
        const sysRoleIndexArray: ISysRoleIndex[] = [sampleWithRequiredData, sampleWithPartialData, sampleWithFullData];
        const sysRoleIndexCollection: ISysRoleIndex[] = [sampleWithRequiredData];
        expectedResult = service.addSysRoleIndexToCollectionIfMissing(sysRoleIndexCollection, ...sysRoleIndexArray);
        expect(expectedResult).toHaveLength(3);
      });

      it('should accept varargs', () => {
        const sysRoleIndex: ISysRoleIndex = sampleWithRequiredData;
        const sysRoleIndex2: ISysRoleIndex = sampleWithPartialData;
        expectedResult = service.addSysRoleIndexToCollectionIfMissing([], sysRoleIndex, sysRoleIndex2);
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(sysRoleIndex);
        expect(expectedResult).toContain(sysRoleIndex2);
      });

      it('should accept null and undefined values', () => {
        const sysRoleIndex: ISysRoleIndex = sampleWithRequiredData;
        expectedResult = service.addSysRoleIndexToCollectionIfMissing([], null, sysRoleIndex, undefined);
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(sysRoleIndex);
      });

      it('should return initial array if no SysRoleIndex is added', () => {
        const sysRoleIndexCollection: ISysRoleIndex[] = [sampleWithRequiredData];
        expectedResult = service.addSysRoleIndexToCollectionIfMissing(sysRoleIndexCollection, undefined, null);
        expect(expectedResult).toEqual(sysRoleIndexCollection);
      });
    });

    describe('compareSysRoleIndex', () => {
      it('Should return true if both entities are null', () => {
        const entity1 = null;
        const entity2 = null;

        const compareResult = service.compareSysRoleIndex(entity1, entity2);

        expect(compareResult).toEqual(true);
      });

      it('Should return false if one entity is null', () => {
        const entity1 = { id: 'ABC' };
        const entity2 = null;

        const compareResult1 = service.compareSysRoleIndex(entity1, entity2);
        const compareResult2 = service.compareSysRoleIndex(entity2, entity1);

        expect(compareResult1).toEqual(false);
        expect(compareResult2).toEqual(false);
      });

      it('Should return false if primaryKey differs', () => {
        const entity1 = { id: 'ABC' };
        const entity2 = { id: 'CBA' };

        const compareResult1 = service.compareSysRoleIndex(entity1, entity2);
        const compareResult2 = service.compareSysRoleIndex(entity2, entity1);

        expect(compareResult1).toEqual(false);
        expect(compareResult2).toEqual(false);
      });

      it('Should return false if primaryKey matches', () => {
        const entity1 = { id: 'ABC' };
        const entity2 = { id: 'ABC' };

        const compareResult1 = service.compareSysRoleIndex(entity1, entity2);
        const compareResult2 = service.compareSysRoleIndex(entity2, entity1);

        expect(compareResult1).toEqual(true);
        expect(compareResult2).toEqual(true);
      });
    });
  });

  afterEach(() => {
    httpMock.verify();
  });
});
