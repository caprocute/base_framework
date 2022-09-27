package com.viettel.solution.base.service.mapper;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class SysDataLogMapperTest {

    private SysDataLogMapper sysDataLogMapper;

    @BeforeEach
    public void setUp() {
        sysDataLogMapper = new SysDataLogMapperImpl();
    }
}
