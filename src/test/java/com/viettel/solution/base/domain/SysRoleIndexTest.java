package com.viettel.solution.base.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.viettel.solution.base.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class SysRoleIndexTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(SysRoleIndex.class);
        SysRoleIndex sysRoleIndex1 = new SysRoleIndex();
        sysRoleIndex1.setId("id1");
        SysRoleIndex sysRoleIndex2 = new SysRoleIndex();
        sysRoleIndex2.setId(sysRoleIndex1.getId());
        assertThat(sysRoleIndex1).isEqualTo(sysRoleIndex2);
        sysRoleIndex2.setId("id2");
        assertThat(sysRoleIndex1).isNotEqualTo(sysRoleIndex2);
        sysRoleIndex1.setId(null);
        assertThat(sysRoleIndex1).isNotEqualTo(sysRoleIndex2);
    }
}
