package com.viettel.solution.base.service.mapper;

import com.viettel.solution.base.domain.SysDataLog;
import com.viettel.solution.base.service.dto.SysDataLogDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link SysDataLog} and its DTO {@link SysDataLogDTO}.
 */
@Mapper(componentModel = "spring")
public interface SysDataLogMapper extends EntityMapper<SysDataLogDTO, SysDataLog> {}
