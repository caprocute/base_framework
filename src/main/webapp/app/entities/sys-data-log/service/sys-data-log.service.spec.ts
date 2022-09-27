import { TestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';

import { DATE_FORMAT } from 'app/config/input.constants';
import { ISysDataLog } from '../sys-data-log.model';
import { sampleWithRequiredData, sampleWithNewData, sampleWithPartialData, sampleWithFullData } from '../sys-data-log.test-samples';

import { SysDataLogService, RestSysDataLog } from './sys-data-log.service';

const requireRestSample: RestSysDataLog = {
  ...sampleWithRequiredData,
  createTime: sampleWithRequiredData.createTime?.format(DATE_FORMAT),
  updateTime: sampleWithRequiredData.updateTime?.format(DATE_FORMAT),
};

describe('SysDataLog Service', () => {
  let service: SysDataLogService;
  let httpMock: HttpTestingController;
  let expectedResult: ISysDataLog | ISysDataLog[] | boolean | null;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule],
    });
    expectedResult = null;
    service = TestBed.inject(SysDataLogService);
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

    it('should create a SysDataLog', () => {
      // eslint-disable-next-line @typescript-eslint/no-unused-vars
      const sysDataLog = { ...sampleWithNewData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.create(sysDataLog).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'POST' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should update a SysDataLog', () => {
      const sysDataLog = { ...sampleWithRequiredData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.update(sysDataLog).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'PUT' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should partial update a SysDataLog', () => {
      const patchObject = { ...sampleWithPartialData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.partialUpdate(patchObject).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'PATCH' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should return a list of SysDataLog', () => {
      const returnedFromService = { ...requireRestSample };

      const expected = { ...sampleWithRequiredData };

      service.query().subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'GET' });
      req.flush([returnedFromService]);
      httpMock.verify();
      expect(expectedResult).toMatchObject([expected]);
    });

    it('should delete a SysDataLog', () => {
      const expected = true;

      service.delete('ABC').subscribe(resp => (expectedResult = resp.ok));

      const req = httpMock.expectOne({ method: 'DELETE' });
      req.flush({ status: 200 });
      expect(expectedResult).toBe(expected);
    });

    describe('addSysDataLogToCollectionIfMissing', () => {
      it('should add a SysDataLog to an empty array', () => {
        const sysDataLog: ISysDataLog = sampleWithRequiredData;
        expectedResult = service.addSysDataLogToCollectionIfMissing([], sysDataLog);
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(sysDataLog);
      });

      it('should not add a SysDataLog to an array that contains it', () => {
        const sysDataLog: ISysDataLog = sampleWithRequiredData;
        const sysDataLogCollection: ISysDataLog[] = [
          {
            ...sysDataLog,
          },
          sampleWithPartialData,
        ];
        expectedResult = service.addSysDataLogToCollectionIfMissing(sysDataLogCollection, sysDataLog);
        expect(expectedResult).toHaveLength(2);
      });

      it("should add a SysDataLog to an array that doesn't contain it", () => {
        const sysDataLog: ISysDataLog = sampleWithRequiredData;
        const sysDataLogCollection: ISysDataLog[] = [sampleWithPartialData];
        expectedResult = service.addSysDataLogToCollectionIfMissing(sysDataLogCollection, sysDataLog);
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(sysDataLog);
      });

      it('should add only unique SysDataLog to an array', () => {
        const sysDataLogArray: ISysDataLog[] = [sampleWithRequiredData, sampleWithPartialData, sampleWithFullData];
        const sysDataLogCollection: ISysDataLog[] = [sampleWithRequiredData];
        expectedResult = service.addSysDataLogToCollectionIfMissing(sysDataLogCollection, ...sysDataLogArray);
        expect(expectedResult).toHaveLength(3);
      });

      it('should accept varargs', () => {
        const sysDataLog: ISysDataLog = sampleWithRequiredData;
        const sysDataLog2: ISysDataLog = sampleWithPartialData;
        expectedResult = service.addSysDataLogToCollectionIfMissing([], sysDataLog, sysDataLog2);
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(sysDataLog);
        expect(expectedResult).toContain(sysDataLog2);
      });

      it('should accept null and undefined values', () => {
        const sysDataLog: ISysDataLog = sampleWithRequiredData;
        expectedResult = service.addSysDataLogToCollectionIfMissing([], null, sysDataLog, undefined);
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(sysDataLog);
      });

      it('should return initial array if no SysDataLog is added', () => {
        const sysDataLogCollection: ISysDataLog[] = [sampleWithRequiredData];
        expectedResult = service.addSysDataLogToCollectionIfMissing(sysDataLogCollection, undefined, null);
        expect(expectedResult).toEqual(sysDataLogCollection);
      });
    });

    describe('compareSysDataLog', () => {
      it('Should return true if both entities are null', () => {
        const entity1 = null;
        const entity2 = null;

        const compareResult = service.compareSysDataLog(entity1, entity2);

        expect(compareResult).toEqual(true);
      });

      it('Should return false if one entity is null', () => {
        const entity1 = { id: 'ABC' };
        const entity2 = null;

        const compareResult1 = service.compareSysDataLog(entity1, entity2);
        const compareResult2 = service.compareSysDataLog(entity2, entity1);

        expect(compareResult1).toEqual(false);
        expect(compareResult2).toEqual(false);
      });

      it('Should return false if primaryKey differs', () => {
        const entity1 = { id: 'ABC' };
        const entity2 = { id: 'CBA' };

        const compareResult1 = service.compareSysDataLog(entity1, entity2);
        const compareResult2 = service.compareSysDataLog(entity2, entity1);

        expect(compareResult1).toEqual(false);
        expect(compareResult2).toEqual(false);
      });

      it('Should return false if primaryKey matches', () => {
        const entity1 = { id: 'ABC' };
        const entity2 = { id: 'ABC' };

        const compareResult1 = service.compareSysDataLog(entity1, entity2);
        const compareResult2 = service.compareSysDataLog(entity2, entity1);

        expect(compareResult1).toEqual(true);
        expect(compareResult2).toEqual(true);
      });
    });
  });

  afterEach(() => {
    httpMock.verify();
  });
});
