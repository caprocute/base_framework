package com.viettel.solution.base.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.viettel.solution.base.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class SysRolePermissionTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(SysRolePermission.class);
        SysRolePermission sysRolePermission1 = new SysRolePermission();
        sysRolePermission1.setId("id1");
        SysRolePermission sysRolePermission2 = new SysRolePermission();
        sysRolePermission2.setId(sysRolePermission1.getId());
        assertThat(sysRolePermission1).isEqualTo(sysRolePermission2);
        sysRolePermission2.setId("id2");
        assertThat(sysRolePermission1).isNotEqualTo(sysRolePermission2);
        sysRolePermission1.setId(null);
        assertThat(sysRolePermission1).isNotEqualTo(sysRolePermission2);
    }
}
