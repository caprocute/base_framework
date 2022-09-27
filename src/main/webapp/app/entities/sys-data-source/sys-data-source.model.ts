import dayjs from 'dayjs/esm';

export interface ISysDataSource {
  id: string;
  name?: string | null;
  remark?: string | null;
  dbType?: string | null;
  dbDriver?: string | null;
  dbUrl?: string | null;
  dbName?: string | null;
  dbUserName?: string | null;
  dbDrowssap?: string | null;
  createBy?: string | null;
  createTime?: dayjs.Dayjs | null;
  updateBy?: string | null;
  updateTime?: dayjs.Dayjs | null;
  tenantId?: string | null;
}

export type NewSysDataSource = Omit<ISysDataSource, 'id'> & { id: null };
