import dayjs from 'dayjs/esm';

export interface ISysRolePermission {
  id: string;
  roleId?: string | null;
  permissionId?: string | null;
  dataRuleIds?: string | null;
  operateDate?: dayjs.Dayjs | null;
  operateIp?: string | null;
}

export type NewSysRolePermission = Omit<ISysRolePermission, 'id'> & { id: null };
