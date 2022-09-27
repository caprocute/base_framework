import { NgModule } from '@angular/core';
import { SharedModule } from 'app/shared/shared.module';
import { SysPermissionDataRuleComponent } from './list/sys-permission-data-rule.component';
import { SysPermissionDataRuleDetailComponent } from './detail/sys-permission-data-rule-detail.component';
import { SysPermissionDataRuleUpdateComponent } from './update/sys-permission-data-rule-update.component';
import { SysPermissionDataRuleDeleteDialogComponent } from './delete/sys-permission-data-rule-delete-dialog.component';
import { SysPermissionDataRuleRoutingModule } from './route/sys-permission-data-rule-routing.module';

@NgModule({
  imports: [SharedModule, SysPermissionDataRuleRoutingModule],
  declarations: [
    SysPermissionDataRuleComponent,
    SysPermissionDataRuleDetailComponent,
    SysPermissionDataRuleUpdateComponent,
    SysPermissionDataRuleDeleteDialogComponent,
  ],
})
export class SysPermissionDataRuleModule {}
