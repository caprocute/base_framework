import dayjs from 'dayjs/esm';

import { ISysTenant, NewSysTenant } from './sys-tenant.model';

export const sampleWithRequiredData: ISysTenant = {
  id: '91848a78-ec48-462c-b833-59123ae6266f',
};

export const sampleWithPartialData: ISysTenant = {
  id: '6fe223eb-02fd-4bcd-9014-23687f729683',
  name: 'port',
  status: 40179,
  updateTime: dayjs('2022-09-27'),
  tenantId: 'port Borders',
};

export const sampleWithFullData: ISysTenant = {
  id: '4235c337-c132-43e3-a94b-7a3dac444919',
  name: 'maroon',
  status: 39270,
  createBy: 'Liaison',
  createTime: dayjs('2022-09-27'),
  updateBy: 'bypassing European',
  updateTime: dayjs('2022-09-27'),
  tenantId: 'invoice red',
};

export const sampleWithNewData: NewSysTenant = {
  id: null,
};

Object.freeze(sampleWithNewData);
Object.freeze(sampleWithRequiredData);
Object.freeze(sampleWithPartialData);
Object.freeze(sampleWithFullData);
