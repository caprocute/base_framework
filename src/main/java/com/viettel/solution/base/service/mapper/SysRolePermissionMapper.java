package com.viettel.solution.base.service.mapper;

import com.viettel.solution.base.domain.SysRolePermission;
import com.viettel.solution.base.service.dto.SysRolePermissionDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link SysRolePermission} and its DTO {@link SysRolePermissionDTO}.
 */
@Mapper(componentModel = "spring")
public interface SysRolePermissionMapper extends EntityMapper<SysRolePermissionDTO, SysRolePermission> {}
