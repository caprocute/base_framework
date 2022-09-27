package com.viettel.solution.base.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import com.viettel.solution.base.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class SysRoleIndexDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(SysRoleIndexDTO.class);
        SysRoleIndexDTO sysRoleIndexDTO1 = new SysRoleIndexDTO();
        sysRoleIndexDTO1.setId("id1");
        SysRoleIndexDTO sysRoleIndexDTO2 = new SysRoleIndexDTO();
        assertThat(sysRoleIndexDTO1).isNotEqualTo(sysRoleIndexDTO2);
        sysRoleIndexDTO2.setId(sysRoleIndexDTO1.getId());
        assertThat(sysRoleIndexDTO1).isEqualTo(sysRoleIndexDTO2);
        sysRoleIndexDTO2.setId("id2");
        assertThat(sysRoleIndexDTO1).isNotEqualTo(sysRoleIndexDTO2);
        sysRoleIndexDTO1.setId(null);
        assertThat(sysRoleIndexDTO1).isNotEqualTo(sysRoleIndexDTO2);
    }
}
