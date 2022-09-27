package com.viettel.solution.base.service.mapper;

import com.viettel.solution.base.domain.SysPosition;
import com.viettel.solution.base.service.dto.SysPositionDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link SysPosition} and its DTO {@link SysPositionDTO}.
 */
@Mapper(componentModel = "spring")
public interface SysPositionMapper extends EntityMapper<SysPositionDTO, SysPosition> {}
