package com.viettel.solution.base.service.impl;

import com.viettel.solution.base.domain.SysRoleIndex;
import com.viettel.solution.base.repository.SysRoleIndexRepository;
import com.viettel.solution.base.service.SysRoleIndexService;
import com.viettel.solution.base.service.dto.SysRoleIndexDTO;
import com.viettel.solution.base.service.mapper.SysRoleIndexMapper;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link SysRoleIndex}.
 */
@Service
@Transactional
public class SysRoleIndexServiceImpl implements SysRoleIndexService {

    private final Logger log = LoggerFactory.getLogger(SysRoleIndexServiceImpl.class);

    private final SysRoleIndexRepository sysRoleIndexRepository;

    private final SysRoleIndexMapper sysRoleIndexMapper;

    public SysRoleIndexServiceImpl(SysRoleIndexRepository sysRoleIndexRepository, SysRoleIndexMapper sysRoleIndexMapper) {
        this.sysRoleIndexRepository = sysRoleIndexRepository;
        this.sysRoleIndexMapper = sysRoleIndexMapper;
    }

    @Override
    public SysRoleIndexDTO save(SysRoleIndexDTO sysRoleIndexDTO) {
        log.debug("Request to save SysRoleIndex : {}", sysRoleIndexDTO);
        SysRoleIndex sysRoleIndex = sysRoleIndexMapper.toEntity(sysRoleIndexDTO);
        sysRoleIndex = sysRoleIndexRepository.save(sysRoleIndex);
        return sysRoleIndexMapper.toDto(sysRoleIndex);
    }

    @Override
    public SysRoleIndexDTO update(SysRoleIndexDTO sysRoleIndexDTO) {
        log.debug("Request to update SysRoleIndex : {}", sysRoleIndexDTO);
        SysRoleIndex sysRoleIndex = sysRoleIndexMapper.toEntity(sysRoleIndexDTO);
        sysRoleIndex.setIsPersisted();
        sysRoleIndex = sysRoleIndexRepository.save(sysRoleIndex);
        return sysRoleIndexMapper.toDto(sysRoleIndex);
    }

    @Override
    public Optional<SysRoleIndexDTO> partialUpdate(SysRoleIndexDTO sysRoleIndexDTO) {
        log.debug("Request to partially update SysRoleIndex : {}", sysRoleIndexDTO);

        return sysRoleIndexRepository
            .findById(sysRoleIndexDTO.getId())
            .map(existingSysRoleIndex -> {
                sysRoleIndexMapper.partialUpdate(existingSysRoleIndex, sysRoleIndexDTO);

                return existingSysRoleIndex;
            })
            .map(sysRoleIndexRepository::save)
            .map(sysRoleIndexMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<SysRoleIndexDTO> findAll(Pageable pageable) {
        log.debug("Request to get all SysRoleIndices");
        return sysRoleIndexRepository.findAll(pageable).map(sysRoleIndexMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<SysRoleIndexDTO> findOne(String id) {
        log.debug("Request to get SysRoleIndex : {}", id);
        return sysRoleIndexRepository.findById(id).map(sysRoleIndexMapper::toDto);
    }

    @Override
    public void delete(String id) {
        log.debug("Request to delete SysRoleIndex : {}", id);
        sysRoleIndexRepository.deleteById(id);
    }
}
