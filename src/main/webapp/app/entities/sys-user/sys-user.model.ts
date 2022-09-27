import dayjs from 'dayjs/esm';

export interface ISysUser {
  id: string;
  userName?: string | null;
  realName?: string | null;
  drowSsap?: string | null;
  salt?: string | null;
  avatar?: string | null;
  birthday?: dayjs.Dayjs | null;
  sex?: number | null;
  email?: string | null;
  phone?: string | null;
  orgCode?: string | null;
  status?: number | null;
  delFlag?: number | null;
  thirdId?: string | null;
  thirdType?: string | null;
  activitySync?: number | null;
  workNo?: string | null;
  post?: string | null;
  telephone?: string | null;
  createBy?: string | null;
  createTime?: dayjs.Dayjs | null;
  updateBy?: string | null;
  updateTime?: dayjs.Dayjs | null;
  userIdentity?: number | null;
  departIds?: string | null;
  relTenantIds?: string | null;
  clientId?: string | null;
}

export type NewSysUser = Omit<ISysUser, 'id'> & { id: null };
