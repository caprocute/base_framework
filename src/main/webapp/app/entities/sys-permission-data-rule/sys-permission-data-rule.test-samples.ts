import dayjs from 'dayjs/esm';

import { ISysPermissionDataRule, NewSysPermissionDataRule } from './sys-permission-data-rule.model';

export const sampleWithRequiredData: ISysPermissionDataRule = {
  id: '770bb125-eb00-4dea-a7bd-cb170d8fb2b0',
};

export const sampleWithPartialData: ISysPermissionDataRule = {
  id: '258f83d0-ae8d-4f98-b2fd-ec0d79ffa122',
  permissionId: 'Berkshire SCSI',
  ruleName: 'Investment deposit',
  ruleColumn: 'platforms Multi-tiered front-end',
  status: 88316,
  createTime: dayjs('2022-09-26'),
};

export const sampleWithFullData: ISysPermissionDataRule = {
  id: '40082dad-b0c8-4df4-b611-6383e96a6c1c',
  permissionId: 'Viaduct Mountain parsing',
  ruleName: 'deposit Plaza Web',
  ruleColumn: 'Orchestrator',
  ruleCOnditions: 'Bedfordshire Planner',
  ruleValue: 'bypass',
  status: 80779,
  createBy: 'Awesome',
  createTime: dayjs('2022-09-27'),
  updateBy: 'Soap',
  updateTime: dayjs('2022-09-26'),
  tenantId: 'SQL',
};

export const sampleWithNewData: NewSysPermissionDataRule = {
  id: null,
};

Object.freeze(sampleWithNewData);
Object.freeze(sampleWithRequiredData);
Object.freeze(sampleWithPartialData);
Object.freeze(sampleWithFullData);
