package com.viettel.solution.base.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import com.viettel.solution.base.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class SysDepartRoleDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(SysDepartRoleDTO.class);
        SysDepartRoleDTO sysDepartRoleDTO1 = new SysDepartRoleDTO();
        sysDepartRoleDTO1.setId("id1");
        SysDepartRoleDTO sysDepartRoleDTO2 = new SysDepartRoleDTO();
        assertThat(sysDepartRoleDTO1).isNotEqualTo(sysDepartRoleDTO2);
        sysDepartRoleDTO2.setId(sysDepartRoleDTO1.getId());
        assertThat(sysDepartRoleDTO1).isEqualTo(sysDepartRoleDTO2);
        sysDepartRoleDTO2.setId("id2");
        assertThat(sysDepartRoleDTO1).isNotEqualTo(sysDepartRoleDTO2);
        sysDepartRoleDTO1.setId(null);
        assertThat(sysDepartRoleDTO1).isNotEqualTo(sysDepartRoleDTO2);
    }
}
