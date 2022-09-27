package com.viettel.solution.base.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import com.viettel.solution.base.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class SysDataLogDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(SysDataLogDTO.class);
        SysDataLogDTO sysDataLogDTO1 = new SysDataLogDTO();
        sysDataLogDTO1.setId("id1");
        SysDataLogDTO sysDataLogDTO2 = new SysDataLogDTO();
        assertThat(sysDataLogDTO1).isNotEqualTo(sysDataLogDTO2);
        sysDataLogDTO2.setId(sysDataLogDTO1.getId());
        assertThat(sysDataLogDTO1).isEqualTo(sysDataLogDTO2);
        sysDataLogDTO2.setId("id2");
        assertThat(sysDataLogDTO1).isNotEqualTo(sysDataLogDTO2);
        sysDataLogDTO1.setId(null);
        assertThat(sysDataLogDTO1).isNotEqualTo(sysDataLogDTO2);
    }
}
