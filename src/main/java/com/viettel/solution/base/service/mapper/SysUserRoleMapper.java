package com.viettel.solution.base.service.mapper;

import com.viettel.solution.base.domain.SysUserRole;
import com.viettel.solution.base.service.dto.SysUserRoleDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link SysUserRole} and its DTO {@link SysUserRoleDTO}.
 */
@Mapper(componentModel = "spring")
public interface SysUserRoleMapper extends EntityMapper<SysUserRoleDTO, SysUserRole> {}
