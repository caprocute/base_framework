package com.viettel.solution.base.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import com.viettel.solution.base.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class SysUserDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(SysUserDTO.class);
        SysUserDTO sysUserDTO1 = new SysUserDTO();
        sysUserDTO1.setId("id1");
        SysUserDTO sysUserDTO2 = new SysUserDTO();
        assertThat(sysUserDTO1).isNotEqualTo(sysUserDTO2);
        sysUserDTO2.setId(sysUserDTO1.getId());
        assertThat(sysUserDTO1).isEqualTo(sysUserDTO2);
        sysUserDTO2.setId("id2");
        assertThat(sysUserDTO1).isNotEqualTo(sysUserDTO2);
        sysUserDTO1.setId(null);
        assertThat(sysUserDTO1).isNotEqualTo(sysUserDTO2);
    }
}
