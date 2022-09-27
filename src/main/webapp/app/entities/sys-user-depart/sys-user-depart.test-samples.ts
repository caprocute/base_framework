import { ISysUserDepart, NewSysUserDepart } from './sys-user-depart.model';

export const sampleWithRequiredData: ISysUserDepart = {
  id: '53703ccb-fc77-4f06-b372-d083d7822870',
};

export const sampleWithPartialData: ISysUserDepart = {
  id: '59e75e44-9138-45a7-9d4d-fe88a76e9a02',
  userId: 'Plastic',
};

export const sampleWithFullData: ISysUserDepart = {
  id: '89345236-8a44-47e5-9431-85c5eab7ce03',
  userId: 'Shoes Account Salad',
  depID: 'monitoring connecting',
};

export const sampleWithNewData: NewSysUserDepart = {
  id: null,
};

Object.freeze(sampleWithNewData);
Object.freeze(sampleWithRequiredData);
Object.freeze(sampleWithPartialData);
Object.freeze(sampleWithFullData);
