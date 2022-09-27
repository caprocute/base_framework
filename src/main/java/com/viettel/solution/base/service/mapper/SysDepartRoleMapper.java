package com.viettel.solution.base.service.mapper;

import com.viettel.solution.base.domain.SysDepartRole;
import com.viettel.solution.base.service.dto.SysDepartRoleDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link SysDepartRole} and its DTO {@link SysDepartRoleDTO}.
 */
@Mapper(componentModel = "spring")
public interface SysDepartRoleMapper extends EntityMapper<SysDepartRoleDTO, SysDepartRole> {}
