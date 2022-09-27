import dayjs from 'dayjs/esm';

export interface ISysDepart {
  id: string;
  parentId?: string | null;
  departName?: string | null;
  departOrder?: number | null;
  orgCategory?: string | null;
  orgType?: string | null;
  orgCode?: string | null;
  mobile?: string | null;
  fax?: string | null;
  address?: string | null;
  memo?: string | null;
  status?: boolean | null;
  delFlag?: boolean | null;
  createBy?: string | null;
  createTime?: dayjs.Dayjs | null;
  updateBy?: string | null;
  updateTime?: dayjs.Dayjs | null;
  tenantId?: string | null;
}

export type NewSysDepart = Omit<ISysDepart, 'id'> & { id: null };
