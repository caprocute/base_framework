package com.viettel.solution.base.service.impl;

import com.viettel.solution.base.domain.SysTenant;
import com.viettel.solution.base.repository.SysTenantRepository;
import com.viettel.solution.base.service.SysTenantService;
import com.viettel.solution.base.service.dto.SysTenantDTO;
import com.viettel.solution.base.service.mapper.SysTenantMapper;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link SysTenant}.
 */
@Service
@Transactional
public class SysTenantServiceImpl implements SysTenantService {

    private final Logger log = LoggerFactory.getLogger(SysTenantServiceImpl.class);

    private final SysTenantRepository sysTenantRepository;

    private final SysTenantMapper sysTenantMapper;

    public SysTenantServiceImpl(SysTenantRepository sysTenantRepository, SysTenantMapper sysTenantMapper) {
        this.sysTenantRepository = sysTenantRepository;
        this.sysTenantMapper = sysTenantMapper;
    }

    @Override
    public SysTenantDTO save(SysTenantDTO sysTenantDTO) {
        log.debug("Request to save SysTenant : {}", sysTenantDTO);
        SysTenant sysTenant = sysTenantMapper.toEntity(sysTenantDTO);
        sysTenant = sysTenantRepository.save(sysTenant);
        return sysTenantMapper.toDto(sysTenant);
    }

    @Override
    public SysTenantDTO update(SysTenantDTO sysTenantDTO) {
        log.debug("Request to update SysTenant : {}", sysTenantDTO);
        SysTenant sysTenant = sysTenantMapper.toEntity(sysTenantDTO);
        sysTenant.setIsPersisted();
        sysTenant = sysTenantRepository.save(sysTenant);
        return sysTenantMapper.toDto(sysTenant);
    }

    @Override
    public Optional<SysTenantDTO> partialUpdate(SysTenantDTO sysTenantDTO) {
        log.debug("Request to partially update SysTenant : {}", sysTenantDTO);

        return sysTenantRepository
            .findById(sysTenantDTO.getId())
            .map(existingSysTenant -> {
                sysTenantMapper.partialUpdate(existingSysTenant, sysTenantDTO);

                return existingSysTenant;
            })
            .map(sysTenantRepository::save)
            .map(sysTenantMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<SysTenantDTO> findAll(Pageable pageable) {
        log.debug("Request to get all SysTenants");
        return sysTenantRepository.findAll(pageable).map(sysTenantMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<SysTenantDTO> findOne(String id) {
        log.debug("Request to get SysTenant : {}", id);
        return sysTenantRepository.findById(id).map(sysTenantMapper::toDto);
    }

    @Override
    public void delete(String id) {
        log.debug("Request to delete SysTenant : {}", id);
        sysTenantRepository.deleteById(id);
    }
}
