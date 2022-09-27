package com.viettel.solution.base.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.viettel.solution.base.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class SysPermissionDataRuleTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(SysPermissionDataRule.class);
        SysPermissionDataRule sysPermissionDataRule1 = new SysPermissionDataRule();
        sysPermissionDataRule1.setId("id1");
        SysPermissionDataRule sysPermissionDataRule2 = new SysPermissionDataRule();
        sysPermissionDataRule2.setId(sysPermissionDataRule1.getId());
        assertThat(sysPermissionDataRule1).isEqualTo(sysPermissionDataRule2);
        sysPermissionDataRule2.setId("id2");
        assertThat(sysPermissionDataRule1).isNotEqualTo(sysPermissionDataRule2);
        sysPermissionDataRule1.setId(null);
        assertThat(sysPermissionDataRule1).isNotEqualTo(sysPermissionDataRule2);
    }
}
