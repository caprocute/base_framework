import dayjs from 'dayjs/esm';

import { ISysRoleIndex, NewSysRoleIndex } from './sys-role-index.model';

export const sampleWithRequiredData: ISysRoleIndex = {
  id: '1a0a650d-e057-4009-91c8-16f3c3c53f73',
};

export const sampleWithPartialData: ISysRoleIndex = {
  id: '7125c752-de4e-4eb7-95ba-50cbcc5a6cd1',
  component: 'e-commerce',
  createTime: dayjs('2022-09-27'),
  updateBy: 'Bedfordshire',
};

export const sampleWithFullData: ISysRoleIndex = {
  id: '47ab684c-4883-42fd-ad9e-f70c841ef93e',
  roleCode: 'Poland SQL',
  url: 'http://micaela.com',
  component: 'Berkshire supply-chains',
  isRoute: false,
  priority: 46441,
  status: 54544,
  createBy: 'Industrial',
  createTime: dayjs('2022-09-26'),
  updateBy: 'monitor bluetooth',
  updateTime: dayjs('2022-09-26'),
  tenantId: 'systematic Generic system',
};

export const sampleWithNewData: NewSysRoleIndex = {
  id: null,
};

Object.freeze(sampleWithNewData);
Object.freeze(sampleWithRequiredData);
Object.freeze(sampleWithPartialData);
Object.freeze(sampleWithFullData);
