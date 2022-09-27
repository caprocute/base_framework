import dayjs from 'dayjs/esm';

import { ISysPosition, NewSysPosition } from './sys-position.model';

export const sampleWithRequiredData: ISysPosition = {
  id: '51fb5c90-d8c8-4de4-918a-0cd125103b48',
};

export const sampleWithPartialData: ISysPosition = {
  id: '325ce398-40c6-4562-8125-4531e4ba4c48',
  name: 'mobile',
  createBy: 'Investment Licensed',
  createTime: dayjs('2022-09-26'),
  updateBy: 'Multi-tiered',
};

export const sampleWithFullData: ISysPosition = {
  id: '1b607c5b-5934-4b26-b7e8-7eb3db618a47',
  code: 'Cambridgeshire',
  name: 'wireless',
  postRank: 93318,
  companyId: 'Health',
  sysOrgCode: 'purple Incredible',
  createBy: 'Synergized',
  createTime: dayjs('2022-09-26'),
  updateBy: 'seamless',
  updateTime: dayjs('2022-09-26'),
  tenantId: 'Steel Cambodia Pound',
};

export const sampleWithNewData: NewSysPosition = {
  id: null,
};

Object.freeze(sampleWithNewData);
Object.freeze(sampleWithRequiredData);
Object.freeze(sampleWithPartialData);
Object.freeze(sampleWithFullData);
