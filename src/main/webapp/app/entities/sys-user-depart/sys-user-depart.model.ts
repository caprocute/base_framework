export interface ISysUserDepart {
  id: string;
  userId?: string | null;
  depID?: string | null;
}

export type NewSysUserDepart = Omit<ISysUserDepart, 'id'> & { id: null };
