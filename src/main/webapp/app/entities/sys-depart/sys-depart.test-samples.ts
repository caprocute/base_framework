import dayjs from 'dayjs/esm';

import { ISysDepart, NewSysDepart } from './sys-depart.model';

export const sampleWithRequiredData: ISysDepart = {
  id: 'a27fce27-616a-4348-8fa7-517671264d9c',
  departName: 'reintermediate Circle',
  orgCategory: 'Account',
  orgCode: 'Devolved Balboa Bedfordshire',
};

export const sampleWithPartialData: ISysDepart = {
  id: 'ed32d9ba-da93-459d-8361-91bb4086d10f',
  departName: 'Buckinghamshire Cambridgeshire uniform',
  departOrder: 46737,
  orgCategory: 'Digitized optical Chips',
  orgCode: 'Unbranded',
  mobile: 'innovative',
  fax: 'Planner Pound',
  address: 'technologies De-engineered',
  status: false,
  createBy: 'Licensed capability',
  createTime: dayjs('2022-09-26'),
  updateBy: 'Toys',
};

export const sampleWithFullData: ISysDepart = {
  id: 'df40c02d-840e-4eec-86e8-b75df41b8070',
  parentId: 'Dynamic',
  departName: 'strategize overriding',
  departOrder: 78158,
  orgCategory: 'Customer Practical hack',
  orgType: 'Bike',
  orgCode: 'International Marketing',
  mobile: 'connect',
  fax: 'North service-desk gold',
  address: 'Toys Reverse-engineered Manager',
  memo: 'Fresh markets Sheqel',
  status: true,
  delFlag: false,
  createBy: 'connecting Product Programmable',
  createTime: dayjs('2022-09-26'),
  updateBy: 'IB Loan',
  updateTime: dayjs('2022-09-26'),
  tenantId: 'alarm Loan',
};

export const sampleWithNewData: NewSysDepart = {
  departName: 'Assistant',
  orgCategory: 'invoice',
  orgCode: 'bus connect Nebraska',
  id: null,
};

Object.freeze(sampleWithNewData);
Object.freeze(sampleWithRequiredData);
Object.freeze(sampleWithPartialData);
Object.freeze(sampleWithFullData);
