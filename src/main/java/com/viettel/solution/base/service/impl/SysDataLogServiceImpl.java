package com.viettel.solution.base.service.impl;

import com.viettel.solution.base.domain.SysDataLog;
import com.viettel.solution.base.repository.SysDataLogRepository;
import com.viettel.solution.base.service.SysDataLogService;
import com.viettel.solution.base.service.dto.SysDataLogDTO;
import com.viettel.solution.base.service.mapper.SysDataLogMapper;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link SysDataLog}.
 */
@Service
@Transactional
public class SysDataLogServiceImpl implements SysDataLogService {

    private final Logger log = LoggerFactory.getLogger(SysDataLogServiceImpl.class);

    private final SysDataLogRepository sysDataLogRepository;

    private final SysDataLogMapper sysDataLogMapper;

    public SysDataLogServiceImpl(SysDataLogRepository sysDataLogRepository, SysDataLogMapper sysDataLogMapper) {
        this.sysDataLogRepository = sysDataLogRepository;
        this.sysDataLogMapper = sysDataLogMapper;
    }

    @Override
    public SysDataLogDTO save(SysDataLogDTO sysDataLogDTO) {
        log.debug("Request to save SysDataLog : {}", sysDataLogDTO);
        SysDataLog sysDataLog = sysDataLogMapper.toEntity(sysDataLogDTO);
        sysDataLog = sysDataLogRepository.save(sysDataLog);
        return sysDataLogMapper.toDto(sysDataLog);
    }

    @Override
    public SysDataLogDTO update(SysDataLogDTO sysDataLogDTO) {
        log.debug("Request to update SysDataLog : {}", sysDataLogDTO);
        SysDataLog sysDataLog = sysDataLogMapper.toEntity(sysDataLogDTO);
        sysDataLog.setIsPersisted();
        sysDataLog = sysDataLogRepository.save(sysDataLog);
        return sysDataLogMapper.toDto(sysDataLog);
    }

    @Override
    public Optional<SysDataLogDTO> partialUpdate(SysDataLogDTO sysDataLogDTO) {
        log.debug("Request to partially update SysDataLog : {}", sysDataLogDTO);

        return sysDataLogRepository
            .findById(sysDataLogDTO.getId())
            .map(existingSysDataLog -> {
                sysDataLogMapper.partialUpdate(existingSysDataLog, sysDataLogDTO);

                return existingSysDataLog;
            })
            .map(sysDataLogRepository::save)
            .map(sysDataLogMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<SysDataLogDTO> findAll(Pageable pageable) {
        log.debug("Request to get all SysDataLogs");
        return sysDataLogRepository.findAll(pageable).map(sysDataLogMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<SysDataLogDTO> findOne(String id) {
        log.debug("Request to get SysDataLog : {}", id);
        return sysDataLogRepository.findById(id).map(sysDataLogMapper::toDto);
    }

    @Override
    public void delete(String id) {
        log.debug("Request to delete SysDataLog : {}", id);
        sysDataLogRepository.deleteById(id);
    }
}
