package com.viettel.solution.base.service.mapper;

import com.viettel.solution.base.domain.SysPermission;
import com.viettel.solution.base.service.dto.SysPermissionDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link SysPermission} and its DTO {@link SysPermissionDTO}.
 */
@Mapper(componentModel = "spring")
public interface SysPermissionMapper extends EntityMapper<SysPermissionDTO, SysPermission> {}
