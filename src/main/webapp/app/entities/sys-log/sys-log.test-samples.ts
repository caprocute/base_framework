import dayjs from 'dayjs/esm';

import { ISysLog, NewSysLog } from './sys-log.model';

export const sampleWithRequiredData: ISysLog = {
  id: '09c711f6-8b26-4f0f-9b35-755ed347db3f',
};

export const sampleWithPartialData: ISysLog = {
  id: 'f62a4155-7fa7-4cfc-a368-80bde564b79a',
  logType: 99579,
  logContent: 'Australian',
  userName: 'redefine Bedfordshire generate',
  requestParam: 'Metal Re-contextualized',
  createBy: 'Buckinghamshire Wooden',
};

export const sampleWithFullData: ISysLog = {
  id: '6a6251df-1d0b-4e6a-ae33-851d38220f9b',
  logType: 47732,
  logContent: 'leverage USB harness',
  operateType: 'indigo Technician Tasty',
  userName: 'Handcrafted',
  ip: 'wireless relationships Programmable',
  method: 'Unbranded Nebraska Minnesota',
  requestUrl: 'Kids Loan Mountain',
  requestParam: 'Oklahoma',
  requestType: 'Licensed',
  costTime: 27891,
  createBy: 'Rue Marketing Rupiah',
  createTime: dayjs('2022-09-26'),
  updateBy: 'scalable Strategist Dynamic',
  updateTime: dayjs('2022-09-26'),
  tenantId: 'Buckinghamshire',
};

export const sampleWithNewData: NewSysLog = {
  id: null,
};

Object.freeze(sampleWithNewData);
Object.freeze(sampleWithRequiredData);
Object.freeze(sampleWithPartialData);
Object.freeze(sampleWithFullData);
