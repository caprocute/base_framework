package com.viettel.solution.base.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.viettel.solution.base.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class SysPositionTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(SysPosition.class);
        SysPosition sysPosition1 = new SysPosition();
        sysPosition1.setId("id1");
        SysPosition sysPosition2 = new SysPosition();
        sysPosition2.setId(sysPosition1.getId());
        assertThat(sysPosition1).isEqualTo(sysPosition2);
        sysPosition2.setId("id2");
        assertThat(sysPosition1).isNotEqualTo(sysPosition2);
        sysPosition1.setId(null);
        assertThat(sysPosition1).isNotEqualTo(sysPosition2);
    }
}
