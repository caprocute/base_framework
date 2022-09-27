package com.viettel.solution.base.service.mapper;

import com.viettel.solution.base.domain.SysDataSource;
import com.viettel.solution.base.service.dto.SysDataSourceDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link SysDataSource} and its DTO {@link SysDataSourceDTO}.
 */
@Mapper(componentModel = "spring")
public interface SysDataSourceMapper extends EntityMapper<SysDataSourceDTO, SysDataSource> {}
