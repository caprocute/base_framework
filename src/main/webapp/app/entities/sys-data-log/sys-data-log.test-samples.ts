import dayjs from 'dayjs/esm';

import { ISysDataLog, NewSysDataLog } from './sys-data-log.model';

export const sampleWithRequiredData: ISysDataLog = {
  id: '7e6db1cd-c478-490f-86b1-ee66f70347b8',
};

export const sampleWithPartialData: ISysDataLog = {
  id: 'bc94a921-bbfc-4439-a9fe-1bd2f37a24d3',
  dataTable: 'Soap',
  dataVersion: 39566,
  createBy: 'Belarussian Soap Designer',
  createTime: dayjs('2022-09-26'),
};

export const sampleWithFullData: ISysDataLog = {
  id: '2a45debf-4951-4a0f-94a1-7e471b26eac8',
  dataTable: 'enhance AGP strategic',
  dataId: 'calculating Regional Technician',
  dataContent: 'Synergized connect',
  dataVersion: 35549,
  createBy: 'e-tailers Missouri Home',
  createTime: dayjs('2022-09-27'),
  updateBy: 'Executive AI olive',
  updateTime: dayjs('2022-09-26'),
  tenantId: 'Corporate Guinea parsing',
};

export const sampleWithNewData: NewSysDataLog = {
  id: null,
};

Object.freeze(sampleWithNewData);
Object.freeze(sampleWithRequiredData);
Object.freeze(sampleWithPartialData);
Object.freeze(sampleWithFullData);
