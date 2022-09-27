import dayjs from 'dayjs/esm';

export interface ISysPermission {
  id: string;
  parentId?: string | null;
  name?: string | null;
  url?: string | null;
  component?: string | null;
  isRoute?: boolean | null;
  componentName?: string | null;
  redirect?: string | null;
  menuType?: number | null;
  perms?: string | null;
  permsType?: string | null;
  sortNo?: string | null;
  alwaysShow?: boolean | null;
  icon?: string | null;
  isLeaf?: boolean | null;
  keepAlive?: boolean | null;
  hidden?: boolean | null;
  hideTab?: boolean | null;
  description?: string | null;
  delFlag?: boolean | null;
  ruleFLag?: boolean | null;
  createBy?: string | null;
  createTime?: dayjs.Dayjs | null;
  updateBy?: string | null;
  updateTime?: dayjs.Dayjs | null;
  tenantId?: string | null;
  internalOrExternal?: boolean | null;
}

export type NewSysPermission = Omit<ISysPermission, 'id'> & { id: null };
