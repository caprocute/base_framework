import dayjs from 'dayjs/esm';

import { ISysRolePermission, NewSysRolePermission } from './sys-role-permission.model';

export const sampleWithRequiredData: ISysRolePermission = {
  id: 'bd507293-e89c-4354-8855-4916d6e99cfa',
};

export const sampleWithPartialData: ISysRolePermission = {
  id: '9b2e58eb-d65c-43b7-8dac-e0a62944065e',
  roleId: 'Tanzania',
  operateDate: dayjs('2022-09-26'),
  operateIp: 'virtual Mandatory black',
};

export const sampleWithFullData: ISysRolePermission = {
  id: '9942e9f4-3c7e-4252-a537-d7edb1993562',
  roleId: 'visualize Frozen',
  permissionId: 'withdrawal Pants implementation',
  dataRuleIds: 'IB',
  operateDate: dayjs('2022-09-27'),
  operateIp: 'indexing Berkshire',
};

export const sampleWithNewData: NewSysRolePermission = {
  id: null,
};

Object.freeze(sampleWithNewData);
Object.freeze(sampleWithRequiredData);
Object.freeze(sampleWithPartialData);
Object.freeze(sampleWithFullData);
