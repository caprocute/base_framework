package com.viettel.solution.base.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import com.viettel.solution.base.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class SysPositionDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(SysPositionDTO.class);
        SysPositionDTO sysPositionDTO1 = new SysPositionDTO();
        sysPositionDTO1.setId("id1");
        SysPositionDTO sysPositionDTO2 = new SysPositionDTO();
        assertThat(sysPositionDTO1).isNotEqualTo(sysPositionDTO2);
        sysPositionDTO2.setId(sysPositionDTO1.getId());
        assertThat(sysPositionDTO1).isEqualTo(sysPositionDTO2);
        sysPositionDTO2.setId("id2");
        assertThat(sysPositionDTO1).isNotEqualTo(sysPositionDTO2);
        sysPositionDTO1.setId(null);
        assertThat(sysPositionDTO1).isNotEqualTo(sysPositionDTO2);
    }
}
