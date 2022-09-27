package com.viettel.solution.base.service.impl;

import com.viettel.solution.base.domain.SysUserDepart;
import com.viettel.solution.base.repository.SysUserDepartRepository;
import com.viettel.solution.base.service.SysUserDepartService;
import com.viettel.solution.base.service.dto.SysUserDepartDTO;
import com.viettel.solution.base.service.mapper.SysUserDepartMapper;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link SysUserDepart}.
 */
@Service
@Transactional
public class SysUserDepartServiceImpl implements SysUserDepartService {

    private final Logger log = LoggerFactory.getLogger(SysUserDepartServiceImpl.class);

    private final SysUserDepartRepository sysUserDepartRepository;

    private final SysUserDepartMapper sysUserDepartMapper;

    public SysUserDepartServiceImpl(SysUserDepartRepository sysUserDepartRepository, SysUserDepartMapper sysUserDepartMapper) {
        this.sysUserDepartRepository = sysUserDepartRepository;
        this.sysUserDepartMapper = sysUserDepartMapper;
    }

    @Override
    public SysUserDepartDTO save(SysUserDepartDTO sysUserDepartDTO) {
        log.debug("Request to save SysUserDepart : {}", sysUserDepartDTO);
        SysUserDepart sysUserDepart = sysUserDepartMapper.toEntity(sysUserDepartDTO);
        sysUserDepart = sysUserDepartRepository.save(sysUserDepart);
        return sysUserDepartMapper.toDto(sysUserDepart);
    }

    @Override
    public SysUserDepartDTO update(SysUserDepartDTO sysUserDepartDTO) {
        log.debug("Request to update SysUserDepart : {}", sysUserDepartDTO);
        SysUserDepart sysUserDepart = sysUserDepartMapper.toEntity(sysUserDepartDTO);
        sysUserDepart.setIsPersisted();
        sysUserDepart = sysUserDepartRepository.save(sysUserDepart);
        return sysUserDepartMapper.toDto(sysUserDepart);
    }

    @Override
    public Optional<SysUserDepartDTO> partialUpdate(SysUserDepartDTO sysUserDepartDTO) {
        log.debug("Request to partially update SysUserDepart : {}", sysUserDepartDTO);

        return sysUserDepartRepository
            .findById(sysUserDepartDTO.getId())
            .map(existingSysUserDepart -> {
                sysUserDepartMapper.partialUpdate(existingSysUserDepart, sysUserDepartDTO);

                return existingSysUserDepart;
            })
            .map(sysUserDepartRepository::save)
            .map(sysUserDepartMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<SysUserDepartDTO> findAll(Pageable pageable) {
        log.debug("Request to get all SysUserDeparts");
        return sysUserDepartRepository.findAll(pageable).map(sysUserDepartMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<SysUserDepartDTO> findOne(String id) {
        log.debug("Request to get SysUserDepart : {}", id);
        return sysUserDepartRepository.findById(id).map(sysUserDepartMapper::toDto);
    }

    @Override
    public void delete(String id) {
        log.debug("Request to delete SysUserDepart : {}", id);
        sysUserDepartRepository.deleteById(id);
    }
}
