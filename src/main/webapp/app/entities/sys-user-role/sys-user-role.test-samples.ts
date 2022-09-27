import { ISysUserRole, NewSysUserRole } from './sys-user-role.model';

export const sampleWithRequiredData: ISysUserRole = {
  id: '113cd2af-b618-4e1c-8ea6-3768a258f091',
};

export const sampleWithPartialData: ISysUserRole = {
  id: 'b8b420a6-739b-40d9-8e7e-354024219602',
  roleId: 'Incredible Architect',
};

export const sampleWithFullData: ISysUserRole = {
  id: '358e2553-6e2b-4e66-94d2-b94170711756',
  userId: 'interface Developer',
  roleId: 'transitional Investor',
};

export const sampleWithNewData: NewSysUserRole = {
  id: null,
};

Object.freeze(sampleWithNewData);
Object.freeze(sampleWithRequiredData);
Object.freeze(sampleWithPartialData);
Object.freeze(sampleWithFullData);
