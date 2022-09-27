package com.viettel.solution.base.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.viettel.solution.base.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class SysPermissionTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(SysPermission.class);
        SysPermission sysPermission1 = new SysPermission();
        sysPermission1.setId("id1");
        SysPermission sysPermission2 = new SysPermission();
        sysPermission2.setId(sysPermission1.getId());
        assertThat(sysPermission1).isEqualTo(sysPermission2);
        sysPermission2.setId("id2");
        assertThat(sysPermission1).isNotEqualTo(sysPermission2);
        sysPermission1.setId(null);
        assertThat(sysPermission1).isNotEqualTo(sysPermission2);
    }
}
