package com.viettel.solution.base.service.mapper;

import com.viettel.solution.base.domain.SysRoleIndex;
import com.viettel.solution.base.service.dto.SysRoleIndexDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link SysRoleIndex} and its DTO {@link SysRoleIndexDTO}.
 */
@Mapper(componentModel = "spring")
public interface SysRoleIndexMapper extends EntityMapper<SysRoleIndexDTO, SysRoleIndex> {}
