package com.viettel.solution.base.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import com.viettel.solution.base.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class SysDepartDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(SysDepartDTO.class);
        SysDepartDTO sysDepartDTO1 = new SysDepartDTO();
        sysDepartDTO1.setId("id1");
        SysDepartDTO sysDepartDTO2 = new SysDepartDTO();
        assertThat(sysDepartDTO1).isNotEqualTo(sysDepartDTO2);
        sysDepartDTO2.setId(sysDepartDTO1.getId());
        assertThat(sysDepartDTO1).isEqualTo(sysDepartDTO2);
        sysDepartDTO2.setId("id2");
        assertThat(sysDepartDTO1).isNotEqualTo(sysDepartDTO2);
        sysDepartDTO1.setId(null);
        assertThat(sysDepartDTO1).isNotEqualTo(sysDepartDTO2);
    }
}
