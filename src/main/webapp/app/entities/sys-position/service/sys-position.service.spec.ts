import { TestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';

import { DATE_FORMAT } from 'app/config/input.constants';
import { ISysPosition } from '../sys-position.model';
import { sampleWithRequiredData, sampleWithNewData, sampleWithPartialData, sampleWithFullData } from '../sys-position.test-samples';

import { SysPositionService, RestSysPosition } from './sys-position.service';

const requireRestSample: RestSysPosition = {
  ...sampleWithRequiredData,
  createTime: sampleWithRequiredData.createTime?.format(DATE_FORMAT),
  updateTime: sampleWithRequiredData.updateTime?.format(DATE_FORMAT),
};

describe('SysPosition Service', () => {
  let service: SysPositionService;
  let httpMock: HttpTestingController;
  let expectedResult: ISysPosition | ISysPosition[] | boolean | null;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule],
    });
    expectedResult = null;
    service = TestBed.inject(SysPositionService);
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

    it('should create a SysPosition', () => {
      // eslint-disable-next-line @typescript-eslint/no-unused-vars
      const sysPosition = { ...sampleWithNewData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.create(sysPosition).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'POST' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should update a SysPosition', () => {
      const sysPosition = { ...sampleWithRequiredData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.update(sysPosition).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'PUT' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should partial update a SysPosition', () => {
      const patchObject = { ...sampleWithPartialData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.partialUpdate(patchObject).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'PATCH' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should return a list of SysPosition', () => {
      const returnedFromService = { ...requireRestSample };

      const expected = { ...sampleWithRequiredData };

      service.query().subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'GET' });
      req.flush([returnedFromService]);
      httpMock.verify();
      expect(expectedResult).toMatchObject([expected]);
    });

    it('should delete a SysPosition', () => {
      const expected = true;

      service.delete('ABC').subscribe(resp => (expectedResult = resp.ok));

      const req = httpMock.expectOne({ method: 'DELETE' });
      req.flush({ status: 200 });
      expect(expectedResult).toBe(expected);
    });

    describe('addSysPositionToCollectionIfMissing', () => {
      it('should add a SysPosition to an empty array', () => {
        const sysPosition: ISysPosition = sampleWithRequiredData;
        expectedResult = service.addSysPositionToCollectionIfMissing([], sysPosition);
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(sysPosition);
      });

      it('should not add a SysPosition to an array that contains it', () => {
        const sysPosition: ISysPosition = sampleWithRequiredData;
        const sysPositionCollection: ISysPosition[] = [
          {
            ...sysPosition,
          },
          sampleWithPartialData,
        ];
        expectedResult = service.addSysPositionToCollectionIfMissing(sysPositionCollection, sysPosition);
        expect(expectedResult).toHaveLength(2);
      });

      it("should add a SysPosition to an array that doesn't contain it", () => {
        const sysPosition: ISysPosition = sampleWithRequiredData;
        const sysPositionCollection: ISysPosition[] = [sampleWithPartialData];
        expectedResult = service.addSysPositionToCollectionIfMissing(sysPositionCollection, sysPosition);
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(sysPosition);
      });

      it('should add only unique SysPosition to an array', () => {
        const sysPositionArray: ISysPosition[] = [sampleWithRequiredData, sampleWithPartialData, sampleWithFullData];
        const sysPositionCollection: ISysPosition[] = [sampleWithRequiredData];
        expectedResult = service.addSysPositionToCollectionIfMissing(sysPositionCollection, ...sysPositionArray);
        expect(expectedResult).toHaveLength(3);
      });

      it('should accept varargs', () => {
        const sysPosition: ISysPosition = sampleWithRequiredData;
        const sysPosition2: ISysPosition = sampleWithPartialData;
        expectedResult = service.addSysPositionToCollectionIfMissing([], sysPosition, sysPosition2);
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(sysPosition);
        expect(expectedResult).toContain(sysPosition2);
      });

      it('should accept null and undefined values', () => {
        const sysPosition: ISysPosition = sampleWithRequiredData;
        expectedResult = service.addSysPositionToCollectionIfMissing([], null, sysPosition, undefined);
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(sysPosition);
      });

      it('should return initial array if no SysPosition is added', () => {
        const sysPositionCollection: ISysPosition[] = [sampleWithRequiredData];
        expectedResult = service.addSysPositionToCollectionIfMissing(sysPositionCollection, undefined, null);
        expect(expectedResult).toEqual(sysPositionCollection);
      });
    });

    describe('compareSysPosition', () => {
      it('Should return true if both entities are null', () => {
        const entity1 = null;
        const entity2 = null;

        const compareResult = service.compareSysPosition(entity1, entity2);

        expect(compareResult).toEqual(true);
      });

      it('Should return false if one entity is null', () => {
        const entity1 = { id: 'ABC' };
        const entity2 = null;

        const compareResult1 = service.compareSysPosition(entity1, entity2);
        const compareResult2 = service.compareSysPosition(entity2, entity1);

        expect(compareResult1).toEqual(false);
        expect(compareResult2).toEqual(false);
      });

      it('Should return false if primaryKey differs', () => {
        const entity1 = { id: 'ABC' };
        const entity2 = { id: 'CBA' };

        const compareResult1 = service.compareSysPosition(entity1, entity2);
        const compareResult2 = service.compareSysPosition(entity2, entity1);

        expect(compareResult1).toEqual(false);
        expect(compareResult2).toEqual(false);
      });

      it('Should return false if primaryKey matches', () => {
        const entity1 = { id: 'ABC' };
        const entity2 = { id: 'ABC' };

        const compareResult1 = service.compareSysPosition(entity1, entity2);
        const compareResult2 = service.compareSysPosition(entity2, entity1);

        expect(compareResult1).toEqual(true);
        expect(compareResult2).toEqual(true);
      });
    });
  });

  afterEach(() => {
    httpMock.verify();
  });
});
