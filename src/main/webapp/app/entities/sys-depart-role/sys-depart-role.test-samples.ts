import dayjs from 'dayjs/esm';

import { ISysDepartRole, NewSysDepartRole } from './sys-depart-role.model';

export const sampleWithRequiredData: ISysDepartRole = {
  id: '2bc0e9b7-5cb3-493b-be9e-7695fa2a3d5a',
};

export const sampleWithPartialData: ISysDepartRole = {
  id: 'adde3013-abee-4bd6-b0db-9647da9fefb4',
  departID: 'Somali',
  roleCode: 'Marketing Rubber AGP',
  description: 'Account Towels SQL',
  createBy: 'violet',
  updateBy: 'generate Frozen',
};

export const sampleWithFullData: ISysDepartRole = {
  id: '9f83e5d2-adcf-46ea-81ca-bfa693c087ba',
  departID: 'Berkshire Incredible',
  roleName: 'Product Angola Legacy',
  roleCode: 'architectures Jamahiriya',
  description: 'Arkansas Turkey',
  createBy: 'Frozen redundant',
  createTime: dayjs('2022-09-26'),
  updateBy: 'purple Niue Unbranded',
  updateTime: dayjs('2022-09-26'),
  tenantId: 'application Sao Syrian',
};

export const sampleWithNewData: NewSysDepartRole = {
  id: null,
};

Object.freeze(sampleWithNewData);
Object.freeze(sampleWithRequiredData);
Object.freeze(sampleWithPartialData);
Object.freeze(sampleWithFullData);
