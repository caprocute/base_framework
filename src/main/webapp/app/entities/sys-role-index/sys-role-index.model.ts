import dayjs from 'dayjs/esm';

export interface ISysRoleIndex {
  id: string;
  roleCode?: string | null;
  url?: string | null;
  component?: string | null;
  isRoute?: boolean | null;
  priority?: number | null;
  status?: number | null;
  createBy?: string | null;
  createTime?: dayjs.Dayjs | null;
  updateBy?: string | null;
  updateTime?: dayjs.Dayjs | null;
  tenantId?: string | null;
}

export type NewSysRoleIndex = Omit<ISysRoleIndex, 'id'> & { id: null };
