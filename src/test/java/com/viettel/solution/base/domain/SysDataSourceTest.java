package com.viettel.solution.base.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.viettel.solution.base.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class SysDataSourceTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(SysDataSource.class);
        SysDataSource sysDataSource1 = new SysDataSource();
        sysDataSource1.setId("id1");
        SysDataSource sysDataSource2 = new SysDataSource();
        sysDataSource2.setId(sysDataSource1.getId());
        assertThat(sysDataSource1).isEqualTo(sysDataSource2);
        sysDataSource2.setId("id2");
        assertThat(sysDataSource1).isNotEqualTo(sysDataSource2);
        sysDataSource1.setId(null);
        assertThat(sysDataSource1).isNotEqualTo(sysDataSource2);
    }
}
