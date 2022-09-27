package com.viettel.solution.base.service.mapper;

import com.viettel.solution.base.domain.SysTenant;
import com.viettel.solution.base.service.dto.SysTenantDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link SysTenant} and its DTO {@link SysTenantDTO}.
 */
@Mapper(componentModel = "spring")
public interface SysTenantMapper extends EntityMapper<SysTenantDTO, SysTenant> {}
