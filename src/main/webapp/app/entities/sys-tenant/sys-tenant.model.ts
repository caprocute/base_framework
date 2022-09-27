import dayjs from 'dayjs/esm';

export interface ISysTenant {
  id: string;
  name?: string | null;
  status?: number | null;
  createBy?: string | null;
  createTime?: dayjs.Dayjs | null;
  updateBy?: string | null;
  updateTime?: dayjs.Dayjs | null;
  tenantId?: string | null;
}

export type NewSysTenant = Omit<ISysTenant, 'id'> & { id: null };
