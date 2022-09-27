package com.viettel.solution.base.service.impl;

import com.viettel.solution.base.domain.SysPosition;
import com.viettel.solution.base.repository.SysPositionRepository;
import com.viettel.solution.base.service.SysPositionService;
import com.viettel.solution.base.service.dto.SysPositionDTO;
import com.viettel.solution.base.service.mapper.SysPositionMapper;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link SysPosition}.
 */
@Service
@Transactional
public class SysPositionServiceImpl implements SysPositionService {

    private final Logger log = LoggerFactory.getLogger(SysPositionServiceImpl.class);

    private final SysPositionRepository sysPositionRepository;

    private final SysPositionMapper sysPositionMapper;

    public SysPositionServiceImpl(SysPositionRepository sysPositionRepository, SysPositionMapper sysPositionMapper) {
        this.sysPositionRepository = sysPositionRepository;
        this.sysPositionMapper = sysPositionMapper;
    }

    @Override
    public SysPositionDTO save(SysPositionDTO sysPositionDTO) {
        log.debug("Request to save SysPosition : {}", sysPositionDTO);
        SysPosition sysPosition = sysPositionMapper.toEntity(sysPositionDTO);
        sysPosition = sysPositionRepository.save(sysPosition);
        return sysPositionMapper.toDto(sysPosition);
    }

    @Override
    public SysPositionDTO update(SysPositionDTO sysPositionDTO) {
        log.debug("Request to update SysPosition : {}", sysPositionDTO);
        SysPosition sysPosition = sysPositionMapper.toEntity(sysPositionDTO);
        sysPosition.setIsPersisted();
        sysPosition = sysPositionRepository.save(sysPosition);
        return sysPositionMapper.toDto(sysPosition);
    }

    @Override
    public Optional<SysPositionDTO> partialUpdate(SysPositionDTO sysPositionDTO) {
        log.debug("Request to partially update SysPosition : {}", sysPositionDTO);

        return sysPositionRepository
            .findById(sysPositionDTO.getId())
            .map(existingSysPosition -> {
                sysPositionMapper.partialUpdate(existingSysPosition, sysPositionDTO);

                return existingSysPosition;
            })
            .map(sysPositionRepository::save)
            .map(sysPositionMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<SysPositionDTO> findAll(Pageable pageable) {
        log.debug("Request to get all SysPositions");
        return sysPositionRepository.findAll(pageable).map(sysPositionMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<SysPositionDTO> findOne(String id) {
        log.debug("Request to get SysPosition : {}", id);
        return sysPositionRepository.findById(id).map(sysPositionMapper::toDto);
    }

    @Override
    public void delete(String id) {
        log.debug("Request to delete SysPosition : {}", id);
        sysPositionRepository.deleteById(id);
    }
}
