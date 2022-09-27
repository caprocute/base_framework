package com.viettel.solution.base.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.viettel.solution.base.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class SysUserDepartTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(SysUserDepart.class);
        SysUserDepart sysUserDepart1 = new SysUserDepart();
        sysUserDepart1.setId("id1");
        SysUserDepart sysUserDepart2 = new SysUserDepart();
        sysUserDepart2.setId(sysUserDepart1.getId());
        assertThat(sysUserDepart1).isEqualTo(sysUserDepart2);
        sysUserDepart2.setId("id2");
        assertThat(sysUserDepart1).isNotEqualTo(sysUserDepart2);
        sysUserDepart1.setId(null);
        assertThat(sysUserDepart1).isNotEqualTo(sysUserDepart2);
    }
}
