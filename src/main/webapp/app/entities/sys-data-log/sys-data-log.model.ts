import dayjs from 'dayjs/esm';

export interface ISysDataLog {
  id: string;
  dataTable?: string | null;
  dataId?: string | null;
  dataContent?: string | null;
  dataVersion?: number | null;
  createBy?: string | null;
  createTime?: dayjs.Dayjs | null;
  updateBy?: string | null;
  updateTime?: dayjs.Dayjs | null;
  tenantId?: string | null;
}

export type NewSysDataLog = Omit<ISysDataLog, 'id'> & { id: null };
