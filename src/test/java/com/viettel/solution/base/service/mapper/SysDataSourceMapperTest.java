package com.viettel.solution.base.service.mapper;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class SysDataSourceMapperTest {

    private SysDataSourceMapper sysDataSourceMapper;

    @BeforeEach
    public void setUp() {
        sysDataSourceMapper = new SysDataSourceMapperImpl();
    }
}
