export interface ISysUserRole {
  id: string;
  userId?: string | null;
  roleId?: string | null;
}

export type NewSysUserRole = Omit<ISysUserRole, 'id'> & { id: null };
