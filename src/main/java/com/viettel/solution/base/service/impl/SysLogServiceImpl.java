package com.viettel.solution.base.service.impl;

import com.viettel.solution.base.domain.SysLog;
import com.viettel.solution.base.repository.SysLogRepository;
import com.viettel.solution.base.service.SysLogService;
import com.viettel.solution.base.service.dto.SysLogDTO;
import com.viettel.solution.base.service.mapper.SysLogMapper;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link SysLog}.
 */
@Service
@Transactional
public class SysLogServiceImpl implements SysLogService {

    private final Logger log = LoggerFactory.getLogger(SysLogServiceImpl.class);

    private final SysLogRepository sysLogRepository;

    private final SysLogMapper sysLogMapper;

    public SysLogServiceImpl(SysLogRepository sysLogRepository, SysLogMapper sysLogMapper) {
        this.sysLogRepository = sysLogRepository;
        this.sysLogMapper = sysLogMapper;
    }

    @Override
    public SysLogDTO save(SysLogDTO sysLogDTO) {
        log.debug("Request to save SysLog : {}", sysLogDTO);
        SysLog sysLog = sysLogMapper.toEntity(sysLogDTO);
        sysLog = sysLogRepository.save(sysLog);
        return sysLogMapper.toDto(sysLog);
    }

    @Override
    public SysLogDTO update(SysLogDTO sysLogDTO) {
        log.debug("Request to update SysLog : {}", sysLogDTO);
        SysLog sysLog = sysLogMapper.toEntity(sysLogDTO);
        sysLog.setIsPersisted();
        sysLog = sysLogRepository.save(sysLog);
        return sysLogMapper.toDto(sysLog);
    }

    @Override
    public Optional<SysLogDTO> partialUpdate(SysLogDTO sysLogDTO) {
        log.debug("Request to partially update SysLog : {}", sysLogDTO);

        return sysLogRepository
            .findById(sysLogDTO.getId())
            .map(existingSysLog -> {
                sysLogMapper.partialUpdate(existingSysLog, sysLogDTO);

                return existingSysLog;
            })
            .map(sysLogRepository::save)
            .map(sysLogMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<SysLogDTO> findAll(Pageable pageable) {
        log.debug("Request to get all SysLogs");
        return sysLogRepository.findAll(pageable).map(sysLogMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<SysLogDTO> findOne(String id) {
        log.debug("Request to get SysLog : {}", id);
        return sysLogRepository.findById(id).map(sysLogMapper::toDto);
    }

    @Override
    public void delete(String id) {
        log.debug("Request to delete SysLog : {}", id);
        sysLogRepository.deleteById(id);
    }
}
