import { TestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';

import { DATE_FORMAT } from 'app/config/input.constants';
import { ISysDataSource } from '../sys-data-source.model';
import { sampleWithRequiredData, sampleWithNewData, sampleWithPartialData, sampleWithFullData } from '../sys-data-source.test-samples';

import { SysDataSourceService, RestSysDataSource } from './sys-data-source.service';

const requireRestSample: RestSysDataSource = {
  ...sampleWithRequiredData,
  createTime: sampleWithRequiredData.createTime?.format(DATE_FORMAT),
  updateTime: sampleWithRequiredData.updateTime?.format(DATE_FORMAT),
};

describe('SysDataSource Service', () => {
  let service: SysDataSourceService;
  let httpMock: HttpTestingController;
  let expectedResult: ISysDataSource | ISysDataSource[] | boolean | null;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule],
    });
    expectedResult = null;
    service = TestBed.inject(SysDataSourceService);
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

    it('should create a SysDataSource', () => {
      // eslint-disable-next-line @typescript-eslint/no-unused-vars
      const sysDataSource = { ...sampleWithNewData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.create(sysDataSource).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'POST' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should update a SysDataSource', () => {
      const sysDataSource = { ...sampleWithRequiredData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.update(sysDataSource).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'PUT' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should partial update a SysDataSource', () => {
      const patchObject = { ...sampleWithPartialData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.partialUpdate(patchObject).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'PATCH' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should return a list of SysDataSource', () => {
      const returnedFromService = { ...requireRestSample };

      const expected = { ...sampleWithRequiredData };

      service.query().subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'GET' });
      req.flush([returnedFromService]);
      httpMock.verify();
      expect(expectedResult).toMatchObject([expected]);
    });

    it('should delete a SysDataSource', () => {
      const expected = true;

      service.delete('ABC').subscribe(resp => (expectedResult = resp.ok));

      const req = httpMock.expectOne({ method: 'DELETE' });
      req.flush({ status: 200 });
      expect(expectedResult).toBe(expected);
    });

    describe('addSysDataSourceToCollectionIfMissing', () => {
      it('should add a SysDataSource to an empty array', () => {
        const sysDataSource: ISysDataSource = sampleWithRequiredData;
        expectedResult = service.addSysDataSourceToCollectionIfMissing([], sysDataSource);
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(sysDataSource);
      });

      it('should not add a SysDataSource to an array that contains it', () => {
        const sysDataSource: ISysDataSource = sampleWithRequiredData;
        const sysDataSourceCollection: ISysDataSource[] = [
          {
            ...sysDataSource,
          },
          sampleWithPartialData,
        ];
        expectedResult = service.addSysDataSourceToCollectionIfMissing(sysDataSourceCollection, sysDataSource);
        expect(expectedResult).toHaveLength(2);
      });

      it("should add a SysDataSource to an array that doesn't contain it", () => {
        const sysDataSource: ISysDataSource = sampleWithRequiredData;
        const sysDataSourceCollection: ISysDataSource[] = [sampleWithPartialData];
        expectedResult = service.addSysDataSourceToCollectionIfMissing(sysDataSourceCollection, sysDataSource);
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(sysDataSource);
      });

      it('should add only unique SysDataSource to an array', () => {
        const sysDataSourceArray: ISysDataSource[] = [sampleWithRequiredData, sampleWithPartialData, sampleWithFullData];
        const sysDataSourceCollection: ISysDataSource[] = [sampleWithRequiredData];
        expectedResult = service.addSysDataSourceToCollectionIfMissing(sysDataSourceCollection, ...sysDataSourceArray);
        expect(expectedResult).toHaveLength(3);
      });

      it('should accept varargs', () => {
        const sysDataSource: ISysDataSource = sampleWithRequiredData;
        const sysDataSource2: ISysDataSource = sampleWithPartialData;
        expectedResult = service.addSysDataSourceToCollectionIfMissing([], sysDataSource, sysDataSource2);
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(sysDataSource);
        expect(expectedResult).toContain(sysDataSource2);
      });

      it('should accept null and undefined values', () => {
        const sysDataSource: ISysDataSource = sampleWithRequiredData;
        expectedResult = service.addSysDataSourceToCollectionIfMissing([], null, sysDataSource, undefined);
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(sysDataSource);
      });

      it('should return initial array if no SysDataSource is added', () => {
        const sysDataSourceCollection: ISysDataSource[] = [sampleWithRequiredData];
        expectedResult = service.addSysDataSourceToCollectionIfMissing(sysDataSourceCollection, undefined, null);
        expect(expectedResult).toEqual(sysDataSourceCollection);
      });
    });

    describe('compareSysDataSource', () => {
      it('Should return true if both entities are null', () => {
        const entity1 = null;
        const entity2 = null;

        const compareResult = service.compareSysDataSource(entity1, entity2);

        expect(compareResult).toEqual(true);
      });

      it('Should return false if one entity is null', () => {
        const entity1 = { id: 'ABC' };
        const entity2 = null;

        const compareResult1 = service.compareSysDataSource(entity1, entity2);
        const compareResult2 = service.compareSysDataSource(entity2, entity1);

        expect(compareResult1).toEqual(false);
        expect(compareResult2).toEqual(false);
      });

      it('Should return false if primaryKey differs', () => {
        const entity1 = { id: 'ABC' };
        const entity2 = { id: 'CBA' };

        const compareResult1 = service.compareSysDataSource(entity1, entity2);
        const compareResult2 = service.compareSysDataSource(entity2, entity1);

        expect(compareResult1).toEqual(false);
        expect(compareResult2).toEqual(false);
      });

      it('Should return false if primaryKey matches', () => {
        const entity1 = { id: 'ABC' };
        const entity2 = { id: 'ABC' };

        const compareResult1 = service.compareSysDataSource(entity1, entity2);
        const compareResult2 = service.compareSysDataSource(entity2, entity1);

        expect(compareResult1).toEqual(true);
        expect(compareResult2).toEqual(true);
      });
    });
  });

  afterEach(() => {
    httpMock.verify();
  });
});
