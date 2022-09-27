import dayjs from 'dayjs/esm';

import { ISysPermission, NewSysPermission } from './sys-permission.model';

export const sampleWithRequiredData: ISysPermission = {
  id: '85cb154a-14ae-4135-9284-018d4e7769b6',
};

export const sampleWithPartialData: ISysPermission = {
  id: '196a9be0-befa-4611-b915-f525a7cba11a',
  name: 'Bedfordshire Rhode enhance',
  url: 'https://amaya.info',
  redirect: 'Baby Flat Cambridgeshire',
  permsType: 'Assistant Mississippi',
  sortNo: 'leading Practical',
  alwaysShow: false,
  icon: 'programming Fresh driver',
  hideTab: true,
  description: 'Wyoming parsing Refined',
  delFlag: true,
  createBy: 'generating Sudanese bypassing',
};

export const sampleWithFullData: ISysPermission = {
  id: 'ab966309-b978-4056-99f0-9a512a47703d',
  parentId: 'Arkansas',
  name: 'Groves',
  url: 'https://darrion.com',
  component: 'cyan',
  isRoute: false,
  componentName: 'Moroccan interactive',
  redirect: 'azure International Platinum',
  menuType: 5004,
  perms: 'Chief',
  permsType: 'SDD orchestrate engage',
  sortNo: 'Virtual',
  alwaysShow: false,
  icon: 'Interactions',
  isLeaf: false,
  keepAlive: false,
  hidden: false,
  hideTab: true,
  description: 'Agent',
  delFlag: true,
  ruleFLag: false,
  createBy: 'up',
  createTime: dayjs('2022-09-27'),
  updateBy: 'Licensed',
  updateTime: dayjs('2022-09-26'),
  tenantId: 'Wooden Martinique auxiliary',
  internalOrExternal: true,
};

export const sampleWithNewData: NewSysPermission = {
  id: null,
};

Object.freeze(sampleWithNewData);
Object.freeze(sampleWithRequiredData);
Object.freeze(sampleWithPartialData);
Object.freeze(sampleWithFullData);
