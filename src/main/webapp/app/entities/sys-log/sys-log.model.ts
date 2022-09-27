import dayjs from 'dayjs/esm';

export interface ISysLog {
  id: string;
  logType?: number | null;
  logContent?: string | null;
  operateType?: string | null;
  userName?: string | null;
  ip?: string | null;
  method?: string | null;
  requestUrl?: string | null;
  requestParam?: string | null;
  requestType?: string | null;
  costTime?: number | null;
  createBy?: string | null;
  createTime?: dayjs.Dayjs | null;
  updateBy?: string | null;
  updateTime?: dayjs.Dayjs | null;
  tenantId?: string | null;
}

export type NewSysLog = Omit<ISysLog, 'id'> & { id: null };
