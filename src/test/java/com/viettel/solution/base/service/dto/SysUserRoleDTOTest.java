package com.viettel.solution.base.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import com.viettel.solution.base.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class SysUserRoleDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(SysUserRoleDTO.class);
        SysUserRoleDTO sysUserRoleDTO1 = new SysUserRoleDTO();
        sysUserRoleDTO1.setId("id1");
        SysUserRoleDTO sysUserRoleDTO2 = new SysUserRoleDTO();
        assertThat(sysUserRoleDTO1).isNotEqualTo(sysUserRoleDTO2);
        sysUserRoleDTO2.setId(sysUserRoleDTO1.getId());
        assertThat(sysUserRoleDTO1).isEqualTo(sysUserRoleDTO2);
        sysUserRoleDTO2.setId("id2");
        assertThat(sysUserRoleDTO1).isNotEqualTo(sysUserRoleDTO2);
        sysUserRoleDTO1.setId(null);
        assertThat(sysUserRoleDTO1).isNotEqualTo(sysUserRoleDTO2);
    }
}
