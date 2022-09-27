package com.viettel.solution.base.service.mapper;

import com.viettel.solution.base.domain.SysDepart;
import com.viettel.solution.base.service.dto.SysDepartDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link SysDepart} and its DTO {@link SysDepartDTO}.
 */
@Mapper(componentModel = "spring")
public interface SysDepartMapper extends EntityMapper<SysDepartDTO, SysDepart> {}
