package com.viettel.solution.base.service.impl;

import com.viettel.solution.base.domain.SysDepart;
import com.viettel.solution.base.repository.SysDepartRepository;
import com.viettel.solution.base.service.SysDepartService;
import com.viettel.solution.base.service.dto.SysDepartDTO;
import com.viettel.solution.base.service.mapper.SysDepartMapper;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link SysDepart}.
 */
@Service
@Transactional
public class SysDepartServiceImpl implements SysDepartService {

    private final Logger log = LoggerFactory.getLogger(SysDepartServiceImpl.class);

    private final SysDepartRepository sysDepartRepository;

    private final SysDepartMapper sysDepartMapper;

    public SysDepartServiceImpl(SysDepartRepository sysDepartRepository, SysDepartMapper sysDepartMapper) {
        this.sysDepartRepository = sysDepartRepository;
        this.sysDepartMapper = sysDepartMapper;
    }

    @Override
    public SysDepartDTO save(SysDepartDTO sysDepartDTO) {
        log.debug("Request to save SysDepart : {}", sysDepartDTO);
        SysDepart sysDepart = sysDepartMapper.toEntity(sysDepartDTO);
        sysDepart = sysDepartRepository.save(sysDepart);
        return sysDepartMapper.toDto(sysDepart);
    }

    @Override
    public SysDepartDTO update(SysDepartDTO sysDepartDTO) {
        log.debug("Request to update SysDepart : {}", sysDepartDTO);
        SysDepart sysDepart = sysDepartMapper.toEntity(sysDepartDTO);
        sysDepart.setIsPersisted();
        sysDepart = sysDepartRepository.save(sysDepart);
        return sysDepartMapper.toDto(sysDepart);
    }

    @Override
    public Optional<SysDepartDTO> partialUpdate(SysDepartDTO sysDepartDTO) {
        log.debug("Request to partially update SysDepart : {}", sysDepartDTO);

        return sysDepartRepository
            .findById(sysDepartDTO.getId())
            .map(existingSysDepart -> {
                sysDepartMapper.partialUpdate(existingSysDepart, sysDepartDTO);

                return existingSysDepart;
            })
            .map(sysDepartRepository::save)
            .map(sysDepartMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<SysDepartDTO> findAll(Pageable pageable) {
        log.debug("Request to get all SysDeparts");
        return sysDepartRepository.findAll(pageable).map(sysDepartMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<SysDepartDTO> findOne(String id) {
        log.debug("Request to get SysDepart : {}", id);
        return sysDepartRepository.findById(id).map(sysDepartMapper::toDto);
    }

    @Override
    public void delete(String id) {
        log.debug("Request to delete SysDepart : {}", id);
        sysDepartRepository.deleteById(id);
    }
}
