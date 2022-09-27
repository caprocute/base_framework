package com.viettel.solution.base.service.mapper;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class SysDepartRoleMapperTest {

    private SysDepartRoleMapper sysDepartRoleMapper;

    @BeforeEach
    public void setUp() {
        sysDepartRoleMapper = new SysDepartRoleMapperImpl();
    }
}
