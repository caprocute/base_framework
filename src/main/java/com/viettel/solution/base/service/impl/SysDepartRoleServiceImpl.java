package com.viettel.solution.base.service.impl;

import com.viettel.solution.base.domain.SysDepartRole;
import com.viettel.solution.base.repository.SysDepartRoleRepository;
import com.viettel.solution.base.service.SysDepartRoleService;
import com.viettel.solution.base.service.dto.SysDepartRoleDTO;
import com.viettel.solution.base.service.mapper.SysDepartRoleMapper;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link SysDepartRole}.
 */
@Service
@Transactional
public class SysDepartRoleServiceImpl implements SysDepartRoleService {

    private final Logger log = LoggerFactory.getLogger(SysDepartRoleServiceImpl.class);

    private final SysDepartRoleRepository sysDepartRoleRepository;

    private final SysDepartRoleMapper sysDepartRoleMapper;

    public SysDepartRoleServiceImpl(SysDepartRoleRepository sysDepartRoleRepository, SysDepartRoleMapper sysDepartRoleMapper) {
        this.sysDepartRoleRepository = sysDepartRoleRepository;
        this.sysDepartRoleMapper = sysDepartRoleMapper;
    }

    @Override
    public SysDepartRoleDTO save(SysDepartRoleDTO sysDepartRoleDTO) {
        log.debug("Request to save SysDepartRole : {}", sysDepartRoleDTO);
        SysDepartRole sysDepartRole = sysDepartRoleMapper.toEntity(sysDepartRoleDTO);
        sysDepartRole = sysDepartRoleRepository.save(sysDepartRole);
        return sysDepartRoleMapper.toDto(sysDepartRole);
    }

    @Override
    public SysDepartRoleDTO update(SysDepartRoleDTO sysDepartRoleDTO) {
        log.debug("Request to update SysDepartRole : {}", sysDepartRoleDTO);
        SysDepartRole sysDepartRole = sysDepartRoleMapper.toEntity(sysDepartRoleDTO);
        sysDepartRole.setIsPersisted();
        sysDepartRole = sysDepartRoleRepository.save(sysDepartRole);
        return sysDepartRoleMapper.toDto(sysDepartRole);
    }

    @Override
    public Optional<SysDepartRoleDTO> partialUpdate(SysDepartRoleDTO sysDepartRoleDTO) {
        log.debug("Request to partially update SysDepartRole : {}", sysDepartRoleDTO);

        return sysDepartRoleRepository
            .findById(sysDepartRoleDTO.getId())
            .map(existingSysDepartRole -> {
                sysDepartRoleMapper.partialUpdate(existingSysDepartRole, sysDepartRoleDTO);

                return existingSysDepartRole;
            })
            .map(sysDepartRoleRepository::save)
            .map(sysDepartRoleMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<SysDepartRoleDTO> findAll(Pageable pageable) {
        log.debug("Request to get all SysDepartRoles");
        return sysDepartRoleRepository.findAll(pageable).map(sysDepartRoleMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<SysDepartRoleDTO> findOne(String id) {
        log.debug("Request to get SysDepartRole : {}", id);
        return sysDepartRoleRepository.findById(id).map(sysDepartRoleMapper::toDto);
    }

    @Override
    public void delete(String id) {
        log.debug("Request to delete SysDepartRole : {}", id);
        sysDepartRoleRepository.deleteById(id);
    }
}
