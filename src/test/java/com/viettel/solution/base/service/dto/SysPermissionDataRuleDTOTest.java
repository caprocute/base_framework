package com.viettel.solution.base.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import com.viettel.solution.base.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class SysPermissionDataRuleDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(SysPermissionDataRuleDTO.class);
        SysPermissionDataRuleDTO sysPermissionDataRuleDTO1 = new SysPermissionDataRuleDTO();
        sysPermissionDataRuleDTO1.setId("id1");
        SysPermissionDataRuleDTO sysPermissionDataRuleDTO2 = new SysPermissionDataRuleDTO();
        assertThat(sysPermissionDataRuleDTO1).isNotEqualTo(sysPermissionDataRuleDTO2);
        sysPermissionDataRuleDTO2.setId(sysPermissionDataRuleDTO1.getId());
        assertThat(sysPermissionDataRuleDTO1).isEqualTo(sysPermissionDataRuleDTO2);
        sysPermissionDataRuleDTO2.setId("id2");
        assertThat(sysPermissionDataRuleDTO1).isNotEqualTo(sysPermissionDataRuleDTO2);
        sysPermissionDataRuleDTO1.setId(null);
        assertThat(sysPermissionDataRuleDTO1).isNotEqualTo(sysPermissionDataRuleDTO2);
    }
}
