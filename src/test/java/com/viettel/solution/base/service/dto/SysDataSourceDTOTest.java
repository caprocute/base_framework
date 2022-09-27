package com.viettel.solution.base.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import com.viettel.solution.base.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class SysDataSourceDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(SysDataSourceDTO.class);
        SysDataSourceDTO sysDataSourceDTO1 = new SysDataSourceDTO();
        sysDataSourceDTO1.setId("id1");
        SysDataSourceDTO sysDataSourceDTO2 = new SysDataSourceDTO();
        assertThat(sysDataSourceDTO1).isNotEqualTo(sysDataSourceDTO2);
        sysDataSourceDTO2.setId(sysDataSourceDTO1.getId());
        assertThat(sysDataSourceDTO1).isEqualTo(sysDataSourceDTO2);
        sysDataSourceDTO2.setId("id2");
        assertThat(sysDataSourceDTO1).isNotEqualTo(sysDataSourceDTO2);
        sysDataSourceDTO1.setId(null);
        assertThat(sysDataSourceDTO1).isNotEqualTo(sysDataSourceDTO2);
    }
}
