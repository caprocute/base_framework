import { TestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';

import { DATE_FORMAT } from 'app/config/input.constants';
import { ISysPermissionDataRule } from '../sys-permission-data-rule.model';
import {
  sampleWithRequiredData,
  sampleWithNewData,
  sampleWithPartialData,
  sampleWithFullData,
} from '../sys-permission-data-rule.test-samples';

import { SysPermissionDataRuleService, RestSysPermissionDataRule } from './sys-permission-data-rule.service';

const requireRestSample: RestSysPermissionDataRule = {
  ...sampleWithRequiredData,
  createTime: sampleWithRequiredData.createTime?.format(DATE_FORMAT),
  updateTime: sampleWithRequiredData.updateTime?.format(DATE_FORMAT),
};

describe('SysPermissionDataRule Service', () => {
  let service: SysPermissionDataRuleService;
  let httpMock: HttpTestingController;
  let expectedResult: ISysPermissionDataRule | ISysPermissionDataRule[] | boolean | null;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule],
    });
    expectedResult = null;
    service = TestBed.inject(SysPermissionDataRuleService);
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

    it('should create a SysPermissionDataRule', () => {
      // eslint-disable-next-line @typescript-eslint/no-unused-vars
      const sysPermissionDataRule = { ...sampleWithNewData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.create(sysPermissionDataRule).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'POST' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should update a SysPermissionDataRule', () => {
      const sysPermissionDataRule = { ...sampleWithRequiredData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.update(sysPermissionDataRule).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'PUT' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should partial update a SysPermissionDataRule', () => {
      const patchObject = { ...sampleWithPartialData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.partialUpdate(patchObject).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'PATCH' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should return a list of SysPermissionDataRule', () => {
      const returnedFromService = { ...requireRestSample };

      const expected = { ...sampleWithRequiredData };

      service.query().subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'GET' });
      req.flush([returnedFromService]);
      httpMock.verify();
      expect(expectedResult).toMatchObject([expected]);
    });

    it('should delete a SysPermissionDataRule', () => {
      const expected = true;

      service.delete('ABC').subscribe(resp => (expectedResult = resp.ok));

      const req = httpMock.expectOne({ method: 'DELETE' });
      req.flush({ status: 200 });
      expect(expectedResult).toBe(expected);
    });

    describe('addSysPermissionDataRuleToCollectionIfMissing', () => {
      it('should add a SysPermissionDataRule to an empty array', () => {
        const sysPermissionDataRule: ISysPermissionDataRule = sampleWithRequiredData;
        expectedResult = service.addSysPermissionDataRuleToCollectionIfMissing([], sysPermissionDataRule);
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(sysPermissionDataRule);
      });

      it('should not add a SysPermissionDataRule to an array that contains it', () => {
        const sysPermissionDataRule: ISysPermissionDataRule = sampleWithRequiredData;
        const sysPermissionDataRuleCollection: ISysPermissionDataRule[] = [
          {
            ...sysPermissionDataRule,
          },
          sampleWithPartialData,
        ];
        expectedResult = service.addSysPermissionDataRuleToCollectionIfMissing(sysPermissionDataRuleCollection, sysPermissionDataRule);
        expect(expectedResult).toHaveLength(2);
      });

      it("should add a SysPermissionDataRule to an array that doesn't contain it", () => {
        const sysPermissionDataRule: ISysPermissionDataRule = sampleWithRequiredData;
        const sysPermissionDataRuleCollection: ISysPermissionDataRule[] = [sampleWithPartialData];
        expectedResult = service.addSysPermissionDataRuleToCollectionIfMissing(sysPermissionDataRuleCollection, sysPermissionDataRule);
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(sysPermissionDataRule);
      });

      it('should add only unique SysPermissionDataRule to an array', () => {
        const sysPermissionDataRuleArray: ISysPermissionDataRule[] = [sampleWithRequiredData, sampleWithPartialData, sampleWithFullData];
        const sysPermissionDataRuleCollection: ISysPermissionDataRule[] = [sampleWithRequiredData];
        expectedResult = service.addSysPermissionDataRuleToCollectionIfMissing(
          sysPermissionDataRuleCollection,
          ...sysPermissionDataRuleArray
        );
        expect(expectedResult).toHaveLength(3);
      });

      it('should accept varargs', () => {
        const sysPermissionDataRule: ISysPermissionDataRule = sampleWithRequiredData;
        const sysPermissionDataRule2: ISysPermissionDataRule = sampleWithPartialData;
        expectedResult = service.addSysPermissionDataRuleToCollectionIfMissing([], sysPermissionDataRule, sysPermissionDataRule2);
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(sysPermissionDataRule);
        expect(expectedResult).toContain(sysPermissionDataRule2);
      });

      it('should accept null and undefined values', () => {
        const sysPermissionDataRule: ISysPermissionDataRule = sampleWithRequiredData;
        expectedResult = service.addSysPermissionDataRuleToCollectionIfMissing([], null, sysPermissionDataRule, undefined);
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(sysPermissionDataRule);
      });

      it('should return initial array if no SysPermissionDataRule is added', () => {
        const sysPermissionDataRuleCollection: ISysPermissionDataRule[] = [sampleWithRequiredData];
        expectedResult = service.addSysPermissionDataRuleToCollectionIfMissing(sysPermissionDataRuleCollection, undefined, null);
        expect(expectedResult).toEqual(sysPermissionDataRuleCollection);
      });
    });

    describe('compareSysPermissionDataRule', () => {
      it('Should return true if both entities are null', () => {
        const entity1 = null;
        const entity2 = null;

        const compareResult = service.compareSysPermissionDataRule(entity1, entity2);

        expect(compareResult).toEqual(true);
      });

      it('Should return false if one entity is null', () => {
        const entity1 = { id: 'ABC' };
        const entity2 = null;

        const compareResult1 = service.compareSysPermissionDataRule(entity1, entity2);
        const compareResult2 = service.compareSysPermissionDataRule(entity2, entity1);

        expect(compareResult1).toEqual(false);
        expect(compareResult2).toEqual(false);
      });

      it('Should return false if primaryKey differs', () => {
        const entity1 = { id: 'ABC' };
        const entity2 = { id: 'CBA' };

        const compareResult1 = service.compareSysPermissionDataRule(entity1, entity2);
        const compareResult2 = service.compareSysPermissionDataRule(entity2, entity1);

        expect(compareResult1).toEqual(false);
        expect(compareResult2).toEqual(false);
      });

      it('Should return false if primaryKey matches', () => {
        const entity1 = { id: 'ABC' };
        const entity2 = { id: 'ABC' };

        const compareResult1 = service.compareSysPermissionDataRule(entity1, entity2);
        const compareResult2 = service.compareSysPermissionDataRule(entity2, entity1);

        expect(compareResult1).toEqual(true);
        expect(compareResult2).toEqual(true);
      });
    });
  });

  afterEach(() => {
    httpMock.verify();
  });
});
