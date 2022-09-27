package com.viettel.solution.base.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import com.viettel.solution.base.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class SysUserDepartDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(SysUserDepartDTO.class);
        SysUserDepartDTO sysUserDepartDTO1 = new SysUserDepartDTO();
        sysUserDepartDTO1.setId("id1");
        SysUserDepartDTO sysUserDepartDTO2 = new SysUserDepartDTO();
        assertThat(sysUserDepartDTO1).isNotEqualTo(sysUserDepartDTO2);
        sysUserDepartDTO2.setId(sysUserDepartDTO1.getId());
        assertThat(sysUserDepartDTO1).isEqualTo(sysUserDepartDTO2);
        sysUserDepartDTO2.setId("id2");
        assertThat(sysUserDepartDTO1).isNotEqualTo(sysUserDepartDTO2);
        sysUserDepartDTO1.setId(null);
        assertThat(sysUserDepartDTO1).isNotEqualTo(sysUserDepartDTO2);
    }
}
