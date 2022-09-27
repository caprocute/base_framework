import dayjs from 'dayjs/esm';

export interface ISysDepartRole {
  id: string;
  departID?: string | null;
  roleName?: string | null;
  roleCode?: string | null;
  description?: string | null;
  createBy?: string | null;
  createTime?: dayjs.Dayjs | null;
  updateBy?: string | null;
  updateTime?: dayjs.Dayjs | null;
  tenantId?: string | null;
}

export type NewSysDepartRole = Omit<ISysDepartRole, 'id'> & { id: null };
