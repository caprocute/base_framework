package com.viettel.solution.base.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.viettel.solution.base.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class SysDataLogTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(SysDataLog.class);
        SysDataLog sysDataLog1 = new SysDataLog();
        sysDataLog1.setId("id1");
        SysDataLog sysDataLog2 = new SysDataLog();
        sysDataLog2.setId(sysDataLog1.getId());
        assertThat(sysDataLog1).isEqualTo(sysDataLog2);
        sysDataLog2.setId("id2");
        assertThat(sysDataLog1).isNotEqualTo(sysDataLog2);
        sysDataLog1.setId(null);
        assertThat(sysDataLog1).isNotEqualTo(sysDataLog2);
    }
}
