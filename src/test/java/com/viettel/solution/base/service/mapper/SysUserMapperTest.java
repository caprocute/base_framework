package com.viettel.solution.base.service.mapper;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class SysUserMapperTest {

    private SysUserMapper sysUserMapper;

    @BeforeEach
    public void setUp() {
        sysUserMapper = new SysUserMapperImpl();
    }
}
