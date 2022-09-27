package com.viettel.solution.base.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import com.viettel.solution.base.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class SysPermissionDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(SysPermissionDTO.class);
        SysPermissionDTO sysPermissionDTO1 = new SysPermissionDTO();
        sysPermissionDTO1.setId("id1");
        SysPermissionDTO sysPermissionDTO2 = new SysPermissionDTO();
        assertThat(sysPermissionDTO1).isNotEqualTo(sysPermissionDTO2);
        sysPermissionDTO2.setId(sysPermissionDTO1.getId());
        assertThat(sysPermissionDTO1).isEqualTo(sysPermissionDTO2);
        sysPermissionDTO2.setId("id2");
        assertThat(sysPermissionDTO1).isNotEqualTo(sysPermissionDTO2);
        sysPermissionDTO1.setId(null);
        assertThat(sysPermissionDTO1).isNotEqualTo(sysPermissionDTO2);
    }
}
