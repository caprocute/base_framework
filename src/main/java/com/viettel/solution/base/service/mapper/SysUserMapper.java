package com.viettel.solution.base.service.mapper;

import com.viettel.solution.base.domain.SysUser;
import com.viettel.solution.base.service.dto.SysUserDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link SysUser} and its DTO {@link SysUserDTO}.
 */
@Mapper(componentModel = "spring")
public interface SysUserMapper extends EntityMapper<SysUserDTO, SysUser> {}
