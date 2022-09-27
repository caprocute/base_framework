import { TestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';

import { DATE_FORMAT } from 'app/config/input.constants';
import { ISysLog } from '../sys-log.model';
import { sampleWithRequiredData, sampleWithNewData, sampleWithPartialData, sampleWithFullData } from '../sys-log.test-samples';

import { SysLogService, RestSysLog } from './sys-log.service';

const requireRestSample: RestSysLog = {
  ...sampleWithRequiredData,
  createTime: sampleWithRequiredData.createTime?.format(DATE_FORMAT),
  updateTime: sampleWithRequiredData.updateTime?.format(DATE_FORMAT),
};

describe('SysLog Service', () => {
  let service: SysLogService;
  let httpMock: HttpTestingController;
  let expectedResult: ISysLog | ISysLog[] | boolean | null;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule],
    });
    expectedResult = null;
    service = TestBed.inject(SysLogService);
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

    it('should create a SysLog', () => {
      // eslint-disable-next-line @typescript-eslint/no-unused-vars
      const sysLog = { ...sampleWithNewData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.create(sysLog).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'POST' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should update a SysLog', () => {
      const sysLog = { ...sampleWithRequiredData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.update(sysLog).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'PUT' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should partial update a SysLog', () => {
      const patchObject = { ...sampleWithPartialData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.partialUpdate(patchObject).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'PATCH' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should return a list of SysLog', () => {
      const returnedFromService = { ...requireRestSample };

      const expected = { ...sampleWithRequiredData };

      service.query().subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'GET' });
      req.flush([returnedFromService]);
      httpMock.verify();
      expect(expectedResult).toMatchObject([expected]);
    });

    it('should delete a SysLog', () => {
      const expected = true;

      service.delete('ABC').subscribe(resp => (expectedResult = resp.ok));

      const req = httpMock.expectOne({ method: 'DELETE' });
      req.flush({ status: 200 });
      expect(expectedResult).toBe(expected);
    });

    describe('addSysLogToCollectionIfMissing', () => {
      it('should add a SysLog to an empty array', () => {
        const sysLog: ISysLog = sampleWithRequiredData;
        expectedResult = service.addSysLogToCollectionIfMissing([], sysLog);
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(sysLog);
      });

      it('should not add a SysLog to an array that contains it', () => {
        const sysLog: ISysLog = sampleWithRequiredData;
        const sysLogCollection: ISysLog[] = [
          {
            ...sysLog,
          },
          sampleWithPartialData,
        ];
        expectedResult = service.addSysLogToCollectionIfMissing(sysLogCollection, sysLog);
        expect(expectedResult).toHaveLength(2);
      });

      it("should add a SysLog to an array that doesn't contain it", () => {
        const sysLog: ISysLog = sampleWithRequiredData;
        const sysLogCollection: ISysLog[] = [sampleWithPartialData];
        expectedResult = service.addSysLogToCollectionIfMissing(sysLogCollection, sysLog);
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(sysLog);
      });

      it('should add only unique SysLog to an array', () => {
        const sysLogArray: ISysLog[] = [sampleWithRequiredData, sampleWithPartialData, sampleWithFullData];
        const sysLogCollection: ISysLog[] = [sampleWithRequiredData];
        expectedResult = service.addSysLogToCollectionIfMissing(sysLogCollection, ...sysLogArray);
        expect(expectedResult).toHaveLength(3);
      });

      it('should accept varargs', () => {
        const sysLog: ISysLog = sampleWithRequiredData;
        const sysLog2: ISysLog = sampleWithPartialData;
        expectedResult = service.addSysLogToCollectionIfMissing([], sysLog, sysLog2);
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(sysLog);
        expect(expectedResult).toContain(sysLog2);
      });

      it('should accept null and undefined values', () => {
        const sysLog: ISysLog = sampleWithRequiredData;
        expectedResult = service.addSysLogToCollectionIfMissing([], null, sysLog, undefined);
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(sysLog);
      });

      it('should return initial array if no SysLog is added', () => {
        const sysLogCollection: ISysLog[] = [sampleWithRequiredData];
        expectedResult = service.addSysLogToCollectionIfMissing(sysLogCollection, undefined, null);
        expect(expectedResult).toEqual(sysLogCollection);
      });
    });

    describe('compareSysLog', () => {
      it('Should return true if both entities are null', () => {
        const entity1 = null;
        const entity2 = null;

        const compareResult = service.compareSysLog(entity1, entity2);

        expect(compareResult).toEqual(true);
      });

      it('Should return false if one entity is null', () => {
        const entity1 = { id: 'ABC' };
        const entity2 = null;

        const compareResult1 = service.compareSysLog(entity1, entity2);
        const compareResult2 = service.compareSysLog(entity2, entity1);

        expect(compareResult1).toEqual(false);
        expect(compareResult2).toEqual(false);
      });

      it('Should return false if primaryKey differs', () => {
        const entity1 = { id: 'ABC' };
        const entity2 = { id: 'CBA' };

        const compareResult1 = service.compareSysLog(entity1, entity2);
        const compareResult2 = service.compareSysLog(entity2, entity1);

        expect(compareResult1).toEqual(false);
        expect(compareResult2).toEqual(false);
      });

      it('Should return false if primaryKey matches', () => {
        const entity1 = { id: 'ABC' };
        const entity2 = { id: 'ABC' };

        const compareResult1 = service.compareSysLog(entity1, entity2);
        const compareResult2 = service.compareSysLog(entity2, entity1);

        expect(compareResult1).toEqual(true);
        expect(compareResult2).toEqual(true);
      });
    });
  });

  afterEach(() => {
    httpMock.verify();
  });
});
