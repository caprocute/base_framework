package com.viettel.solution.base.service.mapper;

import com.viettel.solution.base.domain.SysLog;
import com.viettel.solution.base.service.dto.SysLogDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link SysLog} and its DTO {@link SysLogDTO}.
 */
@Mapper(componentModel = "spring")
public interface SysLogMapper extends EntityMapper<SysLogDTO, SysLog> {}
