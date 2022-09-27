import dayjs from 'dayjs/esm';

export interface ISysPosition {
  id: string;
  code?: string | null;
  name?: string | null;
  postRank?: number | null;
  companyId?: string | null;
  sysOrgCode?: string | null;
  createBy?: string | null;
  createTime?: dayjs.Dayjs | null;
  updateBy?: string | null;
  updateTime?: dayjs.Dayjs | null;
  tenantId?: string | null;
}

export type NewSysPosition = Omit<ISysPosition, 'id'> & { id: null };
