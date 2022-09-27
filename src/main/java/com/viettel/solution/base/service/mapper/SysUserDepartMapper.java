package com.viettel.solution.base.service.mapper;

import com.viettel.solution.base.domain.SysUserDepart;
import com.viettel.solution.base.service.dto.SysUserDepartDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link SysUserDepart} and its DTO {@link SysUserDepartDTO}.
 */
@Mapper(componentModel = "spring")
public interface SysUserDepartMapper extends EntityMapper<SysUserDepartDTO, SysUserDepart> {}
