import { TestBed } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { ActivatedRouteSnapshot, ActivatedRoute, Router, convertToParamMap } from '@angular/router';
import { RouterTestingModule } from '@angular/router/testing';
import { of } from 'rxjs';

import { ISysPermissionDataRule } from '../sys-permission-data-rule.model';
import { SysPermissionDataRuleService } from '../service/sys-permission-data-rule.service';

import { SysPermissionDataRuleRoutingResolveService } from './sys-permission-data-rule-routing-resolve.service';

describe('SysPermissionDataRule routing resolve service', () => {
  let mockRouter: Router;
  let mockActivatedRouteSnapshot: ActivatedRouteSnapshot;
  let routingResolveService: SysPermissionDataRuleRoutingResolveService;
  let service: SysPermissionDataRuleService;
  let resultSysPermissionDataRule: ISysPermissionDataRule | null | undefined;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule, RouterTestingModule.withRoutes([])],
      providers: [
        {
          provide: ActivatedRoute,
          useValue: {
            snapshot: {
              paramMap: convertToParamMap({}),
            },
          },
        },
      ],
    });
    mockRouter = TestBed.inject(Router);
    jest.spyOn(mockRouter, 'navigate').mockImplementation(() => Promise.resolve(true));
    mockActivatedRouteSnapshot = TestBed.inject(ActivatedRoute).snapshot;
    routingResolveService = TestBed.inject(SysPermissionDataRuleRoutingResolveService);
    service = TestBed.inject(SysPermissionDataRuleService);
    resultSysPermissionDataRule = undefined;
  });

  describe('resolve', () => {
    it('should return ISysPermissionDataRule returned by find', () => {
      // GIVEN
      service.find = jest.fn(id => of(new HttpResponse({ body: { id } })));
      mockActivatedRouteSnapshot.params = { id: 'ABC' };

      // WHEN
      routingResolveService.resolve(mockActivatedRouteSnapshot).subscribe(result => {
        resultSysPermissionDataRule = result;
      });

      // THEN
      expect(service.find).toBeCalledWith('ABC');
      expect(resultSysPermissionDataRule).toEqual({ id: 'ABC' });
    });

    it('should return null if id is not provided', () => {
      // GIVEN
      service.find = jest.fn();
      mockActivatedRouteSnapshot.params = {};

      // WHEN
      routingResolveService.resolve(mockActivatedRouteSnapshot).subscribe(result => {
        resultSysPermissionDataRule = result;
      });

      // THEN
      expect(service.find).not.toBeCalled();
      expect(resultSysPermissionDataRule).toEqual(null);
    });

    it('should route to 404 page if data not found in server', () => {
      // GIVEN
      jest.spyOn(service, 'find').mockReturnValue(of(new HttpResponse<ISysPermissionDataRule>({ body: null })));
      mockActivatedRouteSnapshot.params = { id: 'ABC' };

      // WHEN
      routingResolveService.resolve(mockActivatedRouteSnapshot).subscribe(result => {
        resultSysPermissionDataRule = result;
      });

      // THEN
      expect(service.find).toBeCalledWith('ABC');
      expect(resultSysPermissionDataRule).toEqual(undefined);
      expect(mockRouter.navigate).toHaveBeenCalledWith(['404']);
    });
  });
});
