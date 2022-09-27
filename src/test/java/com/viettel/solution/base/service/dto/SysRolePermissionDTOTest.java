package com.viettel.solution.base.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import com.viettel.solution.base.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class SysRolePermissionDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(SysRolePermissionDTO.class);
        SysRolePermissionDTO sysRolePermissionDTO1 = new SysRolePermissionDTO();
        sysRolePermissionDTO1.setId("id1");
        SysRolePermissionDTO sysRolePermissionDTO2 = new SysRolePermissionDTO();
        assertThat(sysRolePermissionDTO1).isNotEqualTo(sysRolePermissionDTO2);
        sysRolePermissionDTO2.setId(sysRolePermissionDTO1.getId());
        assertThat(sysRolePermissionDTO1).isEqualTo(sysRolePermissionDTO2);
        sysRolePermissionDTO2.setId("id2");
        assertThat(sysRolePermissionDTO1).isNotEqualTo(sysRolePermissionDTO2);
        sysRolePermissionDTO1.setId(null);
        assertThat(sysRolePermissionDTO1).isNotEqualTo(sysRolePermissionDTO2);
    }
}
