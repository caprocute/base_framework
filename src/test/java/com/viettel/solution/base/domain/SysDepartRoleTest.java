package com.viettel.solution.base.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.viettel.solution.base.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class SysDepartRoleTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(SysDepartRole.class);
        SysDepartRole sysDepartRole1 = new SysDepartRole();
        sysDepartRole1.setId("id1");
        SysDepartRole sysDepartRole2 = new SysDepartRole();
        sysDepartRole2.setId(sysDepartRole1.getId());
        assertThat(sysDepartRole1).isEqualTo(sysDepartRole2);
        sysDepartRole2.setId("id2");
        assertThat(sysDepartRole1).isNotEqualTo(sysDepartRole2);
        sysDepartRole1.setId(null);
        assertThat(sysDepartRole1).isNotEqualTo(sysDepartRole2);
    }
}
