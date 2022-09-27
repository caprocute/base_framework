package com.viettel.solution.base.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.viettel.solution.base.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class SysTenantTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(SysTenant.class);
        SysTenant sysTenant1 = new SysTenant();
        sysTenant1.setId("id1");
        SysTenant sysTenant2 = new SysTenant();
        sysTenant2.setId(sysTenant1.getId());
        assertThat(sysTenant1).isEqualTo(sysTenant2);
        sysTenant2.setId("id2");
        assertThat(sysTenant1).isNotEqualTo(sysTenant2);
        sysTenant1.setId(null);
        assertThat(sysTenant1).isNotEqualTo(sysTenant2);
    }
}
