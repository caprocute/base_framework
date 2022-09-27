import dayjs from 'dayjs/esm';

import { ISysDataSource, NewSysDataSource } from './sys-data-source.model';

export const sampleWithRequiredData: ISysDataSource = {
  id: 'ed199ab9-8b1c-4012-8bdc-3ca64cd01e58',
};

export const sampleWithPartialData: ISysDataSource = {
  id: 'bbe65978-f717-422b-a834-7eea36748ead',
  name: 'SQL bandwidth',
  dbDriver: 'Direct Awesome',
  dbUrl: 'evolve',
  dbUserName: 'Account up New',
  createBy: 'Card',
  createTime: dayjs('2022-09-27'),
  updateBy: 'Fall XML incentivize',
  updateTime: dayjs('2022-09-27'),
};

export const sampleWithFullData: ISysDataSource = {
  id: 'cc430135-0c46-4272-aa4d-1f4ead9abf68',
  name: 'Guinea-Bissau Administrator',
  remark: 'system',
  dbType: 'best-of-breed',
  dbDriver: 'GB Keyboard',
  dbUrl: 'index Tactics program',
  dbName: 'Gloves',
  dbUserName: 'Overpass Tennessee synthesize',
  dbDrowssap: 'US Tactics',
  createBy: 'collaborative Liaison',
  createTime: dayjs('2022-09-26'),
  updateBy: 'Forint digital',
  updateTime: dayjs('2022-09-26'),
  tenantId: 'enterprise',
};

export const sampleWithNewData: NewSysDataSource = {
  id: null,
};

Object.freeze(sampleWithNewData);
Object.freeze(sampleWithRequiredData);
Object.freeze(sampleWithPartialData);
Object.freeze(sampleWithFullData);
