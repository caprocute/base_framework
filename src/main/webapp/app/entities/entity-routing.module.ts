import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

@NgModule({
  imports: [
    RouterModule.forChild([
      {
        path: 'sys-user',
        data: { pageTitle: 'baseFrameWorkApp.sysUser.home.title' },
        loadChildren: () => import('./sys-user/sys-user.module').then(m => m.SysUserModule),
      },
      {
        path: 'sys-data-log',
        data: { pageTitle: 'baseFrameWorkApp.sysDataLog.home.title' },
        loadChildren: () => import('./sys-data-log/sys-data-log.module').then(m => m.SysDataLogModule),
      },
      {
        path: 'sys-data-source',
        data: { pageTitle: 'baseFrameWorkApp.sysDataSource.home.title' },
        loadChildren: () => import('./sys-data-source/sys-data-source.module').then(m => m.SysDataSourceModule),
      },
      {
        path: 'sys-depart',
        data: { pageTitle: 'baseFrameWorkApp.sysDepart.home.title' },
        loadChildren: () => import('./sys-depart/sys-depart.module').then(m => m.SysDepartModule),
      },
      {
        path: 'sys-depart-role',
        data: { pageTitle: 'baseFrameWorkApp.sysDepartRole.home.title' },
        loadChildren: () => import('./sys-depart-role/sys-depart-role.module').then(m => m.SysDepartRoleModule),
      },
      {
        path: 'sys-log',
        data: { pageTitle: 'baseFrameWorkApp.sysLog.home.title' },
        loadChildren: () => import('./sys-log/sys-log.module').then(m => m.SysLogModule),
      },
      {
        path: 'sys-permission',
        data: { pageTitle: 'baseFrameWorkApp.sysPermission.home.title' },
        loadChildren: () => import('./sys-permission/sys-permission.module').then(m => m.SysPermissionModule),
      },
      {
        path: 'sys-permission-data-rule',
        data: { pageTitle: 'baseFrameWorkApp.sysPermissionDataRule.home.title' },
        loadChildren: () => import('./sys-permission-data-rule/sys-permission-data-rule.module').then(m => m.SysPermissionDataRuleModule),
      },
      {
        path: 'sys-position',
        data: { pageTitle: 'baseFrameWorkApp.sysPosition.home.title' },
        loadChildren: () => import('./sys-position/sys-position.module').then(m => m.SysPositionModule),
      },
      {
        path: 'sys-role-index',
        data: { pageTitle: 'baseFrameWorkApp.sysRoleIndex.home.title' },
        loadChildren: () => import('./sys-role-index/sys-role-index.module').then(m => m.SysRoleIndexModule),
      },
      {
        path: 'sys-role-permission',
        data: { pageTitle: 'baseFrameWorkApp.sysRolePermission.home.title' },
        loadChildren: () => import('./sys-role-permission/sys-role-permission.module').then(m => m.SysRolePermissionModule),
      },
      {
        path: 'sys-tenant',
        data: { pageTitle: 'baseFrameWorkApp.sysTenant.home.title' },
        loadChildren: () => import('./sys-tenant/sys-tenant.module').then(m => m.SysTenantModule),
      },
      {
        path: 'sys-user-depart',
        data: { pageTitle: 'baseFrameWorkApp.sysUserDepart.home.title' },
        loadChildren: () => import('./sys-user-depart/sys-user-depart.module').then(m => m.SysUserDepartModule),
      },
      {
        path: 'sys-user-role',
        data: { pageTitle: 'baseFrameWorkApp.sysUserRole.home.title' },
        loadChildren: () => import('./sys-user-role/sys-user-role.module').then(m => m.SysUserRoleModule),
      },
      /* jhipster-needle-add-entity-route - JHipster will add entity modules routes here */
    ]),
  ],
})
export class EntityRoutingModule {}
