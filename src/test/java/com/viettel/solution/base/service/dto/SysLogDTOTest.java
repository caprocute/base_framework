package com.viettel.solution.base.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import com.viettel.solution.base.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class SysLogDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(SysLogDTO.class);
        SysLogDTO sysLogDTO1 = new SysLogDTO();
        sysLogDTO1.setId("id1");
        SysLogDTO sysLogDTO2 = new SysLogDTO();
        assertThat(sysLogDTO1).isNotEqualTo(sysLogDTO2);
        sysLogDTO2.setId(sysLogDTO1.getId());
        assertThat(sysLogDTO1).isEqualTo(sysLogDTO2);
        sysLogDTO2.setId("id2");
        assertThat(sysLogDTO1).isNotEqualTo(sysLogDTO2);
        sysLogDTO1.setId(null);
        assertThat(sysLogDTO1).isNotEqualTo(sysLogDTO2);
    }
}
