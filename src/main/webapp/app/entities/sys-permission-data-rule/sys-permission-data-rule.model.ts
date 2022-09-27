import dayjs from 'dayjs/esm';

export interface ISysPermissionDataRule {
  id: string;
  permissionId?: string | null;
  ruleName?: string | null;
  ruleColumn?: string | null;
  ruleCOnditions?: string | null;
  ruleValue?: string | null;
  status?: number | null;
  createBy?: string | null;
  createTime?: dayjs.Dayjs | null;
  updateBy?: string | null;
  updateTime?: dayjs.Dayjs | null;
  tenantId?: string | null;
}

export type NewSysPermissionDataRule = Omit<ISysPermissionDataRule, 'id'> & { id: null };
