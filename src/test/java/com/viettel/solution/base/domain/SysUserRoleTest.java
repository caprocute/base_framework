package com.viettel.solution.base.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.viettel.solution.base.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class SysUserRoleTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(SysUserRole.class);
        SysUserRole sysUserRole1 = new SysUserRole();
        sysUserRole1.setId("id1");
        SysUserRole sysUserRole2 = new SysUserRole();
        sysUserRole2.setId(sysUserRole1.getId());
        assertThat(sysUserRole1).isEqualTo(sysUserRole2);
        sysUserRole2.setId("id2");
        assertThat(sysUserRole1).isNotEqualTo(sysUserRole2);
        sysUserRole1.setId(null);
        assertThat(sysUserRole1).isNotEqualTo(sysUserRole2);
    }
}
