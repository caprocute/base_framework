package com.viettel.solution.base.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import com.viettel.solution.base.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class SysTenantDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(SysTenantDTO.class);
        SysTenantDTO sysTenantDTO1 = new SysTenantDTO();
        sysTenantDTO1.setId("id1");
        SysTenantDTO sysTenantDTO2 = new SysTenantDTO();
        assertThat(sysTenantDTO1).isNotEqualTo(sysTenantDTO2);
        sysTenantDTO2.setId(sysTenantDTO1.getId());
        assertThat(sysTenantDTO1).isEqualTo(sysTenantDTO2);
        sysTenantDTO2.setId("id2");
        assertThat(sysTenantDTO1).isNotEqualTo(sysTenantDTO2);
        sysTenantDTO1.setId(null);
        assertThat(sysTenantDTO1).isNotEqualTo(sysTenantDTO2);
    }
}
