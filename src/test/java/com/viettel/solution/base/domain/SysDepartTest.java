package com.viettel.solution.base.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.viettel.solution.base.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class SysDepartTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(SysDepart.class);
        SysDepart sysDepart1 = new SysDepart();
        sysDepart1.setId("id1");
        SysDepart sysDepart2 = new SysDepart();
        sysDepart2.setId(sysDepart1.getId());
        assertThat(sysDepart1).isEqualTo(sysDepart2);
        sysDepart2.setId("id2");
        assertThat(sysDepart1).isNotEqualTo(sysDepart2);
        sysDepart1.setId(null);
        assertThat(sysDepart1).isNotEqualTo(sysDepart2);
    }
}
