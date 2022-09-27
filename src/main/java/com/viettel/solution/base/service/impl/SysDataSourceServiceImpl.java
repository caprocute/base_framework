package com.viettel.solution.base.service.impl;

import com.viettel.solution.base.domain.SysDataSource;
import com.viettel.solution.base.repository.SysDataSourceRepository;
import com.viettel.solution.base.service.SysDataSourceService;
import com.viettel.solution.base.service.dto.SysDataSourceDTO;
import com.viettel.solution.base.service.mapper.SysDataSourceMapper;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link SysDataSource}.
 */
@Service
@Transactional
public class SysDataSourceServiceImpl implements SysDataSourceService {

    private final Logger log = LoggerFactory.getLogger(SysDataSourceServiceImpl.class);

    private final SysDataSourceRepository sysDataSourceRepository;

    private final SysDataSourceMapper sysDataSourceMapper;

    public SysDataSourceServiceImpl(SysDataSourceRepository sysDataSourceRepository, SysDataSourceMapper sysDataSourceMapper) {
        this.sysDataSourceRepository = sysDataSourceRepository;
        this.sysDataSourceMapper = sysDataSourceMapper;
    }

    @Override
    public SysDataSourceDTO save(SysDataSourceDTO sysDataSourceDTO) {
        log.debug("Request to save SysDataSource : {}", sysDataSourceDTO);
        SysDataSource sysDataSource = sysDataSourceMapper.toEntity(sysDataSourceDTO);
        sysDataSource = sysDataSourceRepository.save(sysDataSource);
        return sysDataSourceMapper.toDto(sysDataSource);
    }

    @Override
    public SysDataSourceDTO update(SysDataSourceDTO sysDataSourceDTO) {
        log.debug("Request to update SysDataSource : {}", sysDataSourceDTO);
        SysDataSource sysDataSource = sysDataSourceMapper.toEntity(sysDataSourceDTO);
        sysDataSource.setIsPersisted();
        sysDataSource = sysDataSourceRepository.save(sysDataSource);
        return sysDataSourceMapper.toDto(sysDataSource);
    }

    @Override
    public Optional<SysDataSourceDTO> partialUpdate(SysDataSourceDTO sysDataSourceDTO) {
        log.debug("Request to partially update SysDataSource : {}", sysDataSourceDTO);

        return sysDataSourceRepository
            .findById(sysDataSourceDTO.getId())
            .map(existingSysDataSource -> {
                sysDataSourceMapper.partialUpdate(existingSysDataSource, sysDataSourceDTO);

                return existingSysDataSource;
            })
            .map(sysDataSourceRepository::save)
            .map(sysDataSourceMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<SysDataSourceDTO> findAll(Pageable pageable) {
        log.debug("Request to get all SysDataSources");
        return sysDataSourceRepository.findAll(pageable).map(sysDataSourceMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<SysDataSourceDTO> findOne(String id) {
        log.debug("Request to get SysDataSource : {}", id);
        return sysDataSourceRepository.findById(id).map(sysDataSourceMapper::toDto);
    }

    @Override
    public void delete(String id) {
        log.debug("Request to delete SysDataSource : {}", id);
        sysDataSourceRepository.deleteById(id);
    }
}
