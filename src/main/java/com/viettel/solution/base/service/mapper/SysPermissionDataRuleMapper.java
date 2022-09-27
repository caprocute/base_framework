package com.viettel.solution.base.service.mapper;

import com.viettel.solution.base.domain.SysPermissionDataRule;
import com.viettel.solution.base.service.dto.SysPermissionDataRuleDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link SysPermissionDataRule} and its DTO {@link SysPermissionDataRuleDTO}.
 */
@Mapper(componentModel = "spring")
public interface SysPermissionDataRuleMapper extends EntityMapper<SysPermissionDataRuleDTO, SysPermissionDataRule> {}
